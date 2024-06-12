package at.ac.univie.imse.backend.mongodb.repositories;

import at.ac.univie.imse.backend.mongodb.datamodel.Student;
import at.ac.univie.imse.backend.mongodb.projections.SuperviseesProjection;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Profile("mongodb")
@RepositoryRestResource(collectionResourceRel = "students", path = "students")
public interface StudentMongoRepository extends MongoRepository<Student, String> {

    @Aggregation(pipeline = {
            "{$match: {'assignedTopic.topic.instructor.researchGroup.name': ?0}}",
            "{$sort: {'assignedTopic.topic.title': 1}}",
            "{$project : {" +
                    "    firstName: '$name.firstName'," +
                    "    lastName: '$name.lastName'," +
                    "    matriculationNumber: 1," +
                    "    email: 1, " +
                    "    title: '$assignedTopic.topic.title', " +
                    "    supervisorFirstName: '$assignedTopic.topic.instructor.name.firstName'," +
                    "    supervisorLastName: '$assignedTopic.topic.instructor.name.lastName'" +
                    "}}"
    })
    Iterable<SuperviseesProjection> supervisees(String researchGroupName);
}
