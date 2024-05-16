package at.ac.univie.imse.backend.mariadb.datamodel;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Instructor extends User {
    private String contactInformation;
    private boolean isAdministrator;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private ResearchGroup group;

    public Instructor() {}

    public Instructor(UserType userType, String username, Name name, String email, String password, String contactInformation, boolean isAdministrator, ResearchGroup group) {
        super(userType, username, name, email, password);
        this.contactInformation = contactInformation;
        this.isAdministrator = isAdministrator;
        this.group = group;
    }
}
