package at.ac.univie.imse.backend.mongodb.datamodel;

public class MongoAssignedTopic {
    private String reason;
    private MongoThesisTopic topic;

    public MongoAssignedTopic(String reason, MongoThesisTopic topic) {
        this.reason = reason;
        this.topic = topic;
    }
}
