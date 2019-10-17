package com.gdyiko.base.po;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.gdyiko.tool.po.GenericPo;
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "jm_file_info")
public class JmFileInfo extends GenericPo implements java.io.Serializable  {
	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = -5444758746396970841L;
	@Id
	@Column
	private String id;
	@Column
	private String name;
	@Column
	private String localpath;
	@Column
	private String creattime;
	@Column
	private long filesize;
	@Column
	private String creator;
	
	@Cascade(value={CascadeType.SAVE_UPDATE,CascadeType.DELETE})  
	@OneToMany(mappedBy = "jmFileInfo", fetch = FetchType.LAZY) 
	@JsonIgnore
	 private java.util.Set<JmFileBsnLink> jmFileBsnLinks;
	
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocalpath() {
		return localpath;
	}
	public void setLocalpath(String localpath) {
		this.localpath = localpath;
	}
	public String getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public java.util.Set<JmFileBsnLink> getJmFileBsnLinks() {
		return jmFileBsnLinks;
	}
	public void setJmFileBsnLinks(java.util.Set<JmFileBsnLink> jmFileBsnLinks) {
		this.jmFileBsnLinks = jmFileBsnLinks;
	}

	

}
