package at.ac.univie.imse.backend.mariadb.repositories;

import at.ac.univie.imse.backend.mariadb.datamodel.Student;
import at.ac.univie.imse.backend.mariadb.projections.SuperviseesProjection;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Profile("mariadb")
@RepositoryRestResource
public interface StudentRepository extends PagingAndSortingRepository<Student, Long>, CrudRepository<Student, Long> {

    @Query(nativeQuery = true, value = "SELECT SUM(priority_points) FROM topic_choice WHERE user_id=?1")
    Long priorityPoints(Long studentId);

    @Query(nativeQuery = true, value = "WITH matching_instructor AS (\n" +
            "    SELECT instructor.user_id, first_name, last_name\n" +
            "    FROM instructor\n" +
            "    INNER JOIN\n" +
            "        research_group\n" +
            "        ON instructor.group_id = research_group.group_id\n" +
            "        AND research_group.name = ?1\n" +
            "    INNER JOIN\n" +
            "        user\n" +
            "        ON instructor.user_id = user.user_id\n" +
            "),\n" +
            "    matching_topics AS (\n" +
            "        SELECT topic_id, title, first_name, last_name\n" +
            "        FROM thesis_topic\n" +
            "        INNER JOIN\n" +
            "            matching_instructor\n" +
            "            ON thesis_topic.supervisor_id = matching_instructor.user_id\n" +
            "    ),\n" +
            "    matching_students AS (\n" +
            "        SELECT student.user_id, matriculation_number,\n" +
            "               title,\n" +
            "               first_name AS supervisor_first_name,\n" +
            "               last_name  AS supervisor_last_name\n" +
            "        FROM student\n" +
            "        INNER JOIN\n" +
            "            assigned_to\n" +
            "            ON student.user_id = assigned_to.user_id\n" +
            "        INNER JOIN\n" +
            "            matching_topics\n" +
            "            ON matching_topics.topic_id = assigned_to.topic_id\n" +
            "    )\n" +
            "SELECT first_name AS firstName," +
            "       last_name AS lastName," +
            "       matriculation_number AS matriculationNumber," +
            "       email," +
            "       title," +
            "       supervisor_first_name AS supervisorFirstName," +
            "       supervisor_last_name AS supervisorLastName \n" +
            "FROM matching_students\n" +
            "INNER JOIN\n" +
            "    user\n" +
            "    ON matching_students.user_id = user.user_id\n" +
            "ORDER BY title;")
    Iterable<SuperviseesProjection> superviseesReport(String researchGroupName);
}
