package at.ac.univie.imse.backend.mariadb.repositories;

import at.ac.univie.imse.backend.configuration.repodetection.ExposeViaRestIf;
import at.ac.univie.imse.backend.mariadb.datamodel.ThesisTopic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@ExposeViaRestIf("expose-mariadb")
public interface TopicRepository extends PagingAndSortingRepository<ThesisTopic, Long>, CrudRepository<ThesisTopic, Long> {

    @RestResource(path = "exists")
    boolean existsThesisTopicByTitleIgnoreCase(String title);
}
