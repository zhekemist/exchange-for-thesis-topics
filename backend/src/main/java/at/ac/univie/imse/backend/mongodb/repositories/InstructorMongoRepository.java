package at.ac.univie.imse.backend.mongodb.repositories;

import at.ac.univie.imse.backend.configuration.repodetection.ExposeViaRestIf;
import at.ac.univie.imse.backend.mongodb.datamodel.MongoInstructor;
import org.springframework.data.mongodb.repository.MongoRepository;

@ExposeViaRestIf("expose-mongodb")
//@RepositoryRestResource(collectionResourceRel = "instructors", path = "instructors")
public interface InstructorMongoRepository extends MongoRepository<MongoInstructor, String> {
}
