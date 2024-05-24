package at.ac.univie.imse.backend.mariadb.repositories;

import at.ac.univie.imse.backend.mariadb.datamodel.Category;
import at.ac.univie.imse.backend.mariadb.datamodel.LiteratureReference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReferenceRepository extends PagingAndSortingRepository<LiteratureReference, Long>, CrudRepository<LiteratureReference, Long> {
}
