package com.gdyiko.base.dao;



import java.util.List;

import com.gdyiko.base.po.DepGeninf;
import com.gdyiko.tool.dao.MyBaseGenericDao;

public interface DepGeninfDao extends MyBaseGenericDao<DepGeninf, String> {

	List<DepGeninf> findNotNullByPage(DepGeninf entity, String nullColumnName, int firstResult, int maxResults);

	List<DepGeninf> findChildByPage(DepGeninf entity, String parentid, int firstResult, int maxResults);

	int findChildBySize(DepGeninf entity, String parentid);

	int findNotNullBySize(DepGeninf entity, String nullColumnName);
	
	public List<DepGeninf> getRootOj();

	public List<DepGeninf> getRootOj(String departname);

}
