package com.gdyiko.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.gdyiko.base.dao.JmFileBsnLinkDao;
import com.gdyiko.base.po.JmFileBsnLink;
import com.gdyiko.tool.dao.impl.MyBaseGenericDaoImpl;

@Repository("jmFileBsnLinkDao")
public class JmFileBsnLinkDaoImpl extends MyBaseGenericDaoImpl<JmFileBsnLink, String> implements JmFileBsnLinkDao {

	@Override
	public Class<JmFileBsnLink> getEntityClass() {
		// TODO Auto-generated method stub
		return JmFileBsnLink.class;
	}
	
	
	
}
