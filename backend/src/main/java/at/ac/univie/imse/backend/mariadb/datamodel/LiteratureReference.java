package at.ac.univie.imse.backend.mariadb.datamodel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "literature_reference")
@RequiredArgsConstructor
@Getter
public class LiteratureReference {
    @Id private final long referenceNumber;
    private final int year;
    private final String title;
    private final String link;
    private final String author;
}
