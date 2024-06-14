package at.ac.univie.imse.backend.mariadb.projections;

public interface PopularTopicsProjection {
    String getCategoryName();

    String getTopicTitles();

    Long getTopicCounter();
}
