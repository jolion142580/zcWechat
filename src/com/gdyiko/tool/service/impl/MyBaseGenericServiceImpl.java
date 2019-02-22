package com.gdyiko.tool.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gdyiko.tool.dao.MyBaseGenericDao;
import com.gdyiko.tool.service.MyBaseGenericService;



/**
 * 
 * @author Abu
 *
 * @param <T>
 * @param <ID>
 */
public  class MyBaseGenericServiceImpl<T, ID extends Serializable> implements
		MyBaseGenericService<T, ID> {
	///@Resource(name="genericDao")
	private MyBaseGenericDao<T,ID> myBaseGenericDao;
	
	///protected abstract Class<T> getEntityClass();
	
	
	public MyBaseGenericDao<T, ID> getGenericDao() {
		return myBaseGenericDao;
	}


	public List<T> findAll() {	
		System.out.println(myBaseGenericDao);
		return myBaseGenericDao.findAll();
	}
	public T findById(ID id) {		
		return myBaseGenericDao.findById(id);
	}
	public List<T> findByPage(int page, int size) {		
		return myBaseGenericDao.findByPage(page, size);
	}
	public List<T> findByPage(T entity,String [] propertyNames,int page, int size){
		
		return myBaseGenericDao.findLikeByEntity(entity, propertyNames, size*(page-1), page*size);
	}
	public int getPageSize(int size) {		
		return myBaseGenericDao.getPageSize(size);
	}
	public int getTotalRows() {		
		return myBaseGenericDao.getTotalRows();
	}
	@Transactional 
	public void modify(T entity) {
		myBaseGenericDao.modify(entity);		
	}
	@Transactional 
	public void remove(T entity) {
		myBaseGenericDao.remove(entity);
	}
	public void removeAll(Collection<T> entities) {
		myBaseGenericDao.removeAll(entities);		
	}
	
	@Transactional 
	public ID save(T entity) {	
		
		return myBaseGenericDao.save(entity);
	}
	public void setGenericDao(MyBaseGenericDao<T, ID> genericDao) {
		this.myBaseGenericDao = genericDao;
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
		return this.myBaseGenericDao.findEqualByEntity( entity, propertyNames);
	}


	public List<T> findLikeByEntity(T entity, String[] propertyNames) {
		// TODO Auto-generated method stub
		return this.myBaseGenericDao.findLikeByEntity(entity, propertyNames);
	}	
	
	public int findLikeByEntitySize(T entity, String[] propertyNames) {
		// TODO Auto-generated method stub
		return this.myBaseGenericDao.findLikeByEntitySize(entity, propertyNames);
	}	
	
	public List<T> findLikeByEntity(T entity, String[] propertyNames,int  firstResult,int maxResults) {
		// TODO Auto-generated method stub
		return this.myBaseGenericDao.findLikeByEntity(entity, propertyNames, firstResult, maxResults);
	}
}
