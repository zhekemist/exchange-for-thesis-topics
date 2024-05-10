package at.ac.univie.imse.backend.mariadb.datamodel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table(name = "instructor")
@RequiredArgsConstructor
@Getter
public class Instructor {
    @Id
    private final long id;
    private final String contactInformation;
    private final boolean isAdministrator;
    private final ResearchGroup group;

    private final List<ThesisTopic> topics;
}
