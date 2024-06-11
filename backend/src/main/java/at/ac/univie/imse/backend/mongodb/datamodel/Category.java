package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashSet;
import java.util.Set;

@Data
@Document(collection = "categories")
public class Category {
    @MongoId
    private String categoryId;
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

    public Category(String id, String name, String shortDescription, String superCategory, Set<String> subCategories) {
        this.categoryId = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.superCategory = superCategory;
        this.ancestorCategories = subCategories;
    }

    public Category(at.ac.univie.imse.backend.mariadb.datamodel.Category category) {
        this.categoryId = String.valueOf(category.getCategoryId());
        this.name = category.getName();
        this.shortDescription = category.getShortDescription();
        this.superCategory = null;
        Set<String> ancestorCategories = null;
//            log.info(String.valueOf("number of subcategories:" + category.getSubCategories().size()));
//            for (Category ancestorCategory : category.getSubCategories())
//                ancestorCategories.add(ancestorCategory.getName());
    }
}
