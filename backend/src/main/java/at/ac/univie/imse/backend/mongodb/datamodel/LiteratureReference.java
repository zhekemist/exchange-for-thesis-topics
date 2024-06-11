package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LiteratureReference {
    private String title;
    private String author;
    private int year;
    private String link;

    public LiteratureReference(String title, String author, int year, String link) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.link = link;
    }
}
