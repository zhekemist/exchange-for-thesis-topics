package at.ac.univie.imse.backend.mariadb.datamodel;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ResearchGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long groupId;

    private String name;
    private String researchProfile;

    public ResearchGroup() {}

    public ResearchGroup(String name, String researchProfile) {
        this.name = name;
        this.researchProfile = researchProfile;
    }
}
