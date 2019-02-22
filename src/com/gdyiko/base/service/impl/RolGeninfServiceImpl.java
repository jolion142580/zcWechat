package com.gdyiko.base.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.base.dao.MemGeninfDao;
import com.gdyiko.base.po.MemGeninf;
import com.gdyiko.base.po.MenuInfo;
import com.gdyiko.base.po.RolGeninf;
import com.gdyiko.base.service.RolGeninfService;
import com.gdyiko.tool.dao.MyBaseGenericDao;
import com.gdyiko.tool.service.impl.MyBaseGenericServiceImpl;

@Service("rolGeninfService")
public class RolGeninfServiceImpl extends MyBaseGenericServiceImpl<RolGeninf, String > implements RolGeninfService {
	
	///@Autowired
	///MemGeninfDao memGeninfDao;
	@Resource(name="memGeninfDao")
	MemGeninfDao memGeninfDao;
	@Resource(name="rolGeninfDao")
	@Override
	public void setGenericDao(MyBaseGenericDao<RolGeninf, String> genericDao) {
		// TODO Auto-generated method stub
		super.setGenericDao(genericDao);
	}


	//设置删除分配
	public void  delMemgeninfsById(String rolid,String memids) {
		// TODO Auto-generated method stub
		RolGeninf rolGeninf = this.getGenericDao().findById(rolid);
		Set<MemGeninf> memGeninfs = rolGeninf.getMemGeninfs();
		
		String[] tempmemids = memids.split(";");
		for (int i = 0; i < tempmemids.length; i++) {
			MemGeninf memGeninf = memGeninfDao.findById(tempmemids[i]);
			if(memGeninf!=null){
				memGeninfs.remove(memGeninf);
			}
		}
		rolGeninf.setMemGeninfs(memGeninfs);
		this.getGenericDao().modify(rolGeninf);
	}
	
	@Override
	public void remove(RolGeninf entity) {
		// TODO Auto-generated method stub
		String id= entity.getId();
		entity.setMemGeninfs(null);
		System.out.println("info:解除多对多关系");
		super.modify(entity);
		System.out.println("info:避免数据不同步先查询相关数据重新删除");
		entity = super.findById(id);
		System.out.println("info:相关数据重新删除");
		super.remove(entity);
		
	}
}
