package com.gdyiko.base.dao;

import com.gdyiko.base.po.MemGeninf;
import com.gdyiko.tool.dao.MyBaseGenericDao;

public interface MemGeninfDao extends MyBaseGenericDao<MemGeninf, String> {
	
	public void setNotRoleMemHql(String rolid);

}
