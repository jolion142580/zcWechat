package com.gdyiko.zcwx.dao.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.gdyiko.zcwx.dao.StreetDao;
import com.gdyiko.zcwx.po.Street;
import com.gdyiko.tool.dao.impl.GenericDaoImpl;


@Repository("streetDao")
public class StreetDaoImpl extends GenericDaoImpl<Street, String> implements StreetDao {

	@Override
	public Class<Street> getEntityClass() {
		// TODO Auto-generated method stub
		return Street.class;
	}
	
	
/*	public static void main(String[]xxxx){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		StreetDao streetDao = (StreetDao)context.getBean("streetDao");
		Street street=streetDao.findById("2");
		//List<JmCydepartmentWorkerInfo> list = jmCydepartmentWorkerInfoService.getListByCydep("144108824432446765292",  0,  20);
		System.out.println(street.getName());
	}*/

}
