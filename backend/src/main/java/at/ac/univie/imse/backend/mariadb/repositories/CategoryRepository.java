package at.ac.univie.imse.backend.mariadb.repositories;

import at.ac.univie.imse.backend.configuration.repodetection.ExposedIf;
import at.ac.univie.imse.backend.mariadb.datamodel.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@ExposedIf("dbs.maria.rest.expose")
@RepositoryRestResource
public interface CategoryRepository extends CrudRepository<Category, Long> {
    public List<Category> findByNameContaining(String name);
}
