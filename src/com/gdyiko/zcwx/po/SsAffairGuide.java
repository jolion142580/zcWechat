package com.gdyiko.zcwx.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gdyiko.tool.po.GenericPo;

/**
 * SsAffairGuide entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ss_affair_guide")
public class SsAffairGuide extends GenericPo implements java.io.Serializable {

	// Fields
	private String guideid;
	private String affairid;
	private String according;
	private String procedures;
	private String material;
	private String institution;
	private String accessorypath;
	private String condition;
	private String xrndomu;
	private String site;
	private String time;
	private String inquire;
	private String charge;
	private String chargegist;
	private String sepcialversion;
	private String affairname;
	private String isonline;
	private String iswrite;

	// Constructors

	/** default constructor */
	public SsAffairGuide() {
	}

	/** minimal constructor */
	public SsAffairGuide(String affairid) {
		this.affairid = affairid;
	}

	/** full constructor */
	public SsAffairGuide(String guideid,String affairid, String according, String procedures,
			String material, String institution, String accessorypath,
			String condition, String xrndomu, String site, String time,
			String inquire, String charge, String chargegist,
			String sepcialversion) {
		this.guideid = guideid;
		this.affairid = affairid;
		this.according = according;
		this.procedures = procedures;
		this.material = material;
		this.institution = institution;
		this.accessorypath = accessorypath;
		this.condition = condition;
		this.xrndomu = xrndomu;
		this.site = site;
		this.time = time;
		this.inquire = inquire;
		this.charge = charge;
		this.chargegist = chargegist;
		this.sepcialversion = sepcialversion;
	}
	

	// Property accessors
	@Id
	@Column(name = "guideid", unique = true, nullable = false, length = 50)
	public String getGuideid() {
		return this.guideid;
	}

	public void setGuideid(String guideid) {
		this.guideid = guideid;
	}
	
	@Column(name = "affairid")
	public String getAffairid() {
		return this.affairid;
	}

	public void setAffairid(String affairid) {
		this.affairid = affairid;
	}
	

	@Column(name = "according")
	public String getAccording() {
		return this.according==null?"":this.according.replaceAll("\r\n","<br/>").replaceAll("\r","</br>").replaceAll("\n","<br/>");
	}

	public void setAccording(String according) {
		this.according = according;
	}

	@Column(name = "procedures")
	public String getProcedures() {
		return this.procedures==null?"":this.procedures.replaceAll("\r\n","<br/>").replaceAll("\r","</br>").replaceAll("\n","<br/>");
	}

	public void setProcedures(String procedures) {
		this.procedures = procedures;
	}

	@Column(name = "material")
	public String getMaterial() {
		return this.material==null?"":this.material.replaceAll("\r\n","<br/>").replaceAll("\r","</br>").replaceAll("\n","<br/>");
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Column(name = "institution")
	public String getInstitution() {
		return this.institution==null?"":this.institution.replaceAll("\r\n","<br/>").replaceAll("\r","</br>").replaceAll("\n","<br/>");
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	@Column(name = "accessorypath", length = 4000)
	public String getAccessorypath() {
		return this.accessorypath;
	}

	public void setAccessorypath(String accessorypath) {
		this.accessorypath = accessorypath;
	}

	@Column(name = "condition", length = 4000)
	public String getCondition() {
		return this.condition==null?"":this.condition.replaceAll("\r\n","<br/>").replaceAll("\r","</br>").replaceAll("\n","<br/>");
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Column(name = "xrndomu", length = 4000)
	public String getXrndomu() {
		return this.xrndomu;
	}

	public void setXrndomu(String xrndomu) {
		this.xrndomu = xrndomu;
	}

	@Column(name = "site", length = 4000)
	public String getSite() {
		return this.site==null?"":this.site.replaceAll("\r\n","<br/>").replaceAll("\r","</br>").replaceAll("\n","<br/>");
	}

	public void setSite(String site) {
		this.site = site;
	}

	@Column(name = "time", length = 4000)
	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(name = "inquire", length = 4000)
	public String getInquire() {
		return this.inquire==null?"":this.inquire.replaceAll("\r\n","<br/>").replaceAll("\r","</br>").replaceAll("\n","<br/>");
	}

	public void setInquire(String inquire) {
		this.inquire = inquire;
	}

	@Column(name = "charge", length = 4000)
	public String getCharge() {
		return this.charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	@Column(name = "chargegist", length = 4000)
	public String getChargegist() {
		return this.chargegist;
	}

	public void setChargegist(String chargegist) {
		this.chargegist = chargegist;
	}

	@Column(name = "sepcialversion", length = 4000)
	public String getSepcialversion() {
		return this.sepcialversion==null?"":this.sepcialversion.replaceAll("\r\n","<br/>").replaceAll("\r","</br>").replaceAll("\n","<br/>");
	}

	public void setSepcialversion(String sepcialversion) {
		this.sepcialversion = sepcialversion;
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
/*	public String getAfterReplace(){
		return "正常属性".replaceAll("\r\n","<br/>").replaceAll("\r","</br>").replaceAll("\n","<br/>");
		}*/

	@Transient
	public String getAffairname() {
		return affairname;
	}

	public void setAffairname(String affairname) {
		this.affairname = affairname;
	}

	@Transient
	public String getIswrite() {
		return iswrite;
	}

	public void setIswrite(String iswrite) {
		this.iswrite = iswrite;
	}

	@Transient
	public String getIsonline() {
		return isonline;
	}

	public void setIsonline(String isonline) {
		this.isonline = isonline;
	}

	
	
}