package com.gdyiko.zcwx.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gdyiko.tool.po.GenericPo;

/**
 * SsAffairObject entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ss_affair_object")
public class SsAffairObject extends GenericPo implements java.io.Serializable {

	// Fields

	private String objid;
	private String objindex;
	private String objname;
	private String affairid;
	private String iswrite;
	private String config;
	private String templateid;
	private String config1;
	private String templateid1;
	private String isshow;

	// Constructors

	/** default constructor */
	public SsAffairObject() {
	}

	/** full constructor */
	public SsAffairObject(String objid,String objindex, String objname, String affairid) {
		this.objid = objid;
		this.objindex = objindex;
		this.objname = objname;
		this.affairid = affairid;
	}

	// Property accessors
	@Id
	@Column(name = "objid", unique = true, nullable = false, length = 50)
	public String getObjid() {
		return this.objid;
	}

	public void setObjid(String objid) {
		this.objid = objid;
	}

	@Column(name = "objindex", length = 50)
	public String getObjindex() {
		return this.objindex;
	}

	public void setObjindex(String objindex) {
		this.objindex = objindex;
	}

	@Column(name = "objname", length = 1073741823)
	public String getObjname() {
		return this.objname;
	}

	public void setObjname(String objname) {
		this.objname = objname;
	}

	@Column(name = "affairid", length = 50)
	public String getAffairid() {
		return this.affairid;
	}

	public void setAffairid(String affairid) {
		this.affairid = affairid;
	}
	
	@Column(name = "iswrite", length = 50)
	public String getIswrite() {
		return this.iswrite;
	}

	public void setIswrite(String iswrite) {
		this.iswrite = iswrite;
	}

	@Column(name = "config", length = 1073741823)
	public String getConfig() {
		return this.config;
	}

	public void setConfig(String config) {
		this.config = config;
	}
	
	@Column(name = "templateid", length = 500)
	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}
	
	@Column(name = "config1", length = 1073741823)
	public String getConfig1() {
		return config1;
	}

	public void setConfig1(String config1) {
		this.config1 = config1;
	}

	@Column(name = "templateid1", length = 500)
	public String getTemplateid1() {
		return templateid1;
	}

	public void setTemplateid1(String templateid1) {
		this.templateid1 = templateid1;
	}

	@Column(name = "isshow", length = 50)
	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

	@Override
	@Transient
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transient
	public String getCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreator(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transient
	public String getCreattime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreattime(String creattime) {
		// TODO Auto-generated method stub
		
	}

}