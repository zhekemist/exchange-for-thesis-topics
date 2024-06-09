package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
//@Document(collection = "instructors")
@Document
public class MongoInstructor {
    @Id
    private String userId;
    private String email;
    private String username;
    private String password;
    private MongoName name;
    private String contactInformation;
    private boolean isAdministrator;
    private MongoResearchGroup researchGroup;

    public MongoInstructor() {
    }

    public MongoInstructor(String email, String username, String password, MongoName name, String contactInformation, boolean isAdministrator, MongoResearchGroup researchGroup) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.contactInformation = contactInformation;
        this.isAdministrator = isAdministrator;
        this.researchGroup = researchGroup;
    }
}
