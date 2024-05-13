package at.ac.univie.imse.backend.mariadb.datamodel;

import jakarta.persistence.Embeddable;

@Embeddable
public record Name(String firstName, String lastName) {
}
