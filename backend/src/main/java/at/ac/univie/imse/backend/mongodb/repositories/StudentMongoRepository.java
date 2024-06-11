package at.ac.univie.imse.backend.mongodb.repositories;

import at.ac.univie.imse.backend.mongodb.datamodel.Student;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Profile("mongodb")
@RepositoryRestResource(collectionResourceRel = "students", path = "students")
public interface StudentMongoRepository extends MongoRepository<Student, String> {
}
