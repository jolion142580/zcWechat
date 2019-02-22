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
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.gdyiko.tool.po.GenericPo;

/**
 * RolGeninf entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "role_info")
public class RoleInfo  extends GenericPo implements java.io.Serializable {

	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = 3084804357966970088L;

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
	private String type;
    
    @Column
	private String rolelevel;
    
    @Column
   	private String description;
    
    @Column
	private String creator;
   
    @Column
	private String creattime;
    
    @Cascade(value={CascadeType.SAVE_UPDATE})  
    @ManyToMany( fetch = FetchType.LAZY) 
    @JoinTable(name="rights_role", inverseJoinColumns =  @JoinColumn (name="menuid",referencedColumnName="id"),//被维护端外键
            joinColumns =  @JoinColumn (name = "roleid",referencedColumnName="id"))
    private Set<MenuInfo> menuInfos = new HashSet<MenuInfo>();
    

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRolelevel() {
		return rolelevel;
	}

	public void setRolelevel(String rolelevel) {
		this.rolelevel = rolelevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Set<MenuInfo> getMenuInfos() {
		return menuInfos;
	}

	public void setMenuInfos(Set<MenuInfo> menuInfos) {
		this.menuInfos = menuInfos;
	}

	public String getCreattime() {
		return creattime;
	}

	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	
	
    
}