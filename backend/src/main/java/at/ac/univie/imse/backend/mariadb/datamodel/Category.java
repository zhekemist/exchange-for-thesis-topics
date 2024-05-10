package at.ac.univie.imse.backend.mariadb.datamodel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "category")
@RequiredArgsConstructor
@Getter
public class Category {
    @Id private final long categoryId;
    private final String name;
    private final String shortDescription;
}
