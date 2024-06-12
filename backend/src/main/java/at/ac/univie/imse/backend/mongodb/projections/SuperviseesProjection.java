package at.ac.univie.imse.backend.mongodb.projections;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuperviseesProjection {
    String firstName;
    String lastName;
    Long matriculationNumber;
    String email;
    String title;
    String supervisorFirstName;
    String supervisorLastName;
}
