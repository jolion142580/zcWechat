package com.gdyiko.base.action;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.base.po.MenuInfo;
import com.gdyiko.base.po.RoleInfo;
import com.gdyiko.base.service.RoleInfoService;
import com.gdyiko.tool.action.MyBaseAction;
import com.gdyiko.tool.easyui.DataGridColumn;
import com.gdyiko.tool.service.MyBaseGenericService;

@ParentPackage("custom-default") 
@Namespace("/")
@Action(value="RoleInfo",results={
	    //成功
		@Result(name="success" ,location = "/projectweb/mem_manager.jsp"),
		//失败
		@Result(name="fail" ,location = "/")
		
	})
public class RoleInfoAction   extends MyBaseAction<RoleInfo, String> {
	

	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	@Resource(name="roleInfoService")
	RoleInfoService roleInfoService;
	
	String roleid ;
	
	String menuids;
	
	private static final long serialVersionUID = -1478973090188121979L;
	


	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	
	public RoleInfoAction() {
		// TODO Auto-generated constructor stub
		setModelTitle("权限角色管理");

		   //设置表格列
		   List<DataGridColumn> columnList = new ArrayList<DataGridColumn>();
		   columnList.add(new DataGridColumn("角色名","name","250"));
		   columnList.add(new DataGridColumn("编码","code","60", true));
		   columnList.add(new DataGridColumn("分类","type","60", true));
		   columnList.add(new DataGridColumn("分级","rolelevel","60", true));
		   columnList.add(new DataGridColumn("描述","description","200"));
		   columnList.add(new DataGridColumn("创建人","creator","60", true));
		   columnList.add(new DataGridColumn("id", "id", "20", true));
		   setColumnJson(columnList);
		   
		   super.setModelListUrl("RoleInfo!showJsonDatagridListByPage.action");
		   super.setModelSaveUrl("RoleInfo!save.action");
		   super.setModelModifyUrl("RoleInfo!modify.action?myid=");
		   super.setModelDelUrl("RoleInfo!remove.action");
		   super.setLazy(true);
	}

	@Resource(name="roleInfoService")
	@Override
	public void setGenericService(MyBaseGenericService<RoleInfo, String> genericService) {
		// TODO Auto-generated method stub
		super.setGenericService(genericService);
	}

	public String saveMenuInfos(){
		roleInfoService.saveMenuInfos(roleid, menuids);
		return null;
	}

	public RoleInfoService getRoleInfoService() {
		return roleInfoService;
	}

	public void setRoleInfoService(RoleInfoService roleInfoService) {
		this.roleInfoService = roleInfoService;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getMenuids() {
		return menuids;
	}

	public void setMenuids(String menuids) {
		this.menuids = menuids;
	}
	public String getMenuInfosById(){
		RoleInfo roleInfo = (RoleInfo) roleInfoService.findById(roleid);
		Set<MenuInfo> menuInfos =roleInfo.getMenuInfos();
		System.out.println("---->"+menuInfos.size());
		Iterator<MenuInfo> iterator = menuInfos.iterator();
		String ids= "";
		while (iterator.hasNext()) {
			MenuInfo menuInfo  = iterator.next();
			String tempid = menuInfo.getId();
			ids = ids+tempid+";";
		}
		Struts2Utils.renderText(ids);
		return null;
	}
	
}
