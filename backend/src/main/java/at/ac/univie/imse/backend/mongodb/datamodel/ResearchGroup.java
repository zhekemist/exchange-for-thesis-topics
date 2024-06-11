package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ResearchGroup {
    @Id
    private String groupId;
    private String name;
    private String researchProfile;

    public ResearchGroup() {
    }

    public ResearchGroup(String name, String researchProfile) {
        this.name = name;
        this.researchProfile = researchProfile;
    }
}
