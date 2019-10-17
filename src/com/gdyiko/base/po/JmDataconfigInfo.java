package com.gdyiko.base.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.gdyiko.tool.po.GenericPo;

/**
 * JmDataconfigInfoId entity. @author MyEclipse Persistence Tools
 */

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "JM_DATACONFIG_INFO")
public class JmDataconfigInfo extends GenericPo implements java.io.Serializable {

	// Fields

	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = 937005738495738648L;
	@Id
	@Column
	private String id;
	@Column
	private String keystr;
	@Column
	private String valuestr;
	@Column
	private String remark;
	@Column
	private String describe;
	@Column
	private String code;
	@Column
	private String name;
	@Column
	private String creator;
	@Column
	private String creattime;

	
	/*
	 *  父亲节点
	 * */
	@Cascade(value={CascadeType.SAVE_UPDATE})  
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="parentid",referencedColumnName="id")
	@JsonIgnore
	private JmDataconfigInfo parent ;

	/*
	 * 子清单
	 * */
	@Cascade(value={CascadeType.SAVE_UPDATE,CascadeType.DELETE})  
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)  
	@JsonIgnore
	 private java.util.Set<JmDataconfigInfo> children; 

	// Property accessors

	public String getId() {
		System.out.println("check----------->"+this.id);
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKeystr() {
		return this.keystr;
	}

	public void setKeystr(String keystr) {
		this.keystr = keystr;
	}

	public String getValuestr() {
		return this.valuestr;
	}

	public void setValuestr(String valuestr) {
		this.valuestr = valuestr;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}



	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JmDataconfigInfo getParent() {
		return parent;
	}

	public void setParent(JmDataconfigInfo parent) {
		this.parent = parent;
	}

	public java.util.Set<JmDataconfigInfo> getChildren() {
		return children;
	}

	public void setChildren(java.util.Set<JmDataconfigInfo> children) {
		this.children = children;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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