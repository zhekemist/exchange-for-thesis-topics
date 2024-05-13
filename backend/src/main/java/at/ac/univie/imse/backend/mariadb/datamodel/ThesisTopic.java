package at.ac.univie.imse.backend.mariadb.datamodel;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ThesisTopic {
    @Id
    private long topicId;

    private String title;
    private String description;

    /*@ManyToMany
    @JoinTable(name = "belongs_to", joinColumns = {@JoinColumn(name = "topic_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categories;

    @OneToMany
    private Set<LiteratureReference> references;*/
}
