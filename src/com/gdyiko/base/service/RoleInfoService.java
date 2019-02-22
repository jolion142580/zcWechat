package com.gdyiko.base.service;

import com.gdyiko.base.po.RoleInfo;
import com.gdyiko.tool.service.MyBaseGenericService;

public interface RoleInfoService extends MyBaseGenericService<RoleInfo, String> {
	
	public RoleInfo saveMenuInfos(String id,String menuids);

}
