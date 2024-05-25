package at.ac.univie.imse.backend.mariadb.repositories;

import at.ac.univie.imse.backend.configuration.repodetection.ExposeViaRestIf;
import at.ac.univie.imse.backend.mariadb.datamodel.AssignedTopic;
import at.ac.univie.imse.backend.mariadb.datamodel.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

@ExposeViaRestIf("dbs.maria.rest.expose")
public interface TopicAssignmentRepository extends PagingAndSortingRepository<AssignedTopic, Long>, CrudRepository<AssignedTopic, Long> {
}
