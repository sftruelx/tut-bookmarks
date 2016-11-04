package bookmarks;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract  class BaseObject implements Serializable {

    @CreatedBy
    private String createdByUser;

    @CreatedDate
    private Date creationTime;

    @LastModifiedBy
    private String modifiedByUser;

    @LastModifiedDate
    private Date modificationTime;

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getModifiedByUser() {
        return modifiedByUser;
    }

    public void setModifiedByUser(String modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }



  /*
    @CreatedDate
    protected Date createdDate;
    @CreatedBy
    protected SysUser createdBy;
    @LastModifiedDate
    protected Date lastModifiedDate;
    @LastModifiedBy
    private SysUser lastModifiedBy;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public SysUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(SysUser createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public SysUser getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(SysUser lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }*/

    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
