package at.ac.univie.imse.backend.mongodb.datamodel;

import java.time.LocalDateTime;

public class MongoTopicChoice {
    private MongoThesisTopicInTopicChoice topic;
    private LocalDateTime timestamp;
    private int priorityPoints;

    public MongoTopicChoice(MongoThesisTopicInTopicChoice topic, LocalDateTime timestamp, int priorityPoints) {
        this.topic = topic;
        this.timestamp = timestamp;
        this.priorityPoints = priorityPoints;
    }
}
