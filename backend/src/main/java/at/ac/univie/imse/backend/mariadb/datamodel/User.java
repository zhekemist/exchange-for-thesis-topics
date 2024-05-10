package at.ac.univie.imse.backend.mariadb.datamodel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "user")
@RequiredArgsConstructor
@Getter
public class User {
    @Id
    private final long userId;
    private final String username;
    private final String name;
    private final String email;
    private final String password;
}