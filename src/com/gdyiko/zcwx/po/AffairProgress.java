package com.gdyiko.zcwx.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gdyiko.tool.po.GenericPo;

public class AffairProgress extends GenericPo implements java.io.Serializable {

	// Fields

	private String currCode;
	private String affairName;
	private String affairCode;
	private String personName;
	private String personCardId;
	private String personAddress;
	private String personLiveAddress;
	private String isEnd;
	private String nextFlow;
	

	// Constructors

	/** default constructor */
	public AffairProgress() {
	}


	@Override
	@Transient
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
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

	@Override
	@Transient
	public String getCreattime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreattime(String creattime) {
		// TODO Auto-generated method stub
		
	}



	public String getCurrCode() {
		return currCode;
	}



	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}



	public String getAffairName() {
		return affairName;
	}



	public void setAffairName(String affairName) {
		this.affairName = affairName;
	}



	public String getAffairCode() {
		return affairCode;
	}



	public void setAffairCode(String affairCode) {
		this.affairCode = affairCode;
	}



	public String getPersonName() {
		return personName;
	}



	public void setPersonName(String personName) {
		this.personName = personName;
	}



	public String getPersonCardId() {
		return personCardId;
	}



	public void setPersonCardId(String personCardId) {
		this.personCardId = personCardId;
	}



	public String getPersonAddress() {
		return personAddress;
	}



	public void setPersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}



	public String getPersonLiveAddress() {
		return personLiveAddress;
	}



	public void setPersonLiveAddress(String personLiveAddress) {
		this.personLiveAddress = personLiveAddress;
	}



	public String getIsEnd() {
		return isEnd;
	}



	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
	}



	public String getNextFlow() {
		return nextFlow;
	}



	public void setNextFlow(String nextFlow) {
		this.nextFlow = nextFlow;
	}

}