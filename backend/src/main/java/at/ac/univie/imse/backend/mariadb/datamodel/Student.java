package at.ac.univie.imse.backend.mariadb.datamodel;

import jakarta.persistence.*;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    private Set<TopicChoice> choices = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "bookmarked",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "topic_id")}
    )
    private Set<ThesisTopic> bookmarkedTopics = new HashSet<>();

    public Student() {
    }

    public Student(UserType userType, String username, Name name, String email, String password, String studyProgram, int matriculationNumber, Set<TopicChoice> choices, Set<ThesisTopic> bookmarkedTopics) {
        super(userType, username, name, email, password);
        this.studyProgram = studyProgram;
        this.choices = choices;
        this.matriculationNumber = matriculationNumber;
        this.bookmarkedTopics = bookmarkedTopics;
    }
}
