package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TopicChoice {
    private ThesisTopic topic;
    private LocalDateTime timestamp;
    private int priorityPoints;

    public TopicChoice(ThesisTopic topic, LocalDateTime timestamp, int priorityPoints) {
        this.topic = topic;
        this.timestamp = timestamp;
        this.priorityPoints = priorityPoints;
    }

    public TopicChoice(at.ac.univie.imse.backend.mariadb.datamodel.TopicChoice topicChoice) {
        at.ac.univie.imse.backend.mariadb.datamodel.ThesisTopic mariaTopic = topicChoice.getTopic();
        this.topic = new ThesisTopic(mariaTopic.getTopicId(), mariaTopic.getTitle(), mariaTopic.getDescription(), mariaTopic.getSupervisor());
        this.timestamp = topicChoice.getTimestamp();
        this.priorityPoints = topicChoice.getPriorityPoints();
    }
}
