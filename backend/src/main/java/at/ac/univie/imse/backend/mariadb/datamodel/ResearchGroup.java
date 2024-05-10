package at.ac.univie.imse.backend.mariadb.datamodel;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "research_group")
@RequiredArgsConstructor
@Getter
public class ResearchGroup {
    @Id private final long id;
    private final String name;
    private final String researchProfile;
}
