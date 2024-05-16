package at.ac.univie.imse.backend.mariadb.datamodel;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class TopicChoice {
    @EmbeddedId
    private TopicChoiceID id;

    private LocalDateTime timestamp;
    private int priorityPoints;

    @OneToOne
    @JoinColumn(name = "topic_id")
    private ThesisTopic topic;

    public TopicChoice() {}

    public TopicChoice(LocalDateTime timestamp, int priorityPoints, ThesisTopic topic) {
        this.timestamp = timestamp;
        this.priorityPoints = priorityPoints;
        this.topic = topic;
    }
}