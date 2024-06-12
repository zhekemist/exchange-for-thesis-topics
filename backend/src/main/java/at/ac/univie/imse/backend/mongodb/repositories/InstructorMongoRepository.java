package at.ac.univie.imse.backend.mongodb.repositories;

import at.ac.univie.imse.backend.mongodb.datamodel.Instructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Profile("mongodb")
@RepositoryRestResource(collectionResourceRel = "instructors", path = "instructors")
public interface InstructorMongoRepository extends MongoRepository<Instructor, String> {

    @Aggregation(pipeline = {
            "{$group: {'_id': '$researchGroup.name'}}",
            "{$sample: { size: 1 }}"
    })
    String randomResearchGroupName();
}
