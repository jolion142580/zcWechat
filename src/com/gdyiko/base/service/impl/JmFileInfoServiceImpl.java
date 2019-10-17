package com.gdyiko.base.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.base.po.JmFileInfo;
import com.gdyiko.base.service.JmFileInfoService;
import com.gdyiko.tool.dao.MyBaseGenericDao;
import com.gdyiko.tool.service.impl.MyBaseGenericServiceImpl;

/*
 * 
 * 餐饮单位登录人员
 * 
 * */
@Service("jmFileInfoService")
public class JmFileInfoServiceImpl extends MyBaseGenericServiceImpl<JmFileInfo, String > implements JmFileInfoService {
	
	@Resource(name="jmFileInfoDao")
	 @Override
	public void setGenericDao(MyBaseGenericDao<JmFileInfo, String> genericDao) {
		// TODO Auto-generated method stub
		super.setGenericDao(genericDao);
	}
	
	


}
