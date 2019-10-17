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
@Table(name = "BusinessWeight")
public class BusinessWeight extends GenericPo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9124526084625803232L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column
	private String id;
	@Column
	private String businessName;
	@Column
	private String weight;
	@Column
	private String serverTypeId;
	@Column
	private String businessCode;
	@Column
	private String sortVal;
	
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}


	public String getServerTypeId() {
		return serverTypeId;
	}

	public void setServerTypeId(String serverTypeId) {
		this.serverTypeId = serverTypeId;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getSortVal() {
		return sortVal;
	}

	public void setSortVal(String sortVal) {
		this.sortVal = sortVal;
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
