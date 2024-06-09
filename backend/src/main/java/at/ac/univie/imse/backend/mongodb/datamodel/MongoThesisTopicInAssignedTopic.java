package at.ac.univie.imse.backend.mongodb.datamodel;

import java.util.HashSet;
import java.util.Set;

public class MongoThesisTopicInAssignedTopic {
    private String _id;
    private String title;
    private String description;
    private MongoInstructor instructor;
    private Set<MongoCategory> categories = new HashSet<>();
    private Set<MongoLiteratureReference> literatureReferences = new HashSet<>();

    public MongoThesisTopicInAssignedTopic() {
    }

    public MongoThesisTopicInAssignedTopic(String topicId, String title, String description, MongoInstructor instructor, Set<MongoCategory> categories, Set<MongoLiteratureReference> references) {
        this._id = topicId;
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.categories = categories;
        this.literatureReferences = references;
    }
}
