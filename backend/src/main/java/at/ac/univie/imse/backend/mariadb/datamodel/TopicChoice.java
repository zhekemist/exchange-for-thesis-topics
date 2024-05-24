package at.ac.univie.imse.backend.mariadb.datamodel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@IdClass(TopicChoiceID.class)
public class TopicChoice {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Student student;

    @Id
    @OneToOne
    @JoinColumn(name = "topic_id")
    private ThesisTopic topic;

    private LocalDateTime timestamp;
    private int priorityPoints;

    public TopicChoice() {
    }

    public TopicChoice(LocalDateTime timestamp, int priorityPoints, ThesisTopic topic, Student student) {
        this.timestamp = timestamp;
        this.priorityPoints = priorityPoints;
        this.topic = topic;
        this.student = student;
    }
}
