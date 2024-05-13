package at.ac.univie.imse.backend.mariadb.datamodel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;


@Embeddable
public record TopicChoiceID(@Column(name = "topic_id", insertable = false, updatable = false) long topicId,
                            @Column(name = "user_id") long userId) implements Serializable {
}
