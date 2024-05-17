package at.ac.univie.imse.backend.mariadb.datamodel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
public class LiteratureReferenceId implements Serializable {
    private long referenceNumber;
    private long topic;

    public LiteratureReferenceId() {
    }
}
