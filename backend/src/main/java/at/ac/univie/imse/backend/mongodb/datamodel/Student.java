package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Document(collection = "students")
public class Student {
    @MongoId
    private String userId;
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

    public Student(at.ac.univie.imse.backend.mariadb.datamodel.Student student, List<at.ac.univie.imse.backend.mariadb.datamodel.AssignedTopic> assignedTopic) {
        this.userId = String.valueOf(student.getUserId());
        this.email = student.getEmail();
        this.username = student.getUsername();
        this.password = student.getPassword();
        this.name = new Name(student.getName().firstName(), student.getName().lastName());
        this.matriculationNumber = student.getMatriculationNumber();
        this.studyProgram = student.getStudyProgram();
        this.topicChoices = new HashSet<>();
        for (at.ac.univie.imse.backend.mariadb.datamodel.TopicChoice topicChoice : student.getChoices()) {
            this.topicChoices.add(new TopicChoice(topicChoice));
        }
        this.bookmarkedTopics = new HashSet<>();
        for (at.ac.univie.imse.backend.mariadb.datamodel.ThesisTopic thesisTopic : student.getBookmarkedTopics()) {
            this.bookmarkedTopics.add(new ThesisTopic(thesisTopic.getTopicId(), thesisTopic.getTitle(), thesisTopic.getDescription(), thesisTopic.getSupervisor()));
        }
        if (assignedTopic != null && (!assignedTopic.isEmpty())) {
            this.assignedTopic = new AssignedTopic(assignedTopic.getFirst());
        }
    }
}
