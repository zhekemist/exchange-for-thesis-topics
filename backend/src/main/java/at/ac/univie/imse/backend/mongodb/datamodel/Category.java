package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document(collection = "categories")
public class Category {
    @Id
    private long categoryId;
    private String name;
    private String shortDescription;
    private String superCategory;
    private Set<String> ancestorCategories = new HashSet<>();

    public Category(String name, String shortDescription, Category superCategory, Set<Category> subCategories) {
        this.name = name;
        this.shortDescription = shortDescription;

        if (superCategory != null)
            this.superCategory = superCategory.getName();

        for (Category subCategory : subCategories)
            this.ancestorCategories.add(subCategory.getName());
    }
}
