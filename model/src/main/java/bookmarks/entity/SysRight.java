package bookmarks.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_right")
public class SysRight  implements Serializable {

	private Long id;

	private String rightText;

	private String rightUrl;

	private Long parentId;

	private SysRight parent;

	private String menuCode;

	private String rightDesc;

	private String menuIcon;

	private Integer hasChild;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "right_text",nullable = false, unique = true)
	public String getRightText() {
		return rightText;
	}

	public void setRightText(String rightText) {
		this.rightText = rightText;
	}

	@Column(name = "right_url",nullable = false, unique = true)
	public String getRightUrl() {
		return rightUrl;
	}

	public void setRightUrl(String rightUrl) {
		this.rightUrl = rightUrl;
	}

	@Column(name = "parent_id")
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "menu_code")
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	@Column(name = "right_desc")
	public String getRightDesc() {
		return rightDesc;
	}

	public void setRightDesc(String rightDesc) {
		this.rightDesc = rightDesc;
	}

	@Column(name = "menu_icon")
	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	@Column(name = "has_child")
	public Integer getHasChild() {
		return hasChild;
	}

	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof SysRight)) {
			return false;
		}

		final SysRight sysRight = (SysRight) o;

		return !(rightText != null ? !rightText.equals(sysRight.rightText) : sysRight.rightText != null);

	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return (rightText != null ? rightText.hashCode() : 0);
	}

	public String toString() {
		return "";
	}
}
