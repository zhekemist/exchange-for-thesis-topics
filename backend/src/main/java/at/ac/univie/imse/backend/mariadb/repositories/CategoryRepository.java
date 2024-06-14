package at.ac.univie.imse.backend.mariadb.repositories;

import at.ac.univie.imse.backend.mariadb.datamodel.Category;
import at.ac.univie.imse.backend.mariadb.projections.PopularTopicsProjection;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@Profile("mariadb")
@RepositoryRestResource
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>, CrudRepository<Category, Long> {
    @RestResource(path = "name")
    List<Category> findCategoriesByNameContainsIgnoreCase(String name);

    @RestResource(path = "name-exact")
    List<Category> findByNameIgnoreCase(String name);

    @Query(nativeQuery = true, value = "SELECT\n" +
            "    topicRanking.name AS categoryName,\n" +
            "    GROUP_CONCAT(DISTINCT topicRanking.title) AS topicTitles,\n" +
            "    topicRanking.topicCounter\n" +
            "FROM (SELECT category.name,\n" +
            "             topicEntries.title,\n" +
            "             topicEntries.topicCounter,\n" +
            "             RANK() OVER (PARTITION BY category.category_id ORDER BY topicEntries.topicCounter DESC) AS topicRank\n" +
            "      FROM (\n" +
            "            SELECT\n" +
            "                thesis_topic.topic_id,\n" +
            "                thesis_topic.title,\n" +
            "                COUNT(*) OVER (PARTITION BY thesis_topic.topic_id) AS topicCounter\n" +
            "            FROM\n" +
            "                thesis_topic\n" +
            "                    INNER JOIN topic_choice ON thesis_topic.topic_id = topic_choice.topic_id\n" +
            "                    INNER JOIN student ON topic_choice.user_id = student.user_id\n" +
            "            WHERE\n" +
            "                student.study_program = 'Bachelor of Computer Science'\n" +
            "            ) AS topicEntries\n" +
            "               INNER JOIN belongs_to ON topicEntries.topic_id = belongs_to.topic_id\n" +
            "               INNER JOIN category ON belongs_to.category_id = category.category_id\n" +
            "      ) AS topicRanking\n" +
            "WHERE\n" +
            "    topicRanking.topicRank = 1\n" +
            "GROUP BY\n" +
            "    topicRanking.name\n" +
            "ORDER BY\n" +
            "    topicRanking.name;")
    Iterable<PopularTopicsProjection> popularTopicsReport();
}
