package at.ac.univie.imse.backend.mariadb.repositories;

import at.ac.univie.imse.backend.configuration.repodetection.ExposeViaRestIf;
import at.ac.univie.imse.backend.mariadb.datamodel.Category;
import at.ac.univie.imse.backend.mariadb.datamodel.TopicChoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

@ExposeViaRestIf("dbs.maria.rest.expose")
public interface ChoiceRepository extends PagingAndSortingRepository<TopicChoice, Long>, CrudRepository<TopicChoice, Long> {
}
