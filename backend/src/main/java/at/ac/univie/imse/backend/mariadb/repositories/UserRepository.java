package at.ac.univie.imse.backend.mariadb.repositories;

import at.ac.univie.imse.backend.configuration.repodetection.ExposedIf;
import at.ac.univie.imse.backend.mariadb.datamodel.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@ExposedIf("dbs.maria.rest.expose")
@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long>{

}
