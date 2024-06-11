package at.ac.univie.imse.backend.mariadb.repositories;

import at.ac.univie.imse.backend.mariadb.datamodel.ResearchGroup;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@Profile("mariadb")
@RepositoryRestResource
public interface GroupRepository extends PagingAndSortingRepository<ResearchGroup, Long>, CrudRepository<ResearchGroup, Long> {
    @RestResource(path = "name-exact")
    List<ResearchGroup> findByNameIgnoreCase(String name);
}
