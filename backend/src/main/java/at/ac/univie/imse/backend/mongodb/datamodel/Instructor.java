package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@Document(collection = "instructors")
public class Instructor {
    @MongoId
    private String userId;
    private String email;
    private String username;
    private String password;
    private Name name;
    private String contactInformation;
    private Boolean isAdministrator;
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

    public Instructor(at.ac.univie.imse.backend.mariadb.datamodel.Instructor instructor) {
        this.userId = String.valueOf(instructor.getUserId());
        this.email = instructor.getEmail();
        this.username = instructor.getUsername();
        this.password = instructor.getPassword();
        this.name = new Name(instructor.getName().firstName(), instructor.getName().lastName());
        this.contactInformation = instructor.getContactInformation();
        this.isAdministrator = instructor.isAdministrator();
        this.researchGroup = new ResearchGroup(instructor.getGroup().getGroupId(), instructor.getGroup().getName(), instructor.getGroup().getResearchProfile());
    }

    public Instructor(long userId, at.ac.univie.imse.backend.mariadb.datamodel.Name name, at.ac.univie.imse.backend.mariadb.datamodel.ResearchGroup researchGroup) {
        this.userId = String.valueOf(userId);
        this.name = new Name(name.firstName(), name.lastName());
        this.researchGroup = new ResearchGroup(researchGroup);
    }

    public Instructor(long userId, at.ac.univie.imse.backend.mariadb.datamodel.Name name) {
        this.userId = String.valueOf(String.valueOf(userId));
        this.name = new Name(name.firstName(), name.lastName());
    }
}
