package com.gdyiko.base.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.base.po.MenuInfo;
import com.gdyiko.base.service.MenuInfoService;
import com.gdyiko.tool.action.MyBaseAction;
import com.gdyiko.tool.easyui.DataGridColumn;
import com.gdyiko.tool.easyui.TreeNodeMonitorJson;
import com.gdyiko.tool.service.MyBaseGenericService;

@ParentPackage("custom-default")  
@Namespace("/")
@Action(value="menuinfo",results={
	    //成功
		
		/*@Result(name="success" ,location = "/projectweb/menuinfo_manager.jsp"),
		
		@Result(name="see" ,location = "/projectweb/menuinfo_see.jsp"),
		
		@Result(name="monitor" ,location = "/projectweb/menuinfo.jsp"),
		//失败
		@Result(name="fail" ,location = "/")
		*/
	})
public class MenuInfoAction extends MyBaseAction<MenuInfo, String> {

	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
TreeNodeMonitorJson treeNodeJson;
	
	private String parentid;
	
	
	
	
	private static final long serialVersionUID = -7209385373170290085L;
	
	public MenuInfoAction(){
		//super();
		//设置模块名称 
		setModelTitle("人员架构管理");
		setModelListTitle("部门职务管理");
		

	   //设置表格列
	List<DataGridColumn> columnList = new ArrayList<DataGridColumn>();
	   ///columnList.add(new DataGridColumn("视频名","monitorName","200"));
	   columnList.add(new DataGridColumn("菜单名称","name","200"));
	   columnList.add(new DataGridColumn("连接","link","200"));
	   columnList.add(new DataGridColumn("id", "id", "20", true));
	   setColumnJson(columnList);
	   
	  // super.setModelListUrl("Monitor!showJsonDatagridListByPage.action");
	   super.setModelSaveUrl("menuinfo!save.action");
	   super.setModelDelUrl("menuinfo!remove.action");
	   //很重要 设置懒加载不然读取全部
		super.setLazy(true);
	}

	
	@Resource(name="menuInfoService")
	private MenuInfoService menuInfoService;
	/*
	@Resource(name="depGeninfService")
	@Override
	public void setGenericService(GenericService<DepGeninf, String> genericService) {
		// TODO Auto-generated method stub
		super.setGenericService(genericService);
	}
	*/
	
	//获得ID并且编列节点的一级
	public String subNode() throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		 //////treeNodeJson = new TreeNodeMonitorJson();
		String tempid = super.model.getId();
		System.out.println("当前节点ID11111--->"+tempid);
		JSONArray ja =menuInfoService.subNode(tempid);
		Struts2Utils.renderText(ja.toString());
		return null;
	}
	
	public String rootNode() throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{

		JSONArray ja =null;

		 ja =menuInfoService.rootNode("");
		Struts2Utils.renderText(ja.toString());
		return null;
	}

	public TreeNodeMonitorJson getTreeNodeJson() {
		return treeNodeJson;
	}

	public void setTreeNodeJson(TreeNodeMonitorJson treeNodeJson) {
		this.treeNodeJson = treeNodeJson;
	}


	
	
	public MenuInfoService getMenuInfoService() {
		return menuInfoService;
	}

	public void setMenuInfoService(MenuInfoService menuInfoService) {
		this.menuInfoService = menuInfoService;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	//保存数据
	@Override
	public String save() {
		// TODO Auto-generated method stub
		try {
			
			menuInfoService.save(this.model,this.parentid);
			System.out.println(getResultJson(this.FLAG_SUCCESS));
			Struts2Utils.renderText(getResultJson(super.FLAG_SUCCESS));
		} catch (Exception exception) {
			exception.printStackTrace();
			Struts2Utils.renderText(getResultJson(super.FLAG_FAIL));
			
		}
		return null;
		
	}
	//删除数据
	@Override
	public String remove() {
		try {
			System.out.println("remove:id="+this.model.getId());
			menuInfoService.remove(this.model.getId());
			System.out.println(getResultJson(FLAG_SUCCESS));
			Struts2Utils.renderText(getResultJson(FLAG_SUCCESS));
		} catch (Exception exception) {
			System.out.println(exception);
			exception.getStackTrace();
			Struts2Utils.renderText(getResultJson(FLAG_FAIL));
			
		}
		return null;
	}
	
	
	@Resource(name="menuInfoService")
	@Override
	public void setGenericService(MyBaseGenericService<MenuInfo, String> genericService) {
		// TODO Auto-generated method stub
		super.setGenericService(genericService);
	}
	
	//显示分类为1的
	@Override
	public String showJsonDatagridListByPage() throws RuntimeException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		this.model.setType("1");
		return super.showJsonDatagridListByPage();
	}
	
	
	
}
