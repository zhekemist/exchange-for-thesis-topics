package at.ac.univie.imse.backend.mongodb.datamodel;

public class MongoThesisTopicBookmark {
    private String _id;
    private String title;
    private String description;
    private MongoInstructorInTopicChoice instructor;

    public MongoThesisTopicBookmark() {
    }

    public MongoThesisTopicBookmark(String _id, String title, String description, MongoInstructorInTopicChoice instructor) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.instructor = instructor;
    }

    public MongoThesisTopicBookmark(MongoThesisTopic thesisTopic) {
        this._id = thesisTopic.getTopicId();
        this.title = thesisTopic.getTitle();
        this.description = thesisTopic.getDescription();
        this.instructor = new MongoInstructorInTopicChoice(thesisTopic.getInstructor());
    }
}
