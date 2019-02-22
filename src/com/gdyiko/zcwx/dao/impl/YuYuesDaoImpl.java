package com.gdyiko.zcwx.dao.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.gdyiko.zcwx.dao.YuYuesDao;
import com.gdyiko.zcwx.po.YuYues;
import com.gdyiko.tool.dao.impl.GenericDaoImpl;


@Repository("yuYuesDao")
public class YuYuesDaoImpl extends GenericDaoImpl<YuYues, String> implements YuYuesDao {

	@Override
	public Class<YuYues> getEntityClass() {
		// TODO Auto-generated method stub
		return YuYues.class;
	}
	
	
/*	public static void main(String[]xxxx){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		YuYuesDao yuYuesDao = (YuYuesDao)context.getBean("yuYuesDao");
		YuYues yuYues=yuYuesDao.findById("10002075");
		//List<JmCydepartmentWorkerInfo> list = jmCydepartmentWorkerInfoService.getListByCydep("144108824432446765292",  0,  20);
		System.out.println(yuYues.getName());
	}*/

}
