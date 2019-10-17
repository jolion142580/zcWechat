package com.gdyiko.zcwx.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gdyiko.tool.po.GenericPo;

@Entity
@Table(name = "isPush")
public class IsPush extends GenericPo implements java.io.Serializable
{
	private static final long serialVersionUID = 6038986716307804545L;
	
	@Id
	@Column
	private String id;
	
	@Column
	private String yyno;
	
	@Column
	private Date pushTime;
	
	public String getYyno() {
		return yyno;
	}

	public void setYyno(String yyno) {
		this.yyno = yyno;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Override
	public String getCreattime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreattime(String creattime) {
		// TODO Auto-generated method stub
		
	}
}
