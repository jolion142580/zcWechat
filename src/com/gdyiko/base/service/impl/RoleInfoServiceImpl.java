package com.gdyiko.base.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.base.dao.MenuInfoDao;
import com.gdyiko.base.po.MenuInfo;
import com.gdyiko.base.po.RoleInfo;
import com.gdyiko.base.service.RoleInfoService;
import com.gdyiko.tool.dao.MyBaseGenericDao;
import com.gdyiko.tool.service.impl.MyBaseGenericServiceImpl;

@Service("roleInfoService")
public class RoleInfoServiceImpl extends MyBaseGenericServiceImpl<RoleInfo, String> implements RoleInfoService {

	// /@Autowired
	// /MemGeninfDao memGeninfDao;
	@Resource(name = "roleInfoDao")
	@Override
	public void setGenericDao(MyBaseGenericDao<RoleInfo, String> genericDao) {
		// TODO Auto-generated method stub
		super.setGenericDao(genericDao);
	}
	@Resource(name = "menuInfoDao")
	MenuInfoDao menuInfoDao;

	public RoleInfo saveMenuInfos(String id,String menuids) {
		// TODO Auto-generated method stub
		RoleInfo roleInfo = this.getGenericDao().findById(id);
		
		Set<MenuInfo> menuInfos =new HashSet<MenuInfo>();
		String[] tempmenuids = menuids.split(";");
		for (int i = 0; i < tempmenuids.length; i++) {
			MenuInfo menuInfo = menuInfoDao.findById(tempmenuids[i]);
			if(menuInfo!=null){
				menuInfos.add(menuInfo);
			}
		}
		
		roleInfo.setMenuInfos(menuInfos);
		this.getGenericDao().modify(roleInfo);
		return null;
	}

	//
	@Override
	public void remove(RoleInfo entity) {
		// TODO Auto-generated method stub
		String id= entity.getId();
		entity.setMenuInfos(null);
		System.out.println("info:解除多对多关系");
		super.modify(entity);
		System.out.println("info:避免数据不同步先查询相关数据重新删除");
		entity = super.findById(id);
		System.out.println("info:相关数据重新删除");
		super.remove(entity);
	}
}
