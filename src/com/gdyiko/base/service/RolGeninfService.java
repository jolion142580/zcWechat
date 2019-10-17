package com.gdyiko.base.service;

import com.gdyiko.base.po.RolGeninf;
import com.gdyiko.tool.service.MyBaseGenericService;

public interface RolGeninfService extends MyBaseGenericService<RolGeninf, String> {
	
	
	public void  delMemgeninfsById(String rolid,String memids);
}
