package com.gdyiko.base.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.gdyiko.tool.po.GenericPo;
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "jm_file_bsn_info")
public class JmFileBsnLink  extends GenericPo implements java.io.Serializable {
	
	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = -3034150405973000559L;
	@Id
	@Column
	private String id;
	@Column
	private String bsn_type;
	@Column
	private String bsn_id;
	@Column
	private String displayname;
	@Column
	private String remark;
	//@Column
	//private String file_id;
	@Column
	private String creator;
	@Column
	private String creattime;
	
	 @Cascade(value={CascadeType.SAVE_UPDATE})  
	 @ManyToOne(fetch=FetchType.EAGER) 
	 @JoinColumn(name="file_id",referencedColumnName="id")  
	 @JsonIgnore
	private JmFileInfo jmFileInfo;
	// Constructors
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBsn_type() {
		return bsn_type;
	}
	public void setBsn_type(String bsn_type) {
		this.bsn_type = bsn_type;
	}
	public String getBsn_id() {
		return bsn_id;
	}
	public void setBsn_id(String bsn_id) {
		this.bsn_id = bsn_id;
	}
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/*public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}*/
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String  getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	public JmFileInfo getJmFileInfo() {
		return jmFileInfo;
	}
	public void setJmFileInfo(JmFileInfo jmFileInfo) {
		this.jmFileInfo = jmFileInfo;
	}
	
	

}
