package at.ac.univie.imse.backend.mariadb.repositories;

import at.ac.univie.imse.backend.mariadb.datamodel.AssignedTopic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TopicAssignmentRepository extends PagingAndSortingRepository<AssignedTopic, Long>, CrudRepository<AssignedTopic, Long> {
}
