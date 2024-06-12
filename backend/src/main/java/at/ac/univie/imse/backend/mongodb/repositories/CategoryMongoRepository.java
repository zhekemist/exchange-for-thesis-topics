package at.ac.univie.imse.backend.mongodb.repositories;

import at.ac.univie.imse.backend.mongodb.datamodel.Category;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@Profile("mongodb")
@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface CategoryMongoRepository extends MongoRepository<Category, String> {
    @RestResource(path = "name")
    List<Category> findCategoriesByNameContainsIgnoreCase(String name);

    @RestResource(path = "name-exact")
    List<Category> findByNameIgnoreCase(String name);
}
