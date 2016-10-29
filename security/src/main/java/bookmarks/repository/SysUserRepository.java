package bookmarks.repository;

import bookmarks.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liaoxiang on 2016/7/27.
 */

@Repository
@Transactional(readOnly = true)
public interface SysUserRepository extends JpaRepository<SysUser, Long>, JpaSpecificationExecutor {
    SysUser findById(Long id);

    SysUser findByUsrName(String usrName);

    SysUser findByName(String usrName);

    SysUser findByUsrNameAndUsrFlag(String usrName, String flag);
}
