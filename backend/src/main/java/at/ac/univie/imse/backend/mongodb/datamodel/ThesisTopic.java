package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Document(collection = "thesisTopics")
public class ThesisTopic {
    @Id
    private long topicId;
    private String title;
    private String description;
    private Instructor instructor;
    private Set<Category> categories = new HashSet<>();
    private Set<LiteratureReference> literatureReferences = new HashSet<>();

    public ThesisTopic(String title, String description, Instructor instructor, Set<Category> categories, Set<LiteratureReference> references) {
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.categories = categories;
        this.literatureReferences = references;
    }
}
