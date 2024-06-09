package at.ac.univie.imse.backend.mongodb.datamodel;

public class MongoInstructorInTopicChoice {
    private String _id;
    private MongoName name;

    public MongoInstructorInTopicChoice() {
    }

    public MongoInstructorInTopicChoice(String _id, MongoName name) {
        this._id = _id;
        this.name = name;
    }

    public MongoInstructorInTopicChoice(MongoInstructor instructor) {
        this._id = instructor.getUserId();
        this.name = instructor.getName();
    }

    public MongoInstructorInTopicChoice(MongoInstructorInThesisTopic instructor) {
        this._id = instructor.get_id();
        this.name = instructor.getName();
    }
}
