package bookmarks.repository;

import bookmarks.entity.Teammate;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface TeammateRepository extends PagingAndSortingRepository<Teammate, Long>, JpaSpecificationExecutor {


}
