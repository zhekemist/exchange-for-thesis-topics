package at.ac.univie.imse.backend.mariadb.datamodel;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;


@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TopicChoiceID implements Serializable {
    private long topic;
    private long student;
}
