package com.gdyiko.zcwx.dao.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.gdyiko.zcwx.dao.SmsInfoDao;
import com.gdyiko.zcwx.po.SmsInfo;

import com.gdyiko.tool.dao.impl.GenericDaoImpl;
@Repository("SmsInfoDao")
public class SmsInfoDaoImpl extends GenericDaoImpl<SmsInfo, String> implements SmsInfoDao {

	@Override
	public Class<SmsInfo> getEntityClass() {
		// TODO Auto-generated method stub
		return SmsInfo.class;
	}

	public List<SmsInfo> findbyMobileTime(String time,String mobile) {
		// TODO Auto-generated method stub
		String hql = " from SmsInfo smsInfo " +
				"where CONVERT(varchar(100), smsInfo.creattime , 23)=CONVERT(varchar(10), '"+time+"' )" +
						"and smsInfo.smsmobile = '"+mobile+"' ";
	 return	super.findByHql(hql);
	}

	public List<SmsInfo> findbyIPTime(String time, String ip) {
		// TODO Auto-generated method stub
		String hql = " from SmsInfo smsInfo " +
				"where CONVERT(varchar(100), smsInfo.creattime , 23)=CONVERT(varchar(10), '"+time+"' )"+
						"and smsInfo.ip = '"+ip+"' ";
	 return	super.findByHql(hql);
	}
	
/*	public static void main(String[] args) {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	SmsInfoDao SmsInfo=(SmsInfoDao)context.getBean("SmsInfoDao");
	System.out.println(System.currentTimeMillis());
	List<SmsInfo> rs = SmsInfo.findbyIPTime("2017-02-15 16:29:01.663","192.168.1.2");
	System.out.println(System.currentTimeMillis());
	System.out.println(rs.toString());
	System.out.println(rs.size());
	}*/



}
