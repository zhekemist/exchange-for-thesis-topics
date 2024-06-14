package at.ac.univie.imse.backend.mongodb.projections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PopularTopicsProjection {
    String categoryName;
    Long topicCounter;
    String topicTitles;
}
