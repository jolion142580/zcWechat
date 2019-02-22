package com.gdyiko.base.po;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.gdyiko.tool.po.GenericPo;

/**
 * RolGeninf entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "menu_info")
public class MenuInfo extends GenericPo implements java.io.Serializable {

	/**
	 * 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -7586620673237669966L;
	/**
	 * 
	 */

	
	// Fields
	@Id
	private String id;

	@Column
	private String code;

	@Column
	private String name;

	@Column
	private String link;

	@Column
	private String icon;

	

	@Column
	private String type;

	@Column
	private String menuorder;

	@Column
	private String creator;
	
	@Column
	private String creattime;
	/*
	 *  父亲节点
	 * */
	
	@Cascade(value={CascadeType.SAVE_UPDATE})  
	@ManyToOne(fetch=FetchType.LAZY) 
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="parentid",referencedColumnName="id")
	@JsonIgnore
	private MenuInfo parent ;

	/*
	 * 子清单
	 * */
	
	@Cascade(value={CascadeType.SAVE_UPDATE,CascadeType.DELETE})  
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)  
	@JsonIgnore
	 private java.util.Set<MenuInfo> children; 
	
	
	
	@Cascade(value = { CascadeType.REFRESH })
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rights_role", inverseJoinColumns = @JoinColumn(name = "roleid", referencedColumnName = "id"),// 被维护端外键
	joinColumns = @JoinColumn(name = "menuid", referencedColumnName = "id"))
	private Set<RoleInfo> roleInfos = new HashSet<RoleInfo>();

	

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMenuorder() {
		return menuorder;
	}

	public void setMenuorder(String menuorder) {
		this.menuorder = menuorder;
	}



	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public MenuInfo getParent() {
		return parent;
	}

	public void setParent(MenuInfo parent) {
		this.parent = parent;
	}

	public java.util.Set<MenuInfo> getChildren() {
		return children;
	}

	public void setChildren(java.util.Set<MenuInfo> children) {
		this.children = children;
	}

	public Set<RoleInfo> getRoleInfos() {
		return roleInfos;
	}

	public void setRoleInfos(Set<RoleInfo> roleInfos) {
		this.roleInfos = roleInfos;
	}

	@Override
	public String getCreattime() {
		// TODO Auto-generated method stub
		return this.creattime;
	}

	@Override
	public void setCreattime(String creattime) {
		// TODO Auto-generated method stub
		 this.creattime = creattime;
	}
	
	

	
}