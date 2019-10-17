package com.gdyiko.base.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.base.dao.MemGeninfDao;
import com.gdyiko.base.dao.RoleInfoDao;
import com.gdyiko.base.po.MemGeninf;
import com.gdyiko.base.po.RolGeninf;
import com.gdyiko.base.po.RoleInfo;
import com.gdyiko.base.service.MemGeninfService;
import com.gdyiko.tool.dao.MyBaseGenericDao;
import com.gdyiko.tool.service.impl.MyBaseGenericServiceImpl;

@Service("memGeninfService")
public class MemGeninfServiceImpl extends MyBaseGenericServiceImpl<MemGeninf, String > implements MemGeninfService {
	
	@Resource(name="memGeninfDao")
	MemGeninfDao memGeninfDao;
	@Resource(name="roleInfoDao")
	RoleInfoDao roleInfoDao;
	
	@Resource(name="memGeninfDao")
	@Override
	public void setGenericDao(MyBaseGenericDao<MemGeninf, String> genericDao) {
		// TODO Auto-generated method stub
		super.setGenericDao(genericDao);
	}

	public void setNotRoleMemHql(String rolid) {
		// TODO Auto-generated method stub
		memGeninfDao.setNotRoleMemHql( rolid);
	}

	//查询特殊SQL
	public List<MemGeninf> findByPageNotRoleMem(int page, int size,String rolid) {
		// TODO Auto-generated method stub
		memGeninfDao.setNotRoleMemHql(rolid);
		return memGeninfDao.findByPage(page, size);
	}

	

	public MemGeninfDao getMemGeninfDao() {
		return memGeninfDao;
	}

	public void setMemGeninfDao(MemGeninfDao memGeninfDao) {
		this.memGeninfDao = memGeninfDao;
	}

	public RoleInfoDao getRoleInfoDao() {
		return roleInfoDao;
	}

	public void setRoleInfoDao(RoleInfoDao roleInfoDao) {
		this.roleInfoDao = roleInfoDao;
	}

	public void saveRoleInfos(String id,String roleinfoids) {
		// TODO Auto-generated method stub
		MemGeninf memGeninf = this.getGenericDao().findById(id);
		
		Set<RoleInfo> roleInfos =new HashSet<RoleInfo>();
		String[] temproleinfoids = roleinfoids.split(";");
		for (int i = 0; i < temproleinfoids.length; i++) {
			RoleInfo roleInfo = roleInfoDao.findById(temproleinfoids[i]);
			if(roleInfo!=null){
				roleInfos.add(roleInfo);
			}
		}
		
		memGeninf.setRoleinfos(roleInfos);
		this.getGenericDao().modify(memGeninf);
	
	}
	
	@Override
	public void remove(MemGeninf entity) {
		// TODO Auto-generated method stub
		String id= entity.getId();
		entity.setRolGenInfs(null);
		entity.setRoleinfos(null);
		System.out.println("info:解除多对多关系");
		super.modify(entity);
		System.out.println("info:避免数据不同步先查询相关数据重新删除");
		entity = super.findById(id);
		System.out.println("info:相关数据重新删除");
		super.remove(entity);
	}
	
	
	public String getDepartName(String memid){
		MemGeninf memGeninf = memGeninfDao.findById(memid);
		String depname =""; 
		if(memGeninf.getRolGenInfs()!=null){
			Iterator rolIterator = memGeninf.getRolGenInfs().iterator();
			if(rolIterator.hasNext()){
				RolGeninf rolGeninf=  (RolGeninf)rolIterator.next();
				if(rolGeninf.getDepGeninf()!=null){
					depname = rolGeninf.getDepGeninf().getName();
				}
			}
		}
		return depname;
	}

}
