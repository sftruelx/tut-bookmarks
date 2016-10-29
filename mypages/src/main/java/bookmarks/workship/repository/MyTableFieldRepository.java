package bookmarks.workship.repository;

import bookmarks.workship.entity.MyTableField;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  interface MyTableFieldRepository extends PagingAndSortingRepository<MyTableField, Long>, JpaSpecificationExecutor {

    List<MyTableField> findByTableName(String tableName);

}
