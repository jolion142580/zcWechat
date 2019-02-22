package com.gdyiko.base.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.gdyiko.base.dao.JmDataconfigInfoDao;
import com.gdyiko.base.po.JmDataconfigInfo;
import com.gdyiko.base.service.JmDataconfigInfoService;
import com.gdyiko.tool.dao.MyBaseGenericDao;
import com.gdyiko.tool.service.impl.MyBaseGenericServiceImpl;

/*
 * 
 * 餐饮单位登录人员
 * 
 * */
@Service("jmDataconfigInfoService")
public class JmDataconfigInfoServiceImpl extends MyBaseGenericServiceImpl<JmDataconfigInfo, String > implements JmDataconfigInfoService {
	
	@Resource(name="jmDataconfigInfoDao")
	JmDataconfigInfoDao jmDataconfigInfoDao ;
	
	@Resource(name="jmDataconfigInfoDao")
	 @Override
	public void setGenericDao(MyBaseGenericDao<JmDataconfigInfo, String> genericDao) {
		// TODO Auto-generated method stub
		super.setGenericDao(genericDao);
	}

	public List<JmDataconfigInfo> getRootOj() {
		// TODO Auto-generated method stub
		this.getGenericDao().setHql("from JmDataconfigInfo where parent is null");
		return this.getGenericDao().findByPage(1, 200);
	}

	public static void main(String[]xxxx){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		JmDataconfigInfoService jmDataconfigInfoService=(JmDataconfigInfoService)context.getBean("jmDataconfigInfoService");
		///List<MemGeninf> list = memGeninfDAO.findAll();
		//System.out.println(memGeninfDAO.findAll().size());
		JmDataconfigInfo jdc = jmDataconfigInfoService.findById("1");
		Set<JmDataconfigInfo> jdcSet= jdc.getChildren();
		System.out.println(jdcSet);
	}

	public List<JmDataconfigInfo> findNotNullByPage(JmDataconfigInfo entity, String nullColumnName, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return jmDataconfigInfoDao.findNotNullByPage(entity, nullColumnName, firstResult, maxResults);
	}

	public List<JmDataconfigInfo> findChildByPage(JmDataconfigInfo entity, String parentid, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return jmDataconfigInfoDao.findChildByPage(entity, parentid, firstResult, maxResults);
	}
	

	public JmDataconfigInfoDao getJmDataconfigInfoDao() {
		return jmDataconfigInfoDao;
	}

	public void setJmDataconfigInfoDao(JmDataconfigInfoDao jmDataconfigInfoDao) {
		this.jmDataconfigInfoDao = jmDataconfigInfoDao;
	}

	public int findChildBySize(JmDataconfigInfo entity, String parentid) {
		// TODO Auto-generated method stub
		return jmDataconfigInfoDao.findChildBySize(entity, parentid);

	}

	public int findNotNullBySize(JmDataconfigInfo entity, String nullColumnName) {
		// TODO Auto-generated method stub
		return jmDataconfigInfoDao.findNotNullBySize(entity, nullColumnName);
	}

	

}
