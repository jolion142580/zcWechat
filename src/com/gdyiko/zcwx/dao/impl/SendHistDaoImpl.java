package com.gdyiko.zcwx.dao.impl;

import org.springframework.stereotype.Repository;

import com.gdyiko.zcwx.dao.SendHistDao;
import com.gdyiko.zcwx.po.SendHist;
import com.gdyiko.tool.dao.impl.GenericDaoImpl;
@Repository("sendHistDao")
public class SendHistDaoImpl extends GenericDaoImpl<SendHist, String> implements SendHistDao {

	@Override
	public Class<SendHist> getEntityClass() {
		// TODO Auto-generated method stub
		return SendHist.class;
	}

}
