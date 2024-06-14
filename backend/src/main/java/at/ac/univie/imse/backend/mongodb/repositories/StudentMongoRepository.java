package at.ac.univie.imse.backend.mongodb.repositories;

import at.ac.univie.imse.backend.mongodb.datamodel.Student;
import at.ac.univie.imse.backend.mongodb.projections.PopularTopicsProjection;
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

    @Aggregation(pipeline = {
            "{$match: {'studyProgram': 'Bachelor of Computer Science'}}",
            "{$unwind: '$topicChoices'}",
            "{$group: {" +
                    "    _id: '$topicChoices.topic._id'," +
                    "    title: { $first: '$topicChoices.topic.title' }," +
                    "    counter: { $sum: 1 }" +
                    "}}",
            "{$lookup: {" +
                    "    from: 'thesisTopics'," +
                    "    localField: '_id'," +
                    "    foreignField: '_id'," +
                    "    as: 'topics'" +
                    "}}",
            "{$unwind: '$topics'}",
            "{$unwind: '$topics.categories'}",
            "{$unwind: '$topics.categories.name'}",
            "{$project: {" +
                    "    _id: 1," +
                    "    title: 1," +
                    "    counter: 1," +
                    "    category: '$topics.categories.name'" +
                    "}}",
            "{$sort: { counter: -1 }}",
            "{$group: {" +
                    "    _id: '$category'," +
                    "    topicCounter: { $first: '$counter' }," +
                    "    topics: {" +
                    "        $push: {" +
                    "            _id: '$_id'," +
                    "            title: '$title'," +
                    "            counter: '$counter'" +
                    "        }}" +
                    "}}",
            "{$project: {" +
                    "    _id: 1," +
                    "    categoryName: '$_id'," +
                    "    topicCounter: 1," +
                    "    topics: {" +
                    "    $filter: {" +
                    "        input: '$topics'," +
                    "        as: 'topic'," +
                    "        cond: { $eq: ['$$topic.counter', '$topicCounter'] }" +
                    "        }}" +
                    "}}",
            "{$project: {" +
                    "    _id: 0," +
                    "    categoryName: '$categoryName'," +
                    "    topicCounter: 1," +
                    "    topicTitles: {" +
                    "        $reduce: {" +
                    "            input: '$topics'," +
                    "            initialValue: ''," +
                    "            in: {" +
                    "                $concat: ['$$value', { $cond: { if: { $eq: ['$$value', ''] }, then: '', else: ', ' } }, '$$this.title']" +
                    "        }}" +
                    "    }" +
                    "}}",
            "{$sort: {categoryName: 1}}"
    })
    Iterable<PopularTopicsProjection> popularTopics();
}
