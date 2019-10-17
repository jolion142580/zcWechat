package com.gdyiko.zcwx.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.gdyiko.tool.po.GenericPo;
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "sms_info")
public class SmsInfo extends GenericPo implements java.io.Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	@Column
	private String id;
	@Column
	private String smsmobile;
	@Column
	private String ip;
	@Column
	private String code;
	@Column
	private String creattime;
	

	

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id=id;
	}
	
	public String getSmsmobile() {
		return smsmobile;
	}

	public void setSmsmobile(String smsmobile) {
		this.smsmobile = smsmobile;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
		return creattime;
	}

	@Override
	public void setCreattime(String creattime) {
		// TODO Auto-generated method stub
		this.creattime=creattime;
	}

	@Override
	public String toString() {
		return "SmsInfo [id=" + id + ", smsmobile=" + smsmobile + ", ip=" + ip
				+ ", code=" + code + ", creattime=" + creattime + "]";
	}

}
