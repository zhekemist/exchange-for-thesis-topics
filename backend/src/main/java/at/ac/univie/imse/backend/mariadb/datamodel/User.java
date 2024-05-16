package at.ac.univie.imse.backend.mariadb.datamodel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name = "user_type", columnDefinition = "ENUM('STUDENT','INSTRUCTOR')",
            insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String username;
    @Embedded
    private Name name;
    private String email;
    private String password;

    public User() {}

    public User(UserType userType, String username, Name name, String email, String password) {
        this.userType = userType;
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}