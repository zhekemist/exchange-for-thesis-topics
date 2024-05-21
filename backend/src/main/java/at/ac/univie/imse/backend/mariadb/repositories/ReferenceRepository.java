package at.ac.univie.imse.backend.mariadb.repositories;

import at.ac.univie.imse.backend.mariadb.datamodel.LiteratureReference;
import org.springframework.data.repository.CrudRepository;

public interface ReferenceRepository extends CrudRepository<LiteratureReference, Long> {
}
