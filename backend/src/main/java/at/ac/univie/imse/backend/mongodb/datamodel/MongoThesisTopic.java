package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
//@Document(collection = "thesisTopics")@Document
public class MongoThesisTopic {
    @Id
    private String topicId;
    private String title;
    private String description;
    private MongoInstructorInThesisTopic instructor;
    private Set<MongoCategory> categories = new HashSet<>();
    private Set<MongoLiteratureReference> literatureReferences = new HashSet<>();

    public MongoThesisTopic() {
    }

    public MongoThesisTopic(String title, String description, MongoInstructorInThesisTopic instructor, Set<MongoCategory> categories, Set<MongoLiteratureReference> references) {
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.categories = categories;
        this.literatureReferences = references;
    }
}
