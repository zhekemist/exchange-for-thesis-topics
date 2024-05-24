package at.ac.univie.imse.backend.mariadb.datamodel;

import jakarta.persistence.Entity;
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

    @OneToMany(mappedBy = "student")
    private Set<TopicChoice> choices = new HashSet<>();

    public Student() {
    }

    public Student(UserType userType, String username, Name name, String email, String password, String studyProgram, int matriculationNumber, Set<TopicChoice> choices) {
        super(userType, username, name, email, password);
        this.studyProgram = studyProgram;
        this.choices = choices;
        this.matriculationNumber = matriculationNumber;
    }
}
