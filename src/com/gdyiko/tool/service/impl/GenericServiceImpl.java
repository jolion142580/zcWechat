package com.gdyiko.tool.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.GenericService;



/**
 * 
 * @author Abu
 *
 * @param <T>
 * @param <ID>
 */
public  class GenericServiceImpl<T, ID extends Serializable> implements
		GenericService<T, ID> {
	///@Resource(name="genericDao")
	private GenericDao<T,ID> genericDao;
	
	///protected abstract Class<T> getEntityClass();
	
	
	public GenericDao<T, ID> getGenericDao() {
		return genericDao;
	}


	public List<T> findAll() {	
		System.out.println(genericDao);
		return genericDao.findAll();
	}
	public T findById(ID id) {		
		return genericDao.findById(id);
	}
	public List<T> findByPage(int page, int size) {		
		return genericDao.findByPage(page, size);
	}
	public List<T> findByPage(T entity,String [] propertyNames,int page, int size){
		
		return genericDao.findLikeByEntity(entity, propertyNames, size*(page-1), page*size);
	}
	public int getPageSize(int size) {		
		return genericDao.getPageSize(size);
	}
	public int getTotalRows() {		
		return genericDao.getTotalRows();
	}
	@Transactional 
	public void modify(T entity) {
		genericDao.modify(entity);		
	}
	@Transactional 
	public void remove(T entity) {
		genericDao.remove(entity);
	}
	public void removeAll(Collection<T> entities) {
		genericDao.removeAll(entities);		
	}
	
	@Transactional 
	public ID save(T entity) {	
		
		return genericDao.save(entity);
	}
	public void setGenericDao(GenericDao<T, ID> genericDao) {
		this.genericDao = genericDao;
	}

	/*
	 * 分页  数量  sql
	 * */
	public List<T> findByPageName(int page, int size, String str) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<T> findEqualByEntity(T entity, String[] propertyNames) {
		// TODO Auto-generated method stub
		return this.genericDao.findEqualByEntity( entity, propertyNames);
	}


	public List<T> findLikeByEntity(T entity, String[] propertyNames) {
		// TODO Auto-generated method stub
		return this.genericDao.findLikeByEntity(entity, propertyNames);
	}	
	
	public int findLikeByEntitySize(T entity, String[] propertyNames) {
		// TODO Auto-generated method stub
		return this.genericDao.findLikeByEntitySize(entity, propertyNames);
	}	
	
	public List<T> findLikeByEntity(T entity, String[] propertyNames,int  firstResult,int maxResults) {
		// TODO Auto-generated method stub
		return this.genericDao.findLikeByEntity(entity, propertyNames, firstResult, maxResults);
	}


	public void setExvalue(boolean exvalue) {
		// TODO Auto-generated method stub
		this.genericDao.setExvalue(exvalue);
	}


	public boolean isExvalue() {
		// TODO Auto-generated method stub
		return this.genericDao.isExvalue();
	}


	
}
