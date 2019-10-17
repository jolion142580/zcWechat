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

import com.gdyiko.base.dao.DepGeninfDao;
import com.gdyiko.base.po.DepGeninf;
import com.gdyiko.base.po.MenuInfo;
import com.gdyiko.base.service.DepGeninfService;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.PrimaryProduce;
import com.gdyiko.tool.dao.MyBaseGenericDao;
import com.gdyiko.tool.easyui.TreeNodeOrgDepJson;
import com.gdyiko.tool.service.impl.MyBaseGenericServiceImpl;

@Service("depGeninfService")
public class DepGeninfServiceImpl extends MyBaseGenericServiceImpl<DepGeninf, String> implements DepGeninfService {

	// /@Autowired
	// /MemGeninfDao memGeninfDao;
	@Resource(name = "depGeninfDao")
	DepGeninfDao depGeninfDao;
	String creator = "";

	@Resource(name = "depGeninfDao")
	  @Override public void setGenericDao(MyBaseGenericDao<DepGeninf, String>
	  genericDao) { // TODO Auto-generated method stub
	  super.setGenericDao(genericDao); }
	 

	

	public DepGeninfDao getDepGeninfDao() {
		return depGeninfDao;
	}

	public void setDepGeninfDao(DepGeninfDao depGeninfDao) {
		this.depGeninfDao = depGeninfDao;
	}

	// 获得ID并且编列节点的一级
	public JSONArray subNode(String tempid)  {

		System.out.println("当前节点ID--->" + tempid);
		System.out.println("depGeninfDao------>"+depGeninfDao);
		DepGeninf jdc = depGeninfDao.findById(tempid);
		Set<DepGeninf> jdcSet = jdc.getChildren();
		System.out.println("jdcSet.size()--->"+jdcSet.size());
		Iterator<DepGeninf> jdcIterator = jdcSet.iterator();
		List<TreeNodeOrgDepJson> list = new ArrayList<TreeNodeOrgDepJson>();
		while (jdcIterator.hasNext()) {
			TreeNodeOrgDepJson treeNodeJsonTemp = new TreeNodeOrgDepJson();
			DepGeninf tempJdc = jdcIterator.next();
			String id = tempJdc.getId();
			String text = tempJdc.getName();
			treeNodeJsonTemp.setId(id);
			treeNodeJsonTemp.setText(text);
			treeNodeJsonTemp.setIcon("icon-edit");
			HashMap<String,String> attributes = new HashMap<String,String>();
			if(tempJdc.getAddress()!=null){
				attributes.put("address", tempJdc.getAddress());
			}
			if(tempJdc.getFax()!=null){
				attributes.put("fax", tempJdc.getFax());
			}
			if(tempJdc.getOfficephone()!=null){
				attributes.put("officephone", tempJdc.getOfficephone());
			}
			if(tempJdc.getPostno()!=null){
				attributes.put("postno", tempJdc.getPostno());
			}
			if(tempJdc.getSynopsis()!=null){
				attributes.put("synopsis", tempJdc.getSynopsis());
			}
			if(tempJdc.getParent()!=null){
				attributes.put("parentid", tempJdc.getParent().getDepid());
			}
			
			treeNodeJsonTemp.setAttributes(attributes);
			list.add(treeNodeJsonTemp);
		}

		JSONArray ja = JSONArray.fromObject(list);
		// //Struts2Utils.renderText(ja.toString());
		return ja;
	}

	public JSONArray rootNode(String departname) throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		TreeNodeOrgDepJson treeNodeJson =new TreeNodeOrgDepJson();
		
		System.out.println("departname---->"+departname);
		System.out.println("depGeninfDao------>"+depGeninfDao);
		List<DepGeninf> jdcSet;
		if("".equals(departname)){
			jdcSet= depGeninfDao.getRootOj();
		}else{
			jdcSet= depGeninfDao.getRootOj(departname);
		}
		
		System.out.println("jdcSet------>"+jdcSet.size());
		///List<JmMonitorInfo> jdcSet= jmMonitorInfoService.getRootOj();
		Iterator<DepGeninf> jdcIterator = jdcSet.iterator();
		List<TreeNodeOrgDepJson> list  = new ArrayList<TreeNodeOrgDepJson>();
		while(jdcIterator.hasNext()){
			TreeNodeOrgDepJson treeNodeJsonTemp = new TreeNodeOrgDepJson();
			DepGeninf tempJdc= jdcIterator.next();
			////tempJdc.getChildren();
			String id = tempJdc.getDepid();
			String text =tempJdc.getName();
			//String ip = tempJdc.getIp();
			//String code = tempJdc.getCode();
			//String cannal = tempJdc.getCannal();
			System.out.println(id+"-------->"+text);
			treeNodeJsonTemp.setId(id);
			treeNodeJsonTemp.setText(text);
			treeNodeJsonTemp.setIcon("icon-edit");
			HashMap<String,String> attributes = new HashMap<String,String>();
			if(tempJdc.getAddress()!=null){
				attributes.put("address", tempJdc.getAddress());
			}
			if(tempJdc.getFax()!=null){
				attributes.put("fax", tempJdc.getFax());
			}
			if(tempJdc.getOfficephone()!=null){
				attributes.put("officephone", tempJdc.getOfficephone());
			}
			if(tempJdc.getPostno()!=null){
				attributes.put("postno", tempJdc.getPostno());
			}
			if(tempJdc.getSynopsis()!=null){
				attributes.put("synopsis", tempJdc.getSynopsis());
			}
			if(tempJdc.getParent()!=null){
				attributes.put("parentid", tempJdc.getParent().getDepid());
			}
			
			treeNodeJsonTemp.setAttributes(attributes);
			list.add(treeNodeJsonTemp);
		}
		
		//String id = jdc.getId();
		//String text =jdc.getName();
		treeNodeJson.setId("root");
		treeNodeJson.setText("组织架构");
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
	public JsonConfig getJsonConfigEx(TreeNodeOrgDepJson model) throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		JsonConfig jsonConfig = new JsonConfig();
		// 设置json无线嵌套避免
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		// 设置逃逸非基础的属性，保留String int double 等基础属性
		jsonConfig.setExcludes(BeanUtilEx.getNullPropertyNames(model));
		jsonConfig.setIgnoreDefaultExcludes(false);
		return jsonConfig;
	}

	public void save(DepGeninf model, String parentid) {
		// TODO Auto-generated method stub
		System.out.println("save parentid------>"+parentid);
		DepGeninf parent = depGeninfDao.findById(parentid);
		
		System.out.println("save parentid------>"+parent);
		model.setParent(parent);
		
		if (model.getCreator() == null || "".equals(model.getCreator())) {
			model.setCreator(creator);
		}
		if (model.getId() == null || "".equals(model.getId())) {
			model.setId(PrimaryProduce.produce());
			depGeninfDao.save(model);
		}else{
			
			String id = model.getId();
			
			DepGeninf entity = depGeninfDao.findById(id);
			
			BeanUtils.copyProperties(model, entity, BeanUtilEx.getNullPropertyNames(model));
			
			depGeninfDao.modify(model);
		}
	}
	public void remove(String myid){
		DepGeninf depGeninf = depGeninfDao.findById(myid);
		depGeninfDao.remove(depGeninf);
		
	}

}
