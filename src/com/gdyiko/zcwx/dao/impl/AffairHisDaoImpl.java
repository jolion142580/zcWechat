package com.gdyiko.zcwx.dao.impl;

import org.springframework.stereotype.Repository;

import com.gdyiko.zcwx.dao.AffairHisDao;
import com.gdyiko.zcwx.po.AffairHis;
import com.gdyiko.tool.dao.impl.GenericDaoImpl;

@Repository("AffairHisDao")
public class AffairHisDaoImpl extends GenericDaoImpl<AffairHis, String> implements AffairHisDao {
	@Override
	public Class<AffairHis> getEntityClass() {
		// TODO Auto-generated method stub
		return AffairHis.class;
	}
}
