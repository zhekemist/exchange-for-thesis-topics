package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MongoLiteratureReference {
    private String title;
    private String author;
    private int year;
    private String link;

    public MongoLiteratureReference() {
    }

    public MongoLiteratureReference(String title, String author, int year, String link) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.link = link;
    }
}
