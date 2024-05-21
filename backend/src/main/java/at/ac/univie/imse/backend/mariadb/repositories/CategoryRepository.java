package at.ac.univie.imse.backend.mariadb.repositories;

import at.ac.univie.imse.backend.configuration.repodetection.ExposeViaRestIf;
import at.ac.univie.imse.backend.mariadb.datamodel.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

@ExposeViaRestIf("dbs.maria.rest.expose")
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
}
