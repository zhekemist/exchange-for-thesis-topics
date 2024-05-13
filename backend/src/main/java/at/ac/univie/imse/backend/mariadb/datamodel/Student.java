package at.ac.univie.imse.backend.mariadb.datamodel;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student extends User {
    private String studyProgram;
    private int matriculationNumber;

    /*@OneToMany
    @JoinTable(name = "choose")
    @JoinColumn(name = "user_id")
    private Set<TopicChoice> choiceList;*/
}
