package com.gdyiko.zcwx.service.impl;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.SsUserInfoDao;
import com.gdyiko.zcwx.service.SsUserInfoService;
import com.gdyiko.zcwx.po.SsUserInfo;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

import java.util.List;

@Service("ssUserInfoService")
public class SsUserInfoServiceImpl extends
		GenericServiceImpl<SsUserInfo, String> implements
		SsUserInfoService {
	@Resource(name = "ssUserInfoDao")
	SsUserInfoDao ssUserInfoDao;

	@Resource(name = "ssUserInfoDao")
	@Override
	public void setGenericDao(GenericDao<SsUserInfo, String> genericDao) {
		super.setGenericDao(genericDao);
	}

	@Override
	public SsUserInfo findByPhone(String phone) {

		String hql = "from SsUserInfo where phone = ?";

		String[] values = new String[]{phone};

		List<SsUserInfo> ssUserInfoList =ssUserInfoDao.findByHqlParam(hql,values);
		if (ssUserInfoList.size()>0){
			return ssUserInfoList.get(0);
		}
		return null;
	}


/*	public static void main(String[]xxxx){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		SsUserInfoService ssUserInfoService = (SsUserInfoService)context.getBean("ssUserInfoDao");
		SsUserInfo ssUserInfo=ssUserInfoService.findByPhone("13428808281");
		//List<JmCydepartmentWorkerInfo> list = jmCydepartmentWorkerInfoService.getListByCydep("144108824432446765292",  0,  20);
		System.out.println(ssUserInfo.getName());

	}*/

}
