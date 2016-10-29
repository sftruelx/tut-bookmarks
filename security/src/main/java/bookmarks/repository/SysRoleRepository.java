package bookmarks.repository;

import bookmarks.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liaoxiang on 2016/7/27.
 */

@Repository
@Transactional(readOnly = true)
public interface SysRoleRepository extends JpaRepository<SysRole, Long>, JpaSpecificationExecutor {
    SysRole findById(Long id);

    SysRole findByRoleNameAndIdNot(String roleName, Long id);

}
