package com.gdyiko.base.service;

import java.lang.reflect.InvocationTargetException;

import net.sf.json.JSONArray;

import com.gdyiko.base.po.MenuInfo;
import com.gdyiko.tool.service.MyBaseGenericService;

public interface MenuInfoService extends MyBaseGenericService<MenuInfo, String> {
	
	
	public JSONArray subNode(String tempid);


	public JSONArray rootNode(String name) throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException;


	public void save(MenuInfo model, String parentid);
	
	public void remove(String myid);
}
