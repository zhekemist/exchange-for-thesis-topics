package at.ac.univie.imse.backend.mariadb.datamodel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table(name = "student")
@RequiredArgsConstructor
@Getter
public class Student {
    @Id private final long id;
    private final String studyProgram;
    private final int matriculationNumber;

    private final List<TopicChoice> choiceList;
}
