package com.gdyiko.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.gdyiko.base.dao.RolGeninfDao;
import com.gdyiko.base.po.RolGeninf;
import com.gdyiko.tool.dao.impl.MyBaseGenericDaoImpl;

@Repository("rolGeninfDao")
public class RolGeninfDaoImpl extends MyBaseGenericDaoImpl<RolGeninf, String> implements RolGeninfDao {

	@Override
	public Class<RolGeninf> getEntityClass() {
		// TODO Auto-generated method stub
		return RolGeninf.class;
	}

	

	
}
