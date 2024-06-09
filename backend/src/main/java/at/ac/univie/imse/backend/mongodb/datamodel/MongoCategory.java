package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
//@Document(collection = "categories")
@Document
public class MongoCategory {
    @Id
    private String categoryId;
    private String name;
    private String shortDescription;  //im Dokument anders vll noch weg?
    private String superCategory;
    private Set<String> ancestorCategories = new HashSet<>();

    public MongoCategory() {
    }

    public MongoCategory(String name, String shortDescription, MongoCategory superCategory, Set<MongoCategory> subCategories) {
        this.name = name;
        this.shortDescription = shortDescription;

        if (superCategory != null)
            this.superCategory = superCategory.getName();

        for (MongoCategory subCategory : subCategories)
            this.ancestorCategories.add(subCategory.getName());
    }
}
