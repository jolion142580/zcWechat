package com.gdyiko.base.dao;


import java.util.List;

import com.gdyiko.base.po.MenuInfo;
import com.gdyiko.tool.dao.MyBaseGenericDao;

public interface MenuInfoDao extends MyBaseGenericDao<MenuInfo, String> {
	
	List<MenuInfo> findNotNullByPage(MenuInfo entity, String nullColumnName, int firstResult, int maxResults);

	List<MenuInfo> findChildByPage(MenuInfo entity, String parentid, int firstResult, int maxResults);

	int findChildBySize(MenuInfo entity, String parentid);

	int findNotNullBySize(MenuInfo entity, String nullColumnName);
	
	public List<MenuInfo> getRootOj();

	public List<MenuInfo> getRootOj(String departname);

}
