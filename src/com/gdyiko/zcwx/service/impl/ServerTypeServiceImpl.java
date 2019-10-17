package com.gdyiko.zcwx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.ServerTypeDao;
import com.gdyiko.zcwx.po.ServerType;
import com.gdyiko.zcwx.service.ServerTypeService;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("serverTypeService")
public class ServerTypeServiceImpl extends GenericServiceImpl<ServerType, String> implements ServerTypeService {
	@Resource(name = "serverTypeDao")
	ServerTypeDao serverTypeDao;

	@Resource(name = "serverTypeDao")
	@Override
	public void setGenericDao(GenericDao<ServerType, String> genericDao) {
		super.setGenericDao(genericDao);
	}

}
