package at.ac.univie.imse.backend.mariadb.repositories;

import at.ac.univie.imse.backend.configuration.repodetection.ExposeViaRestIf;
import at.ac.univie.imse.backend.mariadb.datamodel.ResearchGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

@ExposeViaRestIf("expose-mariadb")
public interface GroupRepository extends PagingAndSortingRepository<ResearchGroup, Long>, CrudRepository<ResearchGroup, Long> {
}
