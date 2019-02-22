package com.gdyiko.base.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.base.po.JmFileBsnLink;
import com.gdyiko.base.service.JmFileBsnLinkService;
import com.gdyiko.tool.dao.MyBaseGenericDao;
import com.gdyiko.tool.service.impl.MyBaseGenericServiceImpl;

/*
 * 
 * 餐饮单位登录人员
 * 
 * */
@Service("jmFileBsnLinkService")
public class JmFileBsnLinkServiceImpl extends MyBaseGenericServiceImpl<JmFileBsnLink, String > implements JmFileBsnLinkService {
	
	@Resource(name="jmFileBsnLinkDao")
	 @Override
	public void setGenericDao(MyBaseGenericDao<JmFileBsnLink, String> genericDao) {
		// TODO Auto-generated method stub
		super.setGenericDao(genericDao);
	}
	
	


}
