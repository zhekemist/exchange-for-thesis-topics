package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TopicChoice {
    private ThesisTopic topic;
    private LocalDateTime timestamp;
    private int priorityPoints;

    public TopicChoice(ThesisTopic topic, LocalDateTime timestamp, int priorityPoints) {
        this.topic = topic;
        this.timestamp = timestamp;
        this.priorityPoints = priorityPoints;
    }
}
