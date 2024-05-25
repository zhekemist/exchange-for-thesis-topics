package at.ac.univie.imse.backend.mariadb.datamodel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "assigned_to")
@IdClass(AssignedTopicID.class)
public class AssignedTopic {
    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private Student student;

    @Id
    @OneToOne
    @JoinColumn(name = "topic_id")
    private ThesisTopic topic;

    private String reason;

    public AssignedTopic() {}

    public AssignedTopic(Student student, ThesisTopic topic, String reason) {
        this.student = student;
        this.topic = topic;
        this.reason = reason;
    }
}
