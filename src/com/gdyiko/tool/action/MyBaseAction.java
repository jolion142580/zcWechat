package com.gdyiko.tool.action;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.JsonUtil;
import com.gdyiko.tool.PrimaryProduce;
import com.gdyiko.tool.easyui.DataGridColumn;
import com.gdyiko.tool.easyui.DataGridJson;
import com.gdyiko.tool.po.GenericPo;
import com.gdyiko.tool.service.MyBaseGenericService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MyBaseAction<T extends GenericPo, ID extends Serializable> extends ActionSupport implements ModelDriven<T> {

	/**
	 * 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 * 
	 */
	private boolean isLazy=false;
	private static final long serialVersionUID = 1L;

	public  final String FLAG_SUCCESS = "1";

	public final String FLAG_FAIL = "0";

	private MyBaseGenericService<T, ID> myBaseGenericService;
	/**
	 * 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	protected T model;
	// 样式
	String theme = "default";
	// 基础数据定义通用模板定义
	String modelTitle = "公共模块";
	String modelAddTitle = "公共模块添加";
	String modelModifyTitle = "公共模块修改";
	String modelDelTitle = "公共模块删除";
	String modelListTitle = "公共模块列表";
	String modelListUrl = "";
	String modelSaveUrl = "";
	String modelModifyUrl = "";
	String modelDelUrl = "";
	String modelFindUrl = "";
	String creator  = "";
	String departname="";
	String columnJson = "";

	HttpSession session = null;

	int rows = 20;
	int page = 1;
	public  ID myid;

	public MyBaseAction()  {
		try {
			// 通过反射获取T的实际类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// /log.debug("model的实际类型为：" + clazz);

			// 通过反射生成对象实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
		session = ServletActionContext.getRequest().getSession();
		// /session.getAttribute("user");
		System.out.println("初始化当前录入人员");
		
		///System.out.println(session.getAttribute("user"));
		/* modify by readinho 
		User user = (User)session.getAttribute("user");
		this.cyuser = (JmCydepartmentInfo)session.getAttribute("cyuser");
		 String departName="";
		if(user!=null){
			//设置创建者名称
			try{
			 afc = new AFControlUtil();
			 String departid = afc.getDepartIdByMemberID(user.getMemberId());
			  departName = afc.getDepartNameByID(departid);
			}catch(Exception ex){
				
				
			}
			 System.out.println("内部登陆单位："+departName);
			 departName = departName.replaceAll("食品药品监督管理局", "").replaceAll("教育局", "");
			 if("江门市".equals(departName)){
				 departName = "";
			 }
			this.setDepartname(departName);
			this.setCreator(user.getCName());
		}
		
		if(cyuser!=null){
			//设置创建者名称
			this.setCreator(cyuser.getCydwmc());
		}
		*/

		System.out.println("样式--->" + (String) session.getAttribute("theme"));
		if (session.getAttribute("theme") != null && "".equals(session.getAttribute("theme")) == false) {
			this.setTheme((String) session.getAttribute("theme"));
		}
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		/*
		session = ServletActionContext.getRequest().getSession();
		
		System.out.println("初始化当前录入人员");
	
		User user = (User)session.getAttribute("user");
		this.cyuser = (JmCydepartmentInfo)session.getAttribute("cyuser");
		if(user!=null){
			//设置创建者名称
			this.setCreator(user.getCName());
		}
		
		if(cyuser!=null){
			//设置创建者名称
			this.setCreator(cyuser.getCydwmc());
		}

		System.out.println("样式--->" + (String) session.getAttribute("theme"));
		if (session.getAttribute("theme") != null && "".equals(session.getAttribute("theme")) == false) {
			this.setTheme((String) session.getAttribute("theme"));
		}
		*/
		return super.execute();
	}

	public T getModel() {
		return model;
	}



	public String getModelTitle() {
		return modelTitle;
	}

	public void setModelTitle(String modelTitle) {
		this.modelTitle = modelTitle;
	}

	public MyBaseGenericService<T, ID> getGenericService() {
		return myBaseGenericService;
	}

	public void setGenericService(MyBaseGenericService<T, ID> genericService) {
		this.myBaseGenericService = genericService;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getModelAddTitle() {
		return modelAddTitle;
	}

	public void setModelAddTitle(String modelAddTitle) {
		this.modelAddTitle = modelAddTitle;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getModelModifyTitle() {
		return modelModifyTitle;
	}

	public void setModelModifyTitle(String modelModifyTitle) {
		this.modelModifyTitle = modelModifyTitle;
	}

	public String getModelDelTitle() {
		return modelDelTitle;
	}

	public void setModelDelTitle(String modelDelTitle) {
		this.modelDelTitle = modelDelTitle;
	}

	public String getModelListTitle() {
		return modelListTitle;
	}

	public void setModelListTitle(String modelListTitle) {
		this.modelListTitle = modelListTitle;
	}

	public String getModelListUrl() {
		return modelListUrl;
	}
	
	
	public boolean isLazy() {
		return isLazy;
	}

	public void setLazy(boolean isLazy) {
		this.isLazy = isLazy;
	}

	public void setModelListUrl(String modelListUrl) {
		this.modelListUrl = modelListUrl;
	}

	public String getModelSaveUrl() {
		return modelSaveUrl;
	}

	public void setModelSaveUrl(String modelSaveUrl) {
		this.modelSaveUrl = modelSaveUrl;
	}

	public String getModelModifyUrl() {
		return modelModifyUrl;
	}

	public void setModelModifyUrl(String modelModifyUrl) {
		this.modelModifyUrl = modelModifyUrl;
	}

	public String getModelDelUrl() {
		return modelDelUrl;
	}

	public void setModelDelUrl(String modelDelUrl) {
		this.modelDelUrl = modelDelUrl;
	}

	public String getModelFindUrl() {
		return modelFindUrl;
	}

	public void setModelFindUrl(String modelFindUrl) {
		this.modelFindUrl = modelFindUrl;
	}

	public ID getMyid() {
		return myid;
	}

	public void setMyid(ID myid) {
		this.myid = myid;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public String getColumnJson() {
		return columnJson;
	}

	
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDepartname() {
		return departname;
	}

	public void setDepartname(String departname) {
		this.departname = departname;
	}

	public void setColumnJson(List<DataGridColumn> columnList) {
		// /JSONObject jsonObject = JSONObject.fromObject(columnList);
		JSONArray ja = JSONArray.fromObject(columnList);
		System.out.println(ja.toString());
		this.columnJson = ja.toString();

	}

	/*
	 * 获得一条数据
	 */
	public String findById() throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

		System.out.println(myid);
		T entity = myBaseGenericService.findById(myid);
		JsonConfig jsonConfig = getJsonConfig(model);
		JSONObject jsonObject = JSONObject.fromObject(entity, jsonConfig);
		// JSONObject jsonObject = JSONObject.fromObject(entity);
		Struts2Utils.renderText(jsonObject.toString());
		//Struts2Utils.RE
		return null;
	}

	/*
	 * 增加 数据利用MODEL传入
	 */
	public String save() {
		System.out.println("泛型保存save中");
		System.out.println("登陆用户---"+creator);
		// 没有主键补充
		if (this.model.getId() == null || "".equals(this.model.getId())) {
			this.model.setId(PrimaryProduce.produce());
		}
		
/*		if (this.model.getCreator() == null || "".equals(this.model.getCreator())) {
			this.model.setCreator(creator);
		}*/
		// //System.out.println(JSONObject.fromObject(model).toString());
		try {

			myBaseGenericService.save(model);
			System.out.println(getResultJson(FLAG_SUCCESS));
			Struts2Utils.renderText(getResultJson(FLAG_SUCCESS));
		} catch (Exception exception) {
			exception.printStackTrace();
			Struts2Utils.renderText(getResultJson(FLAG_FAIL));
			
		}
		return null;
	}

	/*
	 * 删除
	 */
	public String remove() {
		try {
			T entity = myBaseGenericService.findById(myid);
			myBaseGenericService.remove(entity);
			System.out.println(getResultJson(FLAG_SUCCESS));
			Struts2Utils.renderText(getResultJson(FLAG_SUCCESS));
		} catch (Exception exception) {
			System.out.println(exception);
			exception.getStackTrace();
			Struts2Utils.renderText(getResultJson(FLAG_FAIL));
			
		}
		return null;
	}

	/*
	 * 更新 需要传入两个东西 表单以及ID ID单独传入
	 */
	public String modify() {
		////try {

			T entity = myBaseGenericService.findById(myid);
			
			BeanUtils.copyProperties(model, entity, BeanUtilEx.getNullPropertyNames(model));
			////System.out.println();
			myBaseGenericService.modify(entity);
			Struts2Utils.renderText(getResultJson(FLAG_SUCCESS));
		///} catch (Exception exception) {
		//	exception.getStackTrace();
		//	Struts2Utils.renderText(getResultJson(FLAG_FAIL));
		//}
		return null;
	}
	
	public String modifyEntity() {
		try {

			///T entity = genericService.findById(myid);
			///BeanUtils.copyProperties(model, entity, BeanUtilEx.getNullPropertyNames(model));
			myBaseGenericService.modify(model);
			Struts2Utils.renderText(getResultJson(FLAG_SUCCESS));
		} catch (Exception exception) {
			exception.getStackTrace();
			Struts2Utils.renderText(getResultJson(FLAG_FAIL));
		}
		return null;
	}

	/*
	 * 获得 Datagrid 格式json默认
	 */
	public String showJsonDatagridList() throws RuntimeException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		List<T> list = myBaseGenericService.findAll();
		int total = myBaseGenericService.getTotalRows();
		System.out.println("查询数据量：" + list.size());
		DataGridJson dgjo = new DataGridJson(list, total);
		///System.out.println(dgjo.toString());
		JsonConfig jsonConfig = getJsonConfig(model);
		JSONObject jsonObject = JSONObject.fromObject(dgjo, jsonConfig);
		/////System.out.println(jsonObject.toString());
		Struts2Utils.renderText(jsonObject.toString());
		return null;
	}
	
	
	public String showJsonDatagrid() throws RuntimeException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		System.out.println("查询数据量：page-->" + page + "rows-->" + rows);
		myBaseGenericService.findByPage(page, rows);
		List<T> list =myBaseGenericService.findByPage(page, rows);
		int total = myBaseGenericService.getTotalRows();
		System.out.println("查询数据量：" + list.size());
		DataGridJson dgjo = new DataGridJson(list, total);
		JsonConfig jsonConfig = getJsonConfig(model);
		JSONObject jsonObject = JSONObject.fromObject(dgjo, jsonConfig);
		Struts2Utils.renderText(jsonObject.toString());
		return null;
	}

	/*
	 * 获得 Datagrid 格式json默认
	 */
	public String showJsonDatagridListByPage() throws RuntimeException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		///System.out.println("----------->" + JSONObject.fromObject(model));
		List<T> list = myBaseGenericService.findLikeByEntity(model, BeanUtilEx.getNotNullEscapePropertyNames(model), rows * (page - 1), rows );
		System.out.println("查询数据量：" + list.size() + "page-->" + page + "rows-->" + rows);
		int total = myBaseGenericService.findLikeByEntitySize(model, BeanUtilEx.getNotNullEscapePropertyNames(model));
		DataGridJson dgjo = new DataGridJson(list, total);
		JsonConfig jsonConfig = getJsonConfig(model);
		// 拦截重复的循环的数据
		JSONObject jsonObject = JSONObject.fromObject(dgjo, jsonConfig);
		Struts2Utils.renderText(jsonObject.toString());
		return null;
	}

	// 返回结果JSON
	public String getResultJson(String flag) {
		HashMap<String, String> hp = new HashMap<String, String>();
		hp.put("flag", flag);
		return JSONObject.fromObject(hp).toString();
	}

	// 返回值类型设置
	public JsonConfig getJsonConfig(T model) throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		JsonConfig jsonConfig = new JsonConfig();
		// 设置json无线嵌套避免
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String excludeStr = "jmFileBsnLinks,jmSupplierInfos,jmMaterialInfos,jmCydepartmentWorkerInfos,jmCydepartmentUserInfos,handler,hibernateLazyInitializer,fieldHandler";
		// 设置逃逸非基础的属性，保留String int double 等基础属性
		if(isLazy){
			String [] notload = JsonUtil.getNotBaseFieldList(model);
			for (String string : notload) {
				excludeStr = excludeStr +","+string;
			}

		}
		System.out.print(excludeStr);
		jsonConfig.setExcludes(excludeStr.split(",")); 
		return jsonConfig;
	}
	//读取列
	public String dgcolumn(){
		String columnJson= this.getColumnJson();
		Struts2Utils.renderText(columnJson);
		return null;
		
	}

}
