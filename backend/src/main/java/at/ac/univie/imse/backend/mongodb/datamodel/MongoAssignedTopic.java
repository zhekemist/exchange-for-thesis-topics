package at.ac.univie.imse.backend.mongodb.datamodel;

public class MongoAssignedTopic {
    private String reason;
    private MongoThesisTopicInAssignedTopic topic;

    public MongoAssignedTopic(String reason, MongoThesisTopicInAssignedTopic topic) {
        this.reason = reason;
        this.topic = topic;
    }
}
