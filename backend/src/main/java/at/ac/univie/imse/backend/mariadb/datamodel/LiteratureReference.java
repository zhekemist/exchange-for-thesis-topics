package at.ac.univie.imse.backend.mariadb.datamodel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LiteratureReference {
    @Id
    @Column(name = "reference_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long referenceNumber;

    private int year;
    private String title;
    private String link;
    private String author;
}
