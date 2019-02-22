package com.gdyiko.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.base.dao.MemGeninfDao;
import com.gdyiko.base.po.MemGeninf;
import com.gdyiko.base.service.LoginService;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.MD5Util;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Resource(name="memGeninfDao")
	MemGeninfDao  memGeninfDao;
	
	public MemGeninf loginVerify(String userName,String password) {
		// TODO Auto-generated method stub
		
		 String md5pw=  MD5Util.MD5(password);
	       MemGeninf memGeninf =  new MemGeninf();
	       memGeninf.setLoginid(userName);
	       List<MemGeninf> memGeninfList = memGeninfDao.findLikeByEntity(memGeninf,  BeanUtilEx.getNotNullEscapePropertyNames(memGeninf), 0, 20);
	       if(memGeninfList.size()==0){
	    	   //没有该用户的情况，需要用户限制用户名 不一致
	    	   return null;
	    	   
	       }
	       memGeninf = memGeninfList.get(0);
	       String mypassword = memGeninf.getPassword();
	       if(md5pw.equals(mypassword.trim())) return memGeninf;
	       return null;
	}

	public MemGeninfDao getMemGeninfDao() {
		return memGeninfDao;
	}

	public void setMemGeninfDao(MemGeninfDao memGeninfDao) {
		this.memGeninfDao = memGeninfDao;
	}
	
	

}
