package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document
public class MongoStudent {
    @Id
    private String userId;
    private String email;
    private String username;
    private String password;
    private MongoName name;
    private int matriculationNumber;
    private String studyProgram;
    private Set<MongoTopicChoice> topicChoices;
    private Set<MongoThesisTopicBookmark> bookmarkedTopics;
    private MongoAssignedTopic assignedTopic;

    public MongoStudent() {
    }

    public MongoStudent(String email, String username, String password, MongoName name, int matriculationNumber, String studyProgram, Set<MongoTopicChoice> topicChoices, Set<MongoThesisTopicBookmark> bookmarkedTopics, MongoAssignedTopic assignedTopic) {
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
