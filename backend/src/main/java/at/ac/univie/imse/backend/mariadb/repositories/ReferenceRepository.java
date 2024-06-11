package at.ac.univie.imse.backend.mariadb.repositories;

import at.ac.univie.imse.backend.mariadb.datamodel.LiteratureReference;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

@Profile("mariadb")
public interface ReferenceRepository extends PagingAndSortingRepository<LiteratureReference, Long>, CrudRepository<LiteratureReference, Long> {
}
