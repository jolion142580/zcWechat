package com.gdyiko.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.gdyiko.base.dao.JmFileInfoDao;
import com.gdyiko.base.po.JmFileInfo;
import com.gdyiko.tool.dao.impl.MyBaseGenericDaoImpl;

@Repository("jmFileInfoDao")
public class JmFileInfoDaoImpl extends MyBaseGenericDaoImpl<JmFileInfo, String> implements JmFileInfoDao {

	@Override
	public Class<JmFileInfo> getEntityClass() {
		// TODO Auto-generated method stub
		return JmFileInfo.class;
	}
	
	
	
}
