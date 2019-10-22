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
@Table(name = "SendHist")
public class SendHist extends GenericPo implements java.io.Serializable{
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
	private String number;
	@Column
	private String idCardNum;
	@Column
	private String userName;
	@Column
	private String phone;
	@Column
	private String itemId;
	@Column
	private String npId;
	@Column
	private String bookType;
	@Column
	private String remark;
	@Column
	private String currentTiime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getIdCardNum() {
		return idCardNum;
	}
	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getNpId() {
		return npId;
	}
	public void setNpId(String npId) {
		this.npId = npId;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCurrentTiime() {
		return currentTiime;
	}
	public void setCurrentTiime(String currentTiime) {
		this.currentTiime = currentTiime;
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
