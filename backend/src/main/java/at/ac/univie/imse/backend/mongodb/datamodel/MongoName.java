package at.ac.univie.imse.backend.mongodb.datamodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MongoName {
    private String firstName;
    private String lastName;

    public MongoName() {
    }

    public MongoName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
