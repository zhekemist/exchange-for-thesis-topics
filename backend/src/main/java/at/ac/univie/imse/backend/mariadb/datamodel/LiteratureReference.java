package at.ac.univie.imse.backend.mariadb.datamodel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LiteratureReference {
    @Id
    private long referenceNumber;

    private int year;
    private String title;
    private String link;
    private String author;
}
