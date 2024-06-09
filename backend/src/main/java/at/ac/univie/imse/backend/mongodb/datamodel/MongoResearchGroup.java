package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@Document
public class MongoResearchGroup {
    @Id
    private String groupId;
    private String name;
    private String researchProfile;

    public MongoResearchGroup() {
    }

    public MongoResearchGroup(String name, String researchProfile) {
        this.name = name;
        this.researchProfile = researchProfile;
    }
}
