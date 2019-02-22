package com.gdyiko.zcwx.dao.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.gdyiko.zcwx.dao.ServerTypeDao;
import com.gdyiko.zcwx.po.ServerType;
import com.gdyiko.tool.dao.impl.GenericDaoImpl;


@Repository("serverTypeDao")
public class ServerTypeDaoImpl extends GenericDaoImpl<ServerType, String> implements ServerTypeDao {

	@Override
	public Class<ServerType> getEntityClass() {
		// TODO Auto-generated method stub
		return ServerType.class;
	}
	
	
/*	public static void main(String[]xxxx){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ServerTypeDao serverTypeDao = (ServerTypeDao)context.getBean("serverTypeDao");
		ServerType serverType=serverTypeDao.findById("1");
		//List<JmCydepartmentWorkerInfo> list = jmCydepartmentWorkerInfoService.getListByCydep("144108824432446765292",  0,  20);
		System.out.println(serverType.getName());
	}*/

}
