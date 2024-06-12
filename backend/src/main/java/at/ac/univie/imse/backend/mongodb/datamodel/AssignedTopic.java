package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AssignedTopic {
    private String reason;
    private ThesisTopic topic;

    public AssignedTopic(String reason, ThesisTopic topic) {
        this.reason = reason;
        this.topic = topic;
    }

    public AssignedTopic(at.ac.univie.imse.backend.mariadb.datamodel.AssignedTopic assignedTopic) {
        this.reason = assignedTopic.getReason();
        this.topic = new ThesisTopic(assignedTopic.getTopic(), true);
    }
}
