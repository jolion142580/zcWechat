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
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.gdyiko.tool.po.GenericPo;

/**
 * RolGeninf entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rol_geninf")
public class RolGeninf extends GenericPo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 751426857384530728L;
	
    public static final String ROL="ROL";
	// Fields
    @Id
	private String rolid;
    
    @Column
	private String name;
    
    @Column(name="id")
	private String serialId;
    
    @Column
	private String synopsis;
    
    @Column
	private String siblingorder;
    
    @Column(name = "creator")
	private String creator;  
    
    @Column
	private String creattime;

 // Constructors
    @Cascade(value={CascadeType.PERSIST})  
    @ManyToMany( fetch = FetchType.LAZY) 
    @JoinTable(name="rol_mem", inverseJoinColumns =  @JoinColumn (name="memid",referencedColumnName="memid"),//被维护端外键
            joinColumns =  @JoinColumn (name = "rolid",referencedColumnName="rolid"))
    private Set<MemGeninf> memGeninfs = new HashSet<MemGeninf>();
	// Constructors

    @Cascade(value={CascadeType.REFRESH})  
   	@ManyToOne(fetch=FetchType.EAGER) 
   	@JoinColumn(name="depid",referencedColumnName="depid")  
   	private   DepGeninf depGeninf;
    
	/** default constructor */
	public RolGeninf() {
	}

	/** minimal constructor */
	public RolGeninf(String rolid) {
		this.rolid = rolid;
	}

	// Property accessors
	public String getRolid() {
		return this.rolid;
	}

	public void setRolid(String rolid) {
		this.rolid = rolid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSynopsis() {
		return this.synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getSiblingorder() {
		return this.siblingorder;
	}

	public void setSiblingorder(String siblingorder) {
		this.siblingorder = siblingorder;
	}

	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public Set<MemGeninf> getMemGeninfs() {
		return memGeninfs;
	}

	public void setMemGeninfs(Set<MemGeninf> memGeninfs) {
		this.memGeninfs = memGeninfs;
	}

	public DepGeninf getDepGeninf() {
		return depGeninf;
	}

	public void setDepGeninf(DepGeninf depGeninf) {
		this.depGeninf = depGeninf;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.getRolid();
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.setRolid(id);
	}

	public String getCreattime() {
		return creattime;
	}

	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}

	

}