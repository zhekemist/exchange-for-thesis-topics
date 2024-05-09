package at.ac.univie.imse.backend.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "person")
@AllArgsConstructor
@Getter
public class Person {
    @Id
    private final long id;
    private final String name;
}
