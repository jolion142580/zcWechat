package com.gdyiko.base.po;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.h2.command.ddl.CreateAggregate;

import com.gdyiko.tool.po.GenericPo;
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "sys_msg_info")
public class SysMsgInfo extends GenericPo {
	
	//主键id
	@Id
	@Column
	private String msgid;
	//创建人
	@Column
	private String creater;
	
	//消息内容
	@Column
	private String message;
	
	//创建时间
	@Column
	private String creattime;
	
	//内部用户
	@Column
	private String msguser;
	
	//消息短信
	@Column
	private Boolean isMsn ;
	
	//是否已发送
	@Column
	private Boolean isSend;
	
	//已读取
	@Column
	private Boolean isRead;

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreattime() {
		return creattime;
	}

	public void setCreattime(String  creattime) {
		this.creattime = creattime;
	}

	public Boolean getIsMsn() {
		return isMsn;
	}

	public void setIsMsn(Boolean isMsn) {
		this.isMsn = isMsn;
	}

	public Boolean getIsSend() {
		return isSend;
	}

	public void setIsSend(Boolean isSend) {
		this.isSend = isSend;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.getMsgid();
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.setMsgid(id);
	}

	public String getMsguser() {
		return msguser;
	}

	public void setMsguser(String msguser) {
		this.msguser = msguser;
	}

	@Override
	public String getCreator() {
		// TODO Auto-generated method stub
		return this.getCreater();
	}

	@Override
	public void setCreator(String id) {
		// TODO Auto-generated method stub
		this.setCreater(id);
		
	}

	
	
}
