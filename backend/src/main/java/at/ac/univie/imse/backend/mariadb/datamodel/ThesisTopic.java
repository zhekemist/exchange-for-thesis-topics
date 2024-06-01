package at.ac.univie.imse.backend.mariadb.datamodel;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
public class ThesisTopic implements Serializable {
    @Id
    @Column(name = "topic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long topicId;

    private String title;
    private String description;

    @ManyToMany
    @JoinTable(name = "belongs_to",
            joinColumns = {@JoinColumn(name = "topic_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "topic")
    @MapKey(name = "referenceNumber")
    private Map<Long, LiteratureReference> references = new HashMap<>();

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Instructor supervisor;

    public ThesisTopic() {
    }

    public ThesisTopic(String title, String description, Set<Category> categories, Map<Long, LiteratureReference> references, Instructor supervisor) {
        this.title = title;
        this.description = description;
        this.categories = categories;
        this.references = references;
        this.supervisor = supervisor;
    }
}
