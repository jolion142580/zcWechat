package com.gdyiko.zcwx.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.gdyiko.tool.po.GenericPo;
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "affairHis")
public class AffairHis extends GenericPo implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 885574310812899951L;
	
	
	@Id
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	@Column
	private String id;
	@Column
	private String openid;
	@Column
	private String affairid;
	@Column
	private String affairname;
	@Column
	private String creattime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getAffairid() {
		return affairid;
	}
	public void setAffairid(String affairid) {
		this.affairid = affairid;
	}
	public String getAffairname() {
		return affairname;
	}
	public void setAffairname(String affairname) {
		this.affairname = affairname;
	}
	public String getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	@Override
	public String getCreator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setCreator(String id) {
		// TODO Auto-generated method stub
		
	}

	
	

}
