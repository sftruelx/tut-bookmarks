package bookmarks.repository;


import bookmarks.entity.SysRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(readOnly = true)
public interface SysRightRepository extends JpaRepository<SysRight, Long>, JpaSpecificationExecutor {
}
