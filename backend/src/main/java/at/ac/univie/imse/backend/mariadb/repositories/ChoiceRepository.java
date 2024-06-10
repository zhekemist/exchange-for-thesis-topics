package at.ac.univie.imse.backend.mariadb.repositories;

import at.ac.univie.imse.backend.configuration.repodetection.ExposeViaRestIf;
import at.ac.univie.imse.backend.mariadb.datamodel.TopicChoice;
import at.ac.univie.imse.backend.mariadb.datamodel.TopicChoiceID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

@ExposeViaRestIf("expose-mariadb")
public interface ChoiceRepository extends PagingAndSortingRepository<TopicChoice, TopicChoiceID>, CrudRepository<TopicChoice, TopicChoiceID> {
}
