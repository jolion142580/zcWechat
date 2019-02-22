package com.gdyiko.base.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gdyiko.tool.po.GenericPo;

@Entity
@Table(name = "rol_mem")
public class RolMem extends GenericPo implements java.io.Serializable {
	@Id
	@Column
	private String rolid;
	@Id
	@Column
	private String memid;
	
	@Column
	private String creattime;
	
	public String getRolid() {
		return rolid;
	}

	public void setRolid(String rolid) {
		this.rolid = rolid;
	}

	public String getMemid() {
		return memid;
	}

	public void setMemid(String memid) {
		this.memid = memid;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.getRolid()+"-"+this.getMemid();
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

	
	public String getCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setCreator(String id) {
		// TODO Auto-generated method stub
		
	}

	public String getCreattime() {
		return creattime;
	}

	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}

	
}
