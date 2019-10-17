package com.gdyiko.zcwx.dao.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.gdyiko.zcwx.dao.YuYuesHistDao;
import com.gdyiko.zcwx.po.YuYuesHist;
import com.gdyiko.tool.dao.impl.GenericDaoImpl;


@Repository("yuYuesHistDao")
public class YuYuesHistDaoImpl extends GenericDaoImpl<YuYuesHist, String> implements YuYuesHistDao {

	@Override
	public Class<YuYuesHist> getEntityClass() {
		// TODO Auto-generated method stub
		return YuYuesHist.class;
	}
	
	
/*	public static void main(String[]xxxx){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		YuYuesHistDao yuYuesHistDao = (YuYuesHistDao)context.getBean("yuYuesHistDao");
		YuYuesHist yuYuesHist=yuYuesHistDao.findById("1");
		//List<JmCydepartmentWorkerInfo> list = jmCydepartmentWorkerInfoService.getListByCydep("144108824432446765292",  0,  20);
		System.out.println(yuYuesHist.getIdcard());
	}*/

}
