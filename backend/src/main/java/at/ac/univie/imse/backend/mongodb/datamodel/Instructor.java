package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "instructors")
public class Instructor {
    @Id
    private long userId;
    private String email;
    private String username;
    private String password;
    private Name name;
    private String contactInformation;
    private boolean isAdministrator;
    private ResearchGroup researchGroup;

    public Instructor(String email, String username, String password, Name name, String contactInformation, boolean isAdministrator, ResearchGroup researchGroup) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.contactInformation = contactInformation;
        this.isAdministrator = isAdministrator;
        this.researchGroup = researchGroup;
    }
}
