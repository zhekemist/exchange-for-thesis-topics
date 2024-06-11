package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;

@Data
public class AssignedTopic {
    private String reason;
    private ThesisTopic topic;

    public AssignedTopic(String reason, ThesisTopic topic) {
        this.reason = reason;
        this.topic = topic;
    }
}
