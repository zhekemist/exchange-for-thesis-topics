package at.ac.univie.imse.backend.mariadb.datamodel;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table(name = "thesis_topic")
@RequiredArgsConstructor
@Getter
public class ThesisTopic {
    @Id private final long id;
    private final String title;
    private final String description;

    private final List<Category> categories;
    private final List<LiteratureReference> references;
}
