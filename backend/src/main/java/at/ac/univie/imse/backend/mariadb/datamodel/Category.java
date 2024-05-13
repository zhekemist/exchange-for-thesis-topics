package at.ac.univie.imse.backend.mariadb.datamodel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {
    @Id
    private long categoryId;

    private String name;
    private String shortDescription;


    /*@OneToMany(mappedBy = "supercategoryId")
    private Set<Category> subCategories;

    @ManyToMany
    @JoinTable(name = "belongs_to", joinColumns = {@JoinColumn(name = "category_id")}, inverseJoinColumns = {@JoinColumn(name = "topic_id")})
    private Set<ThesisTopic> fittingTopics;*/
}
