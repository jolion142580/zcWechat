package com.gdyiko.zcwx.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gdyiko.zcwx.dao.IsPushDao;
import com.gdyiko.zcwx.po.IsPush;
import com.gdyiko.tool.dao.impl.GenericDaoImpl;

@Repository("isPushDao")
public class IsPushDaoImpl extends GenericDaoImpl<IsPush, String> implements IsPushDao 
{
	@Override
	public Class<IsPush> getEntityClass() {
		// TODO Auto-generated method stub
		return IsPush.class;
	}
	
	/**
	 * 获取指定预约号的记录集合
	 * 
	 * @param yyno	预约号
	 * @return
	 */
	public List<IsPush> findByYyno(String yyno)
	{
		Criteria criteria = this.createCriteria();
		criteria.add( Restrictions.eq("yyno", yyno ) );
		
		return (List<IsPush>) criteria.list();
	}
}
