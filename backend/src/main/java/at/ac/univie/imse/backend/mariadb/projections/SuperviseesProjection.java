package at.ac.univie.imse.backend.mariadb.projections;

public interface SuperviseesProjection {
    String getFirstName();

    String getLastName();

    Long getMatriculationNumber();

    String getEmail();

    String getTitle();

    String getSupervisorFirstName();

    String getSupervisorLastName();
}
