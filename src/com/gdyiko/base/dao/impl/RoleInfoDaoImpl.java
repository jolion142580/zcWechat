package com.gdyiko.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.gdyiko.base.dao.RoleInfoDao;
import com.gdyiko.base.po.RoleInfo;
import com.gdyiko.tool.dao.impl.MyBaseGenericDaoImpl;

@Repository("roleInfoDao")
public class RoleInfoDaoImpl extends MyBaseGenericDaoImpl<RoleInfo, String> implements RoleInfoDao {

	@Override
	public Class<RoleInfo> getEntityClass() {
		// TODO Auto-generated method stub
		return RoleInfo.class;
	}

}
