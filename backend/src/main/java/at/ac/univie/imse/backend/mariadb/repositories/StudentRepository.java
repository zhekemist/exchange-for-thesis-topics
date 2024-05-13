package at.ac.univie.imse.backend.mariadb.repositories;

import at.ac.univie.imse.backend.configuration.repodetection.ExposeViaRestIf;
import at.ac.univie.imse.backend.mariadb.datamodel.Student;
import org.springframework.data.repository.CrudRepository;

@ExposeViaRestIf("dbs.maria.rest.expose")
public interface StudentRepository extends CrudRepository<Student, Long> {
}
