package at.ac.univie.imse.backend.test;

import at.ac.univie.imse.backend.configuration.repodetection.ExposedIf;
import at.ac.univie.imse.backend.test.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@ExposedIf("dbs.maria.rest.expose")
@RepositoryRestResource()
public interface PersonRepository extends CrudRepository<Person, Long> {

}
