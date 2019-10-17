package com.gdyiko.base.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.base.po.JmDataconfigInfo;
import com.gdyiko.base.service.JmDataconfigInfoService;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.action.MyBaseAction;
import com.gdyiko.tool.easyui.DataGridColumn;
import com.gdyiko.tool.easyui.DataGridJson;
import com.gdyiko.tool.easyui.TreeNodeJson;
import com.gdyiko.tool.service.MyBaseGenericService;

@ParentPackage("custom-default")
@Namespace("/")
@Action(value = "Dataconfig", results = {
// 成功
		@Result(name = "success", location = "/projectweb/dataconfig_manager.jsp"),
		// 失败
		@Result(name = "fail", location = "/")

})
public class JmDataconfigInfoAction extends
		MyBaseAction<JmDataconfigInfo, String> {

	/**
	 * 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */

	private TreeNodeJson treeNodeJson;
	private static final long serialVersionUID = -7209385373170290085L;
	private String parentid;

	public JmDataconfigInfoAction() {
		// super();
		// 设置模块名称
		setModelTitle("配置管理");

		// 设置表格列
		List<DataGridColumn> columnList = new ArrayList<DataGridColumn>();
		columnList.add(new DataGridColumn("属性名", "name", "200"));
		columnList.add(new DataGridColumn("属性关键编码", "keystr", "100"));
		columnList.add(new DataGridColumn("属性值", "valuestr", "250"));
		columnList.add(new DataGridColumn("属性编码", "code", "80"));
		columnList.add(new DataGridColumn("描述", "describe", "200"));
		columnList.add(new DataGridColumn("备注 ", "remark", "200"));
		columnList.add(new DataGridColumn("id", "id", "20", true));
		setColumnJson(columnList);

		super.setModelListUrl("Dataconfig!showJsonDatagridListByPage.action");
		super.setModelSaveUrl("Dataconfig!save.action");
		super.setModelModifyUrl("Dataconfig!modify.action?myid=");
		super.setModelDelUrl("Dataconfig!remove.action");

	}

	@Resource(name = "jmDataconfigInfoService")
	private JmDataconfigInfoService jmDataconfigInfoService;

	@Resource(name = "jmDataconfigInfoService")
	@Override
	public void setGenericService(
			MyBaseGenericService<JmDataconfigInfo, String> genericService) {
		// TODO Auto-generated method stub
		super.setGenericService(genericService);
	}

	// 获得ID并且编列节点的一级
	public String subNode() throws IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		treeNodeJson = new TreeNodeJson();
		String tempid = super.model.getId();
		System.out.println("当前节点ID--->" + tempid);
		JmDataconfigInfo jdc = jmDataconfigInfoService.findById(tempid);
		Set<JmDataconfigInfo> jdcSet = jdc.getChildren();
		Iterator<JmDataconfigInfo> jdcIterator = jdcSet.iterator();
		List<TreeNodeJson> list = new ArrayList<TreeNodeJson>();
		while (jdcIterator.hasNext()) {
			TreeNodeJson treeNodeJsonTemp = new TreeNodeJson();
			JmDataconfigInfo tempJdc = jdcIterator.next();
			String id = tempJdc.getId();
			String text = tempJdc.getName();
			treeNodeJsonTemp.setId(id);
			treeNodeJsonTemp.setText(text);
			treeNodeJsonTemp.setIcon("icon-edit");
			list.add(treeNodeJsonTemp);
		}

		JSONArray ja = JSONArray.fromObject(list);
		Struts2Utils.renderText(ja.toString());
		return null;
	}

	// 获得ID并且编列节点的一级
	public String rootNode() throws IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		treeNodeJson = new TreeNodeJson();
		List<JmDataconfigInfo> jdcSet = jmDataconfigInfoService.getRootOj();
		Iterator<JmDataconfigInfo> jdcIterator = jdcSet.iterator();
		List<TreeNodeJson> list = new ArrayList<TreeNodeJson>();
		while (jdcIterator.hasNext()) {
			TreeNodeJson treeNodeJsonTemp = new TreeNodeJson();
			JmDataconfigInfo tempJdc = jdcIterator.next();
			// //tempJdc.getChildren();
			String id = tempJdc.getId();
			String text = tempJdc.getName();
			System.out.println(id + "-------->" + text);
			treeNodeJsonTemp.setId(id);
			treeNodeJsonTemp.setText(text);
			treeNodeJsonTemp.setIcon("icon-edit");
			list.add(treeNodeJsonTemp);
		}

		// String id = jdc.getId();
		// String text =jdc.getName();
		treeNodeJson.setId("root");
		treeNodeJson.setText("配置项目");
		treeNodeJson.setIcon("icon-edit");
		treeNodeJson.setChildren(list);
		// /List<TreeNodeJson> tree = new ArrayList<TreeNodeJson>();
		JsonConfig jsonConfig = getJsonConfigEx(treeNodeJson);

		JSONObject jsonObject = JSONObject.fromObject(treeNodeJson, jsonConfig);
		JSONArray ja = new JSONArray();
		ja.add(jsonObject);
		System.out.println(ja.toString());
		Struts2Utils.renderText(ja.toString());
		return null;
	}

	/*
	 * 获得 Datagrid 格式json默认
	 */
	public String showChildDatagridListByPage() throws RuntimeException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		List<JmDataconfigInfo> list = jmDataconfigInfoService.findChildByPage(
				model, model.getId(), this.getRows() * (this.getPage() - 1),
				this.getRows() * this.getPage());
		System.out.println("model.getId()：" + model.getId());
		int total = jmDataconfigInfoService.findChildBySize(model,
				model.getId());
		DataGridJson dgjo = new DataGridJson(list, total);
		System.out.println(dgjo.toString());
		JsonConfig jsonConfig = getJsonConfig(model);
		// 拦截重复的循环的数据
		JSONObject jsonObject = JSONObject.fromObject(dgjo, jsonConfig);
		System.out.println(jsonObject.toString());
		Struts2Utils.renderText(jsonObject.toString());
		return null;
	}

	/*
	 * 获得 Datagrid 格式json默认
	 */
	public String showTopDatagridListByPage() throws RuntimeException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		List<JmDataconfigInfo> list = jmDataconfigInfoService
				.findNotNullByPage(model, "parent",
						this.getRows() * (this.getPage() - 1), this.getRows()
								* this.getPage());
		// //System.out.println("查询数据量：" + list.size() + "page-->" + page +
		// "rows-->" + rows);
		int total = jmDataconfigInfoService.findNotNullBySize(model, "parent");
		DataGridJson dgjo = new DataGridJson(list, total);
		System.out.println(dgjo.toString());
		JsonConfig jsonConfig = getJsonConfig(model);
		// 拦截重复的循环的数据
		JSONObject jsonObject = JSONObject.fromObject(dgjo, jsonConfig);
		System.out.println(jsonObject.toString());
		Struts2Utils.renderText(jsonObject.toString());
		return null;
	}

	public JmDataconfigInfoService getJmDataconfigInfoService() {
		return jmDataconfigInfoService;
	}

	public void setJmDataconfigInfoService(
			JmDataconfigInfoService jmDataconfigInfoService) {
		this.jmDataconfigInfoService = jmDataconfigInfoService;
	}

	// 返回值类型设置
	public JsonConfig getJsonConfigEx(TreeNodeJson model)
			throws IllegalArgumentException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		JsonConfig jsonConfig = new JsonConfig();
		// 设置json无线嵌套避免
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		// 设置逃逸非基础的属性，保留String int double 等基础属性
		jsonConfig.setExcludes(BeanUtilEx.getNullPropertyNames(model));
		jsonConfig.setIgnoreDefaultExcludes(false);
		return jsonConfig;
	}

	@Override
	public String save() {
		// TODO Auto-generated method stub
		System.out.println(this.parentid);
		JmDataconfigInfo parent = jmDataconfigInfoService.findById(parentid);
		this.model.setParent(parent);
		return super.save();
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

}
