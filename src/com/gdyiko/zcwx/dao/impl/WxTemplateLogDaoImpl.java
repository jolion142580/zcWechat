package com.gdyiko.zcwx.dao.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.gdyiko.zcwx.dao.OnlineApplyDao;
import com.gdyiko.zcwx.dao.WxTemplateLogDao;
import com.gdyiko.zcwx.po.OnlineApply;
import com.gdyiko.zcwx.po.WxTemplateLog;
import com.gdyiko.tool.dao.impl.GenericDaoImpl;


@Repository("wxTemplateLogDao")
public class WxTemplateLogDaoImpl extends GenericDaoImpl<WxTemplateLog, String> implements WxTemplateLogDao {

	@Override
	public Class<WxTemplateLog> getEntityClass() {
		// TODO Auto-generated method stub
		return WxTemplateLog.class;
	}
	
/*	
	public static void main(String[]xxxx){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		SsUserInfoDao ssUserInfoDao = (SsUserInfoDao)context.getBean("ssUserInfoDao");
		SsUserInfo ssUserInfo=ssUserInfoDao.findById("1");
		//List<JmCydepartmentWorkerInfo> list = jmCydepartmentWorkerInfoService.getListByCydep("144108824432446765292",  0,  20);
		System.out.println(ssUserInfo.getName());
		
	}*/

}
