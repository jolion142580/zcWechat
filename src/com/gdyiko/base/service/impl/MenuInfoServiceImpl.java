package com.gdyiko.base.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.gdyiko.base.dao.MenuInfoDao;
import com.gdyiko.base.po.MenuInfo;
import com.gdyiko.base.service.MenuInfoService;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.PrimaryProduce;
import com.gdyiko.tool.dao.MyBaseGenericDao;
import com.gdyiko.tool.easyui.TreeNodeJson;
import com.gdyiko.tool.service.impl.MyBaseGenericServiceImpl;

@Service("menuInfoService")
public class MenuInfoServiceImpl extends MyBaseGenericServiceImpl<MenuInfo, String> implements MenuInfoService {


	// /MemGeninfDao memGeninfDao;
	@Resource(name = "menuInfoDao")
	MenuInfoDao menuInfoDao;
	String creator = "";
	
	@Resource(name = "menuInfoDao")
	@Override
	public void setGenericDao(MyBaseGenericDao<MenuInfo, String> genericDao) {
		// TODO Auto-generated method stub
		super.setGenericDao(genericDao);
	}

	
	// 获得ID并且编列节点的一级
		public JSONArray subNode(String tempid)  {

			System.out.println("当前节点ID--->" + tempid);
			System.out.println("depGeninfDao------>"+menuInfoDao);
			MenuInfo jdc = menuInfoDao.findById(tempid);
			Set<MenuInfo> jdcSet = jdc.getChildren();
			System.out.println("jdcSet.size()--->"+jdcSet.size());
			Iterator<MenuInfo> jdcIterator = jdcSet.iterator();
			List<TreeNodeJson> list = new ArrayList<TreeNodeJson>();
			while (jdcIterator.hasNext()) {
				TreeNodeJson treeNodeJsonTemp = new TreeNodeJson();
				MenuInfo tempJdc = jdcIterator.next();
				String id = tempJdc.getId();
				String text =tempJdc.getName();
				String link = tempJdc.getLink();
				String code = tempJdc.getCode();
				String type= tempJdc.getType();
				String icon= tempJdc.getIcon();
				String menuorder= tempJdc.getMenuorder();
				treeNodeJsonTemp.setId(id);
				treeNodeJsonTemp.setText(text);
				if("0".equals(type)){
					//treeNodeJsonTemp.setIcon("icon-application_link");
					//treeNodeJsonTemp.setIconCls("icon-application_link");
				}
				if("1".equals(type)){
					treeNodeJsonTemp.setIconCls("icon-application_link");
				}
				///treeNodeJsonTemp.setIcon("icon-application_link");
				///treeNodeJsonTemp.setIconCls("icon-application_link");
				HashMap<String,String> attributes = new HashMap<String,String>();
				if(link!=null){
					System.out.println("link-->"+link);
					attributes.put("link", link);
				}else{
					attributes.put("link", "");
				}
				if(code!=null){
					System.out.println("code-->"+code);
					attributes.put("code", code);
				}else{
					attributes.put("code", "");
				}
				if(type!=null){
					System.out.println("type-->"+type);
					attributes.put("type",type);
				}else{
					attributes.put("type", "");
				}
				if(icon!=null){
					System.out.println("type-->"+icon);
					attributes.put("icon",icon);
				}else{
					attributes.put("icon", "");
				}
				if(menuorder!=null){
					System.out.println("type-->"+menuorder);
					attributes.put("menuorder",menuorder);
				}else{
					attributes.put("menuorder", "");
				}
				
				
				treeNodeJsonTemp.setAttributes(attributes);
				list.add(treeNodeJsonTemp);
			}

			JSONArray ja = JSONArray.fromObject(list);
			// //Struts2Utils.renderText(ja.toString());
			return ja;
		}

		public JSONArray rootNode(String name) throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
			// TODO Auto-generated method stub
			TreeNodeJson treeNodeJson =new TreeNodeJson();
			List<MenuInfo> jdcSet;
			if("".equals(name)){
				jdcSet= menuInfoDao.getRootOj();
			}else{
				jdcSet= menuInfoDao.getRootOj(name);
			}
			
			System.out.println("jdcSet------>"+jdcSet.size());
			///List<JmMonitorInfo> jdcSet= jmMonitorInfoService.getRootOj();
			Iterator<MenuInfo> jdcIterator = jdcSet.iterator();
			List<TreeNodeJson> list  = new ArrayList<TreeNodeJson>();
			while(jdcIterator.hasNext()){
				TreeNodeJson treeNodeJsonTemp = new TreeNodeJson();
				MenuInfo tempJdc= jdcIterator.next();
				String id = tempJdc.getId();
				String text =tempJdc.getName();
				String link = tempJdc.getLink();
				String code = tempJdc.getCode();
				String type= tempJdc.getType();
				String icon = tempJdc.getIcon();
				String menuorder= tempJdc.getMenuorder();
				
				treeNodeJsonTemp.setId(id);
				treeNodeJsonTemp.setText(text);
				treeNodeJsonTemp.setIcon("icon-edit");
				
				if("0".equals(type)){
					//treeNodeJsonTemp.setIcon("icon-application_link");
					//treeNodeJsonTemp.setIconCls("icon-application_link");
				}
				if("1".equals(type)){
					treeNodeJsonTemp.setIcon("icon-application_link");
					treeNodeJsonTemp.setIconCls("icon-application_link");
				}
				HashMap<String,String> attributes = new HashMap<String,String>();
				if(link!=null){
					System.out.println("link-->"+link);
					attributes.put("link", link);
				}else{
					attributes.put("link", "");
				}
				if(code!=null){
					System.out.println("code-->"+code);
					attributes.put("code", code);
				}else{
					attributes.put("code", "");
				}
				if(type!=null){
					System.out.println("type-->"+type);
					attributes.put("type",type);
				}else{
					attributes.put("type", "");
				}
				if(icon!=null){
					System.out.println("type-->"+icon);
					attributes.put("icon",icon);
				}else{
					attributes.put("icon", "");
				}
				if(menuorder!=null){
					System.out.println("type-->"+menuorder);
					attributes.put("menuorder",menuorder);
				}else{
					attributes.put("menuorder", "");
				}
				
				treeNodeJsonTemp.setAttributes(attributes);
				list.add(treeNodeJsonTemp);
			}
			
			//String id = jdc.getId();
			//String text =jdc.getName();
			treeNodeJson.setId("root");
			treeNodeJson.setText("系统目录");
			treeNodeJson.setIcon("icon-edit");
			treeNodeJson.setChildren(list);
			
			///List<TreeNodeJson> tree = new ArrayList<TreeNodeJson>();
			JsonConfig jsonConfig = getJsonConfigEx(treeNodeJson);
			
			JSONObject jsonObject = JSONObject.fromObject(treeNodeJson, jsonConfig);
			JSONArray ja = new JSONArray();
			ja.add(jsonObject);
			return ja;
		}
		
		// 返回值类型设置
		public JsonConfig getJsonConfigEx(TreeNodeJson model) throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
			JsonConfig jsonConfig = new JsonConfig();
			// 设置json无线嵌套避免
			jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			// 设置逃逸非基础的属性，保留String int double 等基础属性
			//jsonConfig.setExcludes(BeanUtilEx.getNullPropertyNames(model));
			//jsonConfig.setIgnoreDefaultExcludes(false);
			return jsonConfig;
		}

		public void save(MenuInfo model, String parentid) {
			// TODO Auto-generated method stub
			System.out.println("save parentid------>"+parentid);
			MenuInfo parent = menuInfoDao.findById(parentid);
			
			System.out.println("save parentid------>"+parent);
			model.setParent(parent);
			
			if (model.getCreator() == null || "".equals(model.getCreator())) {
				model.setCreator(creator);
			}
			if (model.getId() == null || "".equals(model.getId())) {
				model.setId(PrimaryProduce.produce());
				menuInfoDao.save(model);
			}else{
				String id = model.getId();
				MenuInfo entity = menuInfoDao.findById(id);
				///T entity = myBaseGenericService.findById(myid);
				//确保关系不被清理
				///System.out.println("check fuck------------------------->"+entity.getRoleInfos().size());
				BeanUtils.copyProperties(model, entity, BeanUtilEx.getNullPropertyNames(model));
				///System.out.println("check fuck------------------------->"+entity.getRoleInfos().size());
				menuInfoDao.modify(entity);
			}
		}
		public void remove(String myid){
			MenuInfo menuInfo = menuInfoDao.findById(myid);
			menuInfo.setRoleInfos(null);
			menuInfoDao.modify(menuInfo);
			menuInfo = menuInfoDao.findById(myid);
			menuInfoDao.remove(menuInfo);
		}
	
}
