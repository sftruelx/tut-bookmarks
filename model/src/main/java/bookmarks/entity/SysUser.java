package bookmarks.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author william
 */

@Entity
@Table(name = "sys_user")
public class SysUser  implements Serializable, UserDetails  {

    private Long id;
    private String usrName;
    private String usrPassword;
    private Integer usrType;
    private String usrDepartment;
    private String usrFlag ;
    private String name;
    private String gender;
    private String usrUrl;
    private String usrPhone;
    private String usrEmail;
    private String[] roles;
    private Date createTime;

    private SysRole sysRole;
    private List<SysRole> sysRoles = new ArrayList<SysRole>();
    private boolean enabled = Boolean.TRUE.booleanValue();
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @OneToOne
    @JoinColumn(name = "usr_role_id", insertable = true, unique = true)
    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }
    @Column(name = "usr_name", nullable = false)
    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }
    @Column(name = "usr_password", nullable = true)
    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    @Transient
    public String getUsername() {
        return usrName;
    }

    public void setUsername(String usrName) {
        this.usrName = usrName;
    }

    @Transient
    public String getPassword() {
        return usrPassword;
    }

    public void setPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    @Column(name = "usr_type", nullable = true)
    public Integer getUsrType() {
        return usrType;
    }

    public void setUsrType(Integer usrType) {
        this.usrType = usrType;
    }

    @Column(name = "usr_department", nullable = true)
    public String getUsrDepartment() {
        return usrDepartment;
    }

    public void setUsrDepartment(String usrDepartment) {
        this.usrDepartment = usrDepartment;
    }

    @Column(name = "usr_flag", nullable = false)
    public String getUsrFlag() {
        return usrFlag;
    }

    public void setUsrFlag(String usrFlag) {
        this.usrFlag = usrFlag;
    }
    @Column(name = "usr_url")
    public String getUsrUrl() {
        return usrUrl;
    }

    public void setUsrUrl(String usrUrl) {
        this.usrUrl = usrUrl;
    }
    @Column(name = "usr_phone")
    public String getUsrPhone() {
        return usrPhone;
    }

    public void setUsrPhone(String usrPhone) {
        this.usrPhone = usrPhone;
    }
    @Column(name = "usr_email")
    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }
    @Column(name = "create_time")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @OneToOne
//    @JoinColumn(name = "gender", insertable = true)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Transient
    public String[] getRoles() {
        String[] roleIds ;
        if(roles != null) {
            return roles;
        }else{
            roleIds = new String[sysRoles.size()];
            int i = 0;
            for(SysRole role : sysRoles){
                roleIds[i++] = role.getId().toString();
            }
            return roleIds;
        }
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }




    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "sys_user_role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    public List<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }



    @Transient
    public boolean isEnabled() {
        return Boolean.TRUE;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Transient
    public boolean isAccountExpired() {
        return accountExpired;
    }
    @Transient
    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }
    @Transient
    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    /**
     * @return GrantedAuthority[] an array of roles.
     * @see UserDetails#getAuthorities()
     */

    @Transient
    @JsonIgnore // needed for UserApiITest in appfuse-ws archetype
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new LinkedHashSet<GrantedAuthority>();
        for(SysRole role : sysRoles) {
            authorities.add(role);
        }
        return authorities;
    }

    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }


    public void setAccountNonLocked(boolean accountLocked) {

    }

    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }
    public void setAccountNonExpired(boolean accountExpired) {

    }


    public void setAccountExpired(boolean accountExpired) {

    }

    /**
     * @see UserDetails#isCredentialsNonExpired()
     * @return true if credentials haven't expired
     */
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysUser)) {
            return false;
        }

        final SysUser sysUser = (SysUser) o;

        return !(usrName != null ? !usrName.equals(sysUser.usrName) : sysUser.usrName != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (usrName != null ? usrName.hashCode() : 0);
    }

    public String toString(){

        return "";
    }

}
