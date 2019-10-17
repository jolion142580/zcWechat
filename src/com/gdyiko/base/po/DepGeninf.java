package com.gdyiko.base.po;




import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.gdyiko.tool.po.GenericPo;


/**
 * DepGeninf entity. @author MyEclipse Persistence Tools
 */

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "dep_geninf")
public class DepGeninf extends GenericPo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
    
	/**
	 * 
	 */
	
	public static String DEPGENINF="DEP";
	/**
	 * 
	 */
    @Id
	private String depid;
    
   // @Column
	//private String parentid;
    
    @Column
	private String managerid;
    
    @Column
	private String name;
    
    @Column
	private String synopsis;
    
    @Column
	private String respon;
    
    @Column
	private String siblingorder;
    
    @Column
	private String address;
    
    @Column
	private String officephone;
    
    @Column
	private String fax;
    
    @Column
	private String postno;
    
    @Column(name="id")
    private String serialId;
    
    @Column
	private String creator;
    @Column
	private String creattime;
    @Column
    private String orderNum;
	
    //同类树形 
    @Cascade(value={CascadeType.REFRESH})  
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="parentid",referencedColumnName="depid")  
	private   DepGeninf parent;
    
    @Cascade(value={CascadeType.DELETE})  
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)  
    @OrderBy("postno ASC")
	 private java.util.Set<DepGeninf> children;
    
    @Cascade(value={CascadeType.DELETE})  
    @OneToMany(mappedBy="depGeninf", fetch = FetchType.LAZY)
    private Set<RolGeninf> rolGeninf;
    

	/** default constructor */
	public DepGeninf() {
	}

	/** minimal constructor */
	public DepGeninf(String depid) {
		this.depid = depid;
	}

	

	// Property accessors

	public String getDepid() {
		return this.depid;
	}

	public void setDepid(String depid) {
		this.depid = depid;
	}

	/*public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	} */

	public String getManagerid() {
		return this.managerid;
	}

	public void setManagerid(String managerid) {
		this.managerid = managerid;
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

	public String getRespon() {
		return this.respon;
	}

	public void setRespon(String respon) {
		this.respon = respon;
	}

	public String getSiblingorder() {
		return this.siblingorder;
	}

	public void setSiblingorder(String siblingorder) {
		this.siblingorder = siblingorder;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOfficephone() {
		return this.officephone;
	}

	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPostno() {
		return this.postno;
	}

	public void setPostno(String postno) {
		this.postno = postno;
	}

	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public DepGeninf getParent() {
		return parent;
	}

	public void setParent(DepGeninf parent) {
		this.parent = parent;
	}

	public java.util.Set<DepGeninf> getChildren() {
		return children;
	}

	public void setChildren(java.util.Set<DepGeninf> children) {
		this.children = children;
	}

	public Set<RolGeninf> getRolGeninf() {
		return rolGeninf;
	}

	public void setRolGeninf(Set<RolGeninf> rolGeninf) {
		this.rolGeninf = rolGeninf;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return  this.getDepid();
		 
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.setDepid(id);
		
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
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