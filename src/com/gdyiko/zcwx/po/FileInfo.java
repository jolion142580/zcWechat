package com.gdyiko.zcwx.po;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gdyiko.tool.po.GenericPo;

/**
 * FileInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "file_info")
public class FileInfo extends GenericPo implements java.io.Serializable {

	// Fields

	private String id;
	private String affairid;
	private String materialid;
	private String onlineApplyId;
	private String filename;
	private String localpath;
	private String remark;
	private String openid;
	private String mediaId;
	private String creattime;

	// Constructors

	/** default constructor */
	public FileInfo() {
	}

	/** full constructor */
	public FileInfo(String affairid,String materialid,String onlineApplyId,String filename, String localpath,
			String remark, String openid,String mediaId, String creattime) {
		this.affairid = affairid;
		this.materialid = materialid;
		this.onlineApplyId = onlineApplyId;
		this.filename = filename;
		this.localpath = localpath;
		this.remark = remark;
		this.openid = openid;
		this.mediaId = mediaId;
		this.creattime = creattime;
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

	@Column(name = "affairid", length = 500)
	public String getAffairid() {
		return affairid;
	}

	public void setAffairid(String affairid) {
		this.affairid = affairid;
	}

	@Column(name = "materialid", length = 500)
	public String getMaterialid() {
		return materialid;
	}

	public void setMaterialid(String materialid) {
		this.materialid = materialid;
	}
	
	@Column(name = "onlineApplyId", length = 500)
	public String getOnlineApplyId() {
		return onlineApplyId;
	}

	public void setOnlineApplyId(String onlineApplyId) {
		this.onlineApplyId = onlineApplyId;
	}

	@Column(name = "filename", length = 500)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "localpath", length = 500)
	public String getLocalpath() {
		return this.localpath;
	}

	public void setLocalpath(String localpath) {
		this.localpath = localpath;
	}


	@Column(name = "remark", length = 1073741823)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "openid", length = 500)
	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Column(name = "creattime", length = 23)
	public String getCreattime() {
		return this.creattime;
	}

	@Override
	public void setCreattime(String creattime) {
		this.creattime=creattime;
		
	}
	
	@Override
	@Transient
	public String getCreator() {
		return null;
	}

	@Override
	public void setCreator(String id) {
		
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	

	

}