package at.ac.univie.imse.backend.mariadb.datamodel;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Student extends User {
    private String studyProgram;
    private int matriculationNumber;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<TopicChoice> choices = new HashSet<>();
}
