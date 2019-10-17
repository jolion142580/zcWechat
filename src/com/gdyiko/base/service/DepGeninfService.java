package com.gdyiko.base.service;

import java.lang.reflect.InvocationTargetException;

import net.sf.json.JSONArray;

import com.gdyiko.base.po.DepGeninf;
import com.gdyiko.tool.service.MyBaseGenericService;

public interface DepGeninfService extends MyBaseGenericService<DepGeninf, String> {
	
	
  //public List<DepGeninf> getRootOj();
	
	
	//public List<DepGeninf> getRootOj(String departname);

	
  //  public List<DepGeninf> findNotNullByPage(DepGeninf entity, String nullColumnName, int firstResult,int maxResults);
 

   /// public List<DepGeninf> findChildByPage(DepGeninf entity, String parentid, int firstResult,int maxResults);

	//public int findChildBySize(DepGeninf model, String id);

	//public int findNotNullBySize(DepGeninf model, String string);
	
	public JSONArray subNode(String tempid);


	public JSONArray rootNode(String departname) throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException;


	public void save(DepGeninf model, String parentid);
	
	public void remove(String myid);


}
