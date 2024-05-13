package at.ac.univie.imse.backend.mariadb.datamodel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class TopicChoice {
    @Id
    private long topicId;
    @Id
    private long userId;

    private LocalDateTime timestamp;
    private int priorityPoints;

    /*@OneToOne(mappedBy = "id")
    private ThesisTopic topic;*/
}