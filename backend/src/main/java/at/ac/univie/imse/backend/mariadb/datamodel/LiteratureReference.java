package at.ac.univie.imse.backend.mariadb.datamodel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(LiteratureReferenceId.class)
public class LiteratureReference {
    @Id
    @Column(name = "reference_number")
    private long referenceNumber;

    @ManyToOne
    @Id
    @JoinColumn(name = "topic_id", referencedColumnName = "topic_id")
    private ThesisTopic topic;

    private int year;
    private String title;
    private String link;
    private String author;

    public LiteratureReference() {
    }

    public LiteratureReference(int year, String title, String link, String author) {
        this.year = year;
        this.title = title;
        this.link = link;
        this.author = author;
    }
}
