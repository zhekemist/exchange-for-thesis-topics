package at.ac.univie.imse.backend.mariadb.datamodel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class LiteratureReferenceId implements Serializable {
    private long referenceNumber;
    private long topic;

    public LiteratureReferenceId() {
    }
}
