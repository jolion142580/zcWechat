package com.gdyiko.base.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gdyiko.base.dao.DepGeninfDao;
import com.gdyiko.base.po.DepGeninf;
import com.gdyiko.tool.dao.impl.MyBaseGenericDaoImpl;

@Repository("depGeninfDao")
public class DepGeninfDaoImpl extends MyBaseGenericDaoImpl<DepGeninf, String> implements DepGeninfDao {

	@Override
	public Class<DepGeninf> getEntityClass() {
		// TODO Auto-generated method stub
		return DepGeninf.class;
	}

	public List<DepGeninf> getRootOj() {
		// TODO Auto-generated method stub
		this.setHql("from DepGeninf where parent is null ");
		return this.findByPage(1, 1000);
	}


	/**
	 * 
	 * 
	 * @Title: findNotNullBySize
	 * 
	 * @Description: TODO(查询顶层数量)
	 * 
	 * @param @param entity
	 * @param @param nullColumnName
	 * @param @return 设定文件
	 * 
	 * @return int 返回类型
	 * 
	 * @throws
	 */
	public int findNotNullBySize(DepGeninf entity, String nullColumnName) {
		Criteria criteria = this.createCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.isNull(nullColumnName));
		return criteria.list().size();
	}

	// 查询父ID内的
	public List<DepGeninf> findChildByPage(DepGeninf entity, String parentid, int firstResult, int maxResults) {
		System.out.println("findChildByPage----------->" + parentid + "-->" + firstResult + "-->" + maxResults);
		Criteria criteria = this.createCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		// criteria.add(Restrictions.eq("parentid", parentid));
		criteria.add(Restrictions.eq("parent", this.findById(parentid)));
		criteria.addOrder(Order.asc("postno"));
		////System.out.println("info:------------?" + criteria.list().size());
		return (List<DepGeninf>) criteria.list();
	}

	// 查询子数量
	public int findChildBySize(DepGeninf entity, String parentid) {
		System.out.println("findChildByPage----------->" + parentid);
		Criteria criteria = this.createCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq("parent", this.findById(parentid)));
		return criteria.list().size();
	}

	public List<DepGeninf> findNotNullByPage(DepGeninf entity, String nullColumnName, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return this.findNotNullByPage(entity, nullColumnName, firstResult, maxResults);
	}

	public List<DepGeninf> getRootOj(String departname) {
		// TODO Auto-generated method stub
		this.setHql("from DepGeninf where parent is null and name='"+departname+"'");
		return this.findByPage(1, 1000);
	}

}
