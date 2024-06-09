package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MongoInstructorInThesisTopic {
    private String _id;
    private MongoName name;
    private MongoResearchGroup researchGroup;

    public MongoInstructorInThesisTopic() {
    }

    public MongoInstructorInThesisTopic(String _id, MongoName name, MongoResearchGroup researchGroup) {
        this._id = _id;
        this.name = name;
        this.researchGroup = researchGroup;
    }

    public MongoInstructorInThesisTopic(MongoInstructor instructor) {
        this._id = instructor.getUserId();
        this.name = instructor.getName();
        this.researchGroup = instructor.getResearchGroup();
    }
}
