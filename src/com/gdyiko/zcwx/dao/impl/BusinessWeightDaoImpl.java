package com.gdyiko.zcwx.dao.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.gdyiko.zcwx.dao.BusinessWeightDao;
import com.gdyiko.zcwx.po.BusinessWeight;
import com.gdyiko.tool.dao.impl.GenericDaoImpl;


@Repository("businessWeightDao")
public class BusinessWeightDaoImpl extends GenericDaoImpl<BusinessWeight, String> implements BusinessWeightDao {

	@Override
	public Class<BusinessWeight> getEntityClass() {
		// TODO Auto-generated method stub
		return BusinessWeight.class;
	}

}
