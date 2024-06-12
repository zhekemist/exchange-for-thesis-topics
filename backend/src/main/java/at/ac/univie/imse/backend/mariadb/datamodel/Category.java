package at.ac.univie.imse.backend.mariadb.datamodel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;

    private String name;
    private String shortDescription;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "supercategory_id")
    private Set<Category> subCategories = new HashSet<>();

    public Category() {
    }

    public Category(String name, String shortDescription, Set<Category> subCategories) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.subCategories = subCategories;
    }
}
