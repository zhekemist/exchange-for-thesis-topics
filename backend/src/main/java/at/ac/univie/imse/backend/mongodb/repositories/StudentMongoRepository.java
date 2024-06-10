package at.ac.univie.imse.backend.mongodb.repositories;

import at.ac.univie.imse.backend.configuration.repodetection.ExposeViaRestIf;
import at.ac.univie.imse.backend.mongodb.datamodel.MongoStudent;
import org.springframework.data.mongodb.repository.MongoRepository;

@ExposeViaRestIf("expose-mongodb")
public interface StudentMongoRepository extends MongoRepository<MongoStudent, String> {
}
