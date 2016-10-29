package bookmarks.workship.repository;

import bookmarks.workship.entity.MyTable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface MyTableRepository extends PagingAndSortingRepository<MyTable, Long>, JpaSpecificationExecutor {


}
