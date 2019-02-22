package com.gdyiko.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.gdyiko.base.dao.SysMsgInfoDao;
import com.gdyiko.base.po.SysMsgInfo;
import com.gdyiko.tool.dao.impl.MyBaseGenericDaoImpl;

@Repository("sysMsgInfoDao")
public class SysMsgInfoDaoImpl extends MyBaseGenericDaoImpl<SysMsgInfo , String> implements SysMsgInfoDao {

	@Override
	public Class<SysMsgInfo> getEntityClass() {
		// TODO Auto-generated method stub
		return SysMsgInfo.class;
	}
	
	
	
}
