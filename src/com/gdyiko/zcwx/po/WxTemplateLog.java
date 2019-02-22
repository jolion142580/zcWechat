package com.gdyiko.zcwx.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gdyiko.tool.po.GenericPo;

/**
 * WxTemplateLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_template_log")
public class WxTemplateLog extends GenericPo implements java.io.Serializable {

	// Fields

	private String id;
	private String openid;
	private String creattime;
	private String msgid;
	private String status;

	// Constructors

	/** default constructor */
	public WxTemplateLog() {
	}

	/** full constructor */
	public WxTemplateLog(String openid, String creattime, String msgid,
			String status) {
		this.openid = openid;
		this.creattime = creattime;
		this.msgid = msgid;
		this.status = status;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "openid", length = 500)
	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name = "creattime", length = 500)
	public String getCreattime() {
		return this.creattime;
	}

	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}

	@Column(name = "msgid", length = 500)
	public String getMsgid() {
		return this.msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	@Column(name = "status", length = 500)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

}