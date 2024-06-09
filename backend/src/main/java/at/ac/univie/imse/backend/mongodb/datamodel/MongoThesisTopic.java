package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

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
    @DBRef
    private MongoInstructor instructor;
    private Set<MongoCategory> categories = new HashSet<>();
    private Set<MongoLiteratureReference> references = new HashSet<>();

    public MongoThesisTopic() {
    }

    public MongoThesisTopic(String title, String description, MongoInstructor instructor, Set<MongoCategory> categories, Set<MongoLiteratureReference> references) {
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.categories = categories;
        this.references = references;
    }
}
