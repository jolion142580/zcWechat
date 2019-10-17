package com.gdyiko.base.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.base.po.DepGeninf;
import com.gdyiko.base.service.DepGeninfService;
import com.gdyiko.tool.action.MyBaseAction;
import com.gdyiko.tool.easyui.DataGridColumn;
import com.gdyiko.tool.easyui.TreeNodeMonitorJson;

@ParentPackage("custom-default")  
@Namespace("/")
@Action(value="DepGeninf",results={
	    //成功
		@Result(name="success" ,location = "/projectweb/org_manager.jsp"),
		//失败
		@Result(name="fail" ,location = "/")
		
	})
public class DepGeninfAction extends MyBaseAction<DepGeninf, String> {

	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	TreeNodeMonitorJson treeNodeJson;
	
	private String parentid;
	
	
	
	
	private static final long serialVersionUID = -7209385373170290085L;
	
	public DepGeninfAction(){
		//super();
		//设置模块名称 
		setModelTitle("人员架构管理");
		setModelListTitle("部门职务管理");
		

	   //设置表格列
	List<DataGridColumn> columnList = new ArrayList<DataGridColumn>();
	   columnList.add(new DataGridColumn("视频名","monitorName","200"));
	   columnList.add(new DataGridColumn("ip地址","ip","100"));
	   columnList.add(new DataGridColumn("通道","cannal","100"));
	   columnList.add(new DataGridColumn("编码","code","150"));
	   columnList.add(new DataGridColumn("位置","position","200"));
	   columnList.add(new DataGridColumn("描述 ","describe","200"));
	   columnList.add(new DataGridColumn("id","id","20",true));	
	   setColumnJson(columnList);
	   
	  // super.setModelListUrl("Monitor!showJsonDatagridListByPage.action");
	   super.setModelSaveUrl("DepGeninf!save.action");
	   super.setModelDelUrl("DepGeninf!remove.action");
		
	}

	
	@Resource(name="depGeninfService")
	private DepGeninfService depGeninfService;
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
		String tempid = super.model.getDepid();
		System.out.println("当前节点ID11111--->"+tempid);
		JSONArray ja =depGeninfService.subNode(tempid);
		//Struts2Utils.renderText(ja.toString().replaceAll(",\"children\":\\[\\]", ""));
		Struts2Utils.renderText(ja.toString());
		return null;
	}
	
	public String rootNode() throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		String departname = this.getDepartname();
		System.out.println("departname---->"+departname);
		JSONArray ja =null;
		if(departname==null){
			departname="";
		}
		
		 ja =depGeninfService.rootNode(departname);
		 Struts2Utils.renderText(ja.toString());
		//Struts2Utils.renderText(ja.toString().replaceAll(",\"children\":\\[\\]", ""));
		return null;
	}

	public TreeNodeMonitorJson getTreeNodeJson() {
		return treeNodeJson;
	}

	public void setTreeNodeJson(TreeNodeMonitorJson treeNodeJson) {
		this.treeNodeJson = treeNodeJson;
	}

	public DepGeninfService getDepGeninfService() {
		return depGeninfService;
	}

	public void setDepGeninfService(DepGeninfService depGeninfService) {
		this.depGeninfService = depGeninfService;
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

			depGeninfService.save(this.model,this.parentid);
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
			depGeninfService.remove(this.model.getId());
			System.out.println(getResultJson(FLAG_SUCCESS));
			Struts2Utils.renderText(getResultJson(FLAG_SUCCESS));
		} catch (Exception exception) {
			System.out.println(exception);
			exception.getStackTrace();
			Struts2Utils.renderText(getResultJson(FLAG_FAIL));
			
		}
		return null;
	}
	


	
}
