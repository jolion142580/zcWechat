package com.gdyiko.zcwx.dao.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.gdyiko.zcwx.dao.FileInfoDao;
import com.gdyiko.zcwx.dao.ServerTypeDao;
import com.gdyiko.zcwx.dao.SsAffairsDao;
import com.gdyiko.zcwx.dao.SsAffairsGuideDao;
import com.gdyiko.zcwx.dao.SsBaseDicDao;
import com.gdyiko.zcwx.po.FileInfo;
import com.gdyiko.zcwx.po.ServerType;
import com.gdyiko.zcwx.po.SsAffairGuide;
import com.gdyiko.zcwx.po.SsAffairs;
import com.gdyiko.zcwx.po.SsBaseDic;
import com.gdyiko.tool.dao.impl.GenericDaoImpl;


@Repository("fileInfoDao")
public class FileInfoDaoImpl extends GenericDaoImpl<FileInfo, String> implements FileInfoDao {

	@Override
	public Class<FileInfo> getEntityClass() {
		// TODO Auto-generated method stub
		return FileInfo.class;
	}
	
	
/*	public static void main(String[]xxxx){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ServerTypeDao serverTypeDao = (ServerTypeDao)context.getBean("serverTypeDao");
		ServerType serverType=serverTypeDao.findById("1");
		//List<JmCydepartmentWorkerInfo> list = jmCydepartmentWorkerInfoService.getListByCydep("144108824432446765292",  0,  20);
		System.out.println(serverType.getName());
	}*/

}
