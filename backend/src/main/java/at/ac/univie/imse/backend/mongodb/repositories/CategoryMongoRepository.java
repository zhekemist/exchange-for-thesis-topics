package at.ac.univie.imse.backend.mongodb.repositories;

import at.ac.univie.imse.backend.configuration.repodetection.ExposeViaRestIf;
import at.ac.univie.imse.backend.mongodb.datamodel.MongoCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

@ExposeViaRestIf("expose-mongodb")
//@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface CategoryMongoRepository extends MongoRepository<MongoCategory, String> {
}
