package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Document(collection = "thesisTopics")
public class ThesisTopic {
    @MongoId
    private String topicId;
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

    public ThesisTopic(at.ac.univie.imse.backend.mariadb.datamodel.ThesisTopic thesisTopic) {
        this.topicId = String.valueOf(thesisTopic.getTopicId());
        this.title = thesisTopic.getTitle();
        this.description = thesisTopic.getDescription();
        at.ac.univie.imse.backend.mariadb.datamodel.Instructor mariaInstructor = thesisTopic.getSupervisor();
        this.instructor = new Instructor(mariaInstructor.getUserId(), mariaInstructor.getName(), mariaInstructor.getGroup());
//        for (at.ac.univie.imse.backend.mariadb.datamodel.Category category : thesisTopic.getCategories()) {
//            categories.add(new Category(category));
//        }
//        for (Map.Entry<Long, at.ac.univie.imse.backend.mariadb.datamodel.LiteratureReference> entry : thesisTopic.getReferences().entrySet()) {
//            literatureReferences.add(new LiteratureReference(entry.getValue()));
//        }
    }
}
