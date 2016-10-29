package bookmarks.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author william
 */
@Entity
@Table(name = "sys_role")
public class SysRole implements Serializable, GrantedAuthority {
    private Long id;
    private String roleName;
    private String roleDesc;
    //springSecurity角色名ROLE_xxx
    private String roleSecurity;

    private List<SysRight> sysRights = new ArrayList<SysRight>();
    private List<SysUser> sysUsers = new ArrayList<SysUser>();
    private Integer roleFlag = 1;



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "role_name", nullable = false)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Column(name = "role_desc", nullable = false)
    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Column(name = "role_flag", nullable = false)
    public Integer getRoleFlag() {
        return roleFlag;
    }

    public void setRoleFlag(Integer roleFlag) {
        this.roleFlag = roleFlag;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "sys_role_right",
            joinColumns = { @JoinColumn(name = "role_id") },
            inverseJoinColumns = @JoinColumn(name = "right_id")
    )
    public List<SysRight> getSysRights() {
        return sysRights;
    }

    public void setSysRights(List<SysRight> sysRights) {
        this.sysRights = sysRights;
    }



    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "sys_user_role",
            joinColumns = { @JoinColumn(name = "role_id") },
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public List<SysUser> getSysUsers() {
        return sysUsers;
    }

    public void setSysUsers(List<SysUser> sysUsers) {
        this.sysUsers = sysUsers;
    }


    @Column(name = "role_security", nullable = false)
    public String getRoleSecurity() {
        return roleSecurity;
    }

    @Transient
    public String getAuthority() {
        return getRoleSecurity();
    }
    
    public void setRoleSecurity(String roleSecurity) {
        this.roleSecurity = roleSecurity;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysRole)) {
            return false;
        }

        final SysRole sysRole = (SysRole) o;

        return !(roleName != null ? !roleName.equals(sysRole.roleName) : sysRole.roleName != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (roleName != null ? roleName.hashCode() : 0);
    }

    public String toString() {
        return "";
    }



}
