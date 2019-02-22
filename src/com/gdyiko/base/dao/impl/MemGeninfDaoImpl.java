package com.gdyiko.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.gdyiko.base.dao.MemGeninfDao;
import com.gdyiko.base.po.MemGeninf;
import com.gdyiko.tool.dao.impl.MyBaseGenericDaoImpl;

@Repository("memGeninfDao")
public class MemGeninfDaoImpl extends MyBaseGenericDaoImpl<MemGeninf, String> implements MemGeninfDao {

	@Override
	public Class<MemGeninf> getEntityClass() {
		// TODO Auto-generated method stub
		return MemGeninf.class;
	}

	public void setNotRoleMemHql(String rolid ) {
		// TODO Auto-generated method stub
		String hql ="from MemGeninf where memid not in (select memid from RolMem where rolid='"+rolid+"') ";
		///String hql ="select MemGeninf from Rol_Mem";
		this.setHql(hql);
		///MemGeninf e = new MemGeninf();
		
	}

	
	
}
