package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document
public class ResearchGroup {
    @MongoId
    private String groupId;
    private String name;
    private String researchProfile;

    public ResearchGroup() {
    }

    public ResearchGroup(String name, String researchProfile) {
        this.name = name;
        this.researchProfile = researchProfile;
    }

    public ResearchGroup(long groupId, String name, String researchProfile) {
        this.groupId = String.valueOf(groupId);
        this.name = name;
        this.researchProfile = researchProfile;
    }

    public ResearchGroup(at.ac.univie.imse.backend.mariadb.datamodel.ResearchGroup researchGroup) {
        this.groupId = String.valueOf(researchGroup.getGroupId());
        this.name = researchGroup.getName();
        this.researchProfile = researchGroup.getResearchProfile();
    }
}
