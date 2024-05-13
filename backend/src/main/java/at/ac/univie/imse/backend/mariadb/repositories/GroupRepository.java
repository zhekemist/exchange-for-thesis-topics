package at.ac.univie.imse.backend.mariadb.repositories;

import at.ac.univie.imse.backend.configuration.repodetection.ExposeViaRestIf;
import at.ac.univie.imse.backend.mariadb.datamodel.ResearchGroup;
import org.springframework.data.repository.CrudRepository;

@ExposeViaRestIf("dbs.maria.rest.expose")
public interface GroupRepository extends CrudRepository<ResearchGroup, Long> {
}
