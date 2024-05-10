package at.ac.univie.imse.backend.mariadb.datamodel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "choose")
@RequiredArgsConstructor
@Getter
public class TopicChoice {
    private final ThesisTopic topic;
    private final LocalDateTime timestamp;
    private final int priorityPoints;
}