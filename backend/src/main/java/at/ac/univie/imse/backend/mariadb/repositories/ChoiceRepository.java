package at.ac.univie.imse.backend.mariadb.repositories;

import at.ac.univie.imse.backend.mariadb.datamodel.TopicChoice;
import at.ac.univie.imse.backend.mariadb.datamodel.TopicChoiceID;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Profile("mariadb")
@RepositoryRestResource
public interface ChoiceRepository extends PagingAndSortingRepository<TopicChoice, TopicChoiceID>, CrudRepository<TopicChoice, TopicChoiceID> {
}
