package com.gdyiko.base.service;

import java.util.List;

import com.gdyiko.base.po.MemGeninf;
import com.gdyiko.tool.service.MyBaseGenericService;

public interface MemGeninfService extends MyBaseGenericService<MemGeninf, String> {
	

	public void setNotRoleMemHql(String rolid);
	
	public List<MemGeninf> findByPageNotRoleMem(int page, int size,String rolid);
	
	public void saveRoleInfos(String id,String roleinfoids);
	
	public String getDepartName(String memid);
}
