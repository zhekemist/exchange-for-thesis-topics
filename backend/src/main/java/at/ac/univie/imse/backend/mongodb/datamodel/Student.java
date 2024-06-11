package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@NoArgsConstructor
@Document(collection = "students")
public class Student {
    @Id
    private long userId;
    private String email;
    private String username;
    private String password;
    private Name name;
    private int matriculationNumber;
    private String studyProgram;
    private Set<TopicChoice> topicChoices;
    private Set<ThesisTopic> bookmarkedTopics;
    private AssignedTopic assignedTopic;

    public Student(String email, String username, String password, Name name, int matriculationNumber, String studyProgram, Set<TopicChoice> topicChoices, Set<ThesisTopic> bookmarkedTopics, AssignedTopic assignedTopic) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.matriculationNumber = matriculationNumber;
        this.studyProgram = studyProgram;
        this.topicChoices = topicChoices;
        this.bookmarkedTopics = bookmarkedTopics;
        this.assignedTopic = assignedTopic;
    }
}
