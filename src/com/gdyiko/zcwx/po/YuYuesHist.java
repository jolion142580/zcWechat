package com.gdyiko.zcwx.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gdyiko.tool.po.GenericPo;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "YUYUESHIST")
public class YuYuesHist extends GenericPo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9124526084625803232L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column
	private String id;
	@Column
	private String name;
	@Column
	private String idcard;
	@Column
	private String phone;
	@Column
	private String no;
	@Column
	private String street;
	@Column
	private String state;
	@Column
	private String stype;
	@Column
	private String terminal;
	@Column
	private String ystime;
	@Column
	private String yetime;
	@Column
	private String ydate;
	@Column
	private String cdate;
	@Column
	private String qdate;
	@Column
	private String timecode;
	@Column
	private String openid;
	
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

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getYstime() {
		return ystime;
	}

	public void setYstime(String ystime) {
		this.ystime = ystime;
	}
	
	public String getYetime() {
		return yetime;
	}

	public void setYetime(String yetime) {
		this.yetime = yetime;
	}

	public String getYdate() {
		return ydate;
	}

	public void setYdate(String ydate) {
		this.ydate = ydate;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getQdate() {
		return qdate;
	}

	public void setQdate(String qdate) {
		this.qdate = qdate;
	}

	public String getTimecode() {
		return timecode;
	}

	public void setTimecode(String timecode) {
		this.timecode = timecode;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
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
