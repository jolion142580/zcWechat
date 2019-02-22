package com.gdyiko.base.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.base.po.SysMsgInfo;
import com.gdyiko.base.service.SysMsgInfoService;
import com.gdyiko.tool.dao.MyBaseGenericDao;
import com.gdyiko.tool.service.impl.MyBaseGenericServiceImpl;

/*
 * 
 * 餐饮单位登录人员
 * 
 * */
@Service("sysMsgInfoService")
public class SysMsgInfoServiceImpl extends MyBaseGenericServiceImpl<SysMsgInfo, String > implements SysMsgInfoService {
	
	@Resource(name="sysMsgInfoDao")
	 @Override
	public void setGenericDao(MyBaseGenericDao<SysMsgInfo, String> genericDao) {
		// TODO Auto-generated method stub
		super.setGenericDao(genericDao);
	}
	
	


}
