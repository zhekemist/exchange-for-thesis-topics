package at.ac.univie.imse.backend.mariadb.repositories;

import at.ac.univie.imse.backend.mariadb.datamodel.Category;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@Profile("mariadb")
@RepositoryRestResource
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>, CrudRepository<Category, Long> {
    @RestResource(path = "name")
    List<Category> findCategoriesByNameContainsIgnoreCase(String name);

    @RestResource(path = "name-exact")
    List<Category> findByNameIgnoreCase(String name);
}
