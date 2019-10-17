package com.gdyiko.zcwx.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gdyiko.tool.po.GenericPo;

/**
 * SsAffairs entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ss_affairs")
public class SsAffairs extends GenericPo implements java.io.Serializable {

	// Fields

	private String affairid;
	private String departid;
	private String affairname;
	private String level;
	private String timelimitType;
	private String timelimit;
	private String sortid;
	private String resultForm;
	private String isnet;
	private String code;
	private String istodo;
	private String iswrite;
	private String config;
	private String isonline;
	private String condition;


	// Constructors

	/** default constructor */
	public SsAffairs() {
	}

	/** full constructor */
	public SsAffairs(String departid, String affairname, String level,
			String timelimitType, String timelimit, String sortid,
			String resultForm, String isnet, String code, String istodo,
			String iswrite,String config,String isonline) {
		this.departid = departid;
		this.affairname = affairname;
		this.level = level;
		this.timelimitType = timelimitType;
		this.timelimit = timelimit;
		this.sortid = sortid;
		this.resultForm = resultForm;
		this.isnet = isnet;
		this.code = code;
		this.istodo = istodo;
		this.iswrite = iswrite;
		this.config = config;
		this.isonline = isonline;
	}

	// Property accessors
	@Id
	@Column(name = "affairid", unique = true, nullable = false, length = 50)
	public String getAffairid() {
		return this.affairid;
	}

	public void setAffairid(String affairid) {
		this.affairid = affairid;
	}

	@Column(name = "departid", length = 500)
	public String getDepartid() {
		return this.departid;
	}

	public void setDepartid(String departid) {
		this.departid = departid;
	}

	@Column(name = "affairname", length = 2000)
	public String getAffairname() {
		return this.affairname;
	}

	public void setAffairname(String affairname) {
		this.affairname = affairname;
	}

	@Column(name = "level", length = 50)
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "timelimit_type", length = 50)
	public String getTimelimitType() {
		return this.timelimitType;
	}

	public void setTimelimitType(String timelimitType) {
		this.timelimitType = timelimitType;
	}

	@Column(name = "timelimit", length = 50)
	public String getTimelimit() {
		return this.timelimit;
	}

	public void setTimelimit(String timelimit) {
		this.timelimit = timelimit;
	}

	@Column(name = "sortid", length = 50)
	public String getSortid() {
		return this.sortid;
	}

	public void setSortid(String sortid) {
		this.sortid = sortid;
	}

	@Column(name = "result_form", length = 50)
	public String getResultForm() {
		return this.resultForm;
	}

	public void setResultForm(String resultForm) {
		this.resultForm = resultForm;
	}

	@Column(name = "isnet", length = 50)
	public String getIsnet() {
		return this.isnet;
	}

	public void setIsnet(String isnet) {
		this.isnet = isnet;
	}

	@Column(name = "code", length = 50)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "istodo", length = 50)
	public String getIstodo() {
		return this.istodo;
	}

	public void setIstodo(String istodo) {
		this.istodo = istodo;
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
	
	@Column(name = "isonline", length = 50)
	public String getIsonline() {
		return this.isonline;
	}

	public void setIsonline(String isonline) {
		this.isonline = isonline;
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

	@Transient
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}


	

}