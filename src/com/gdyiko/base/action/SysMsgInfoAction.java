package com.gdyiko.base.action;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Id;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.base.po.MenuInfo;
import com.gdyiko.base.po.RoleInfo;
import com.gdyiko.base.po.SysMsgInfo;
import com.gdyiko.base.service.RoleInfoService;
import com.gdyiko.base.service.SysMsgInfoService;
import com.gdyiko.tool.action.MyBaseAction;
import com.gdyiko.tool.easyui.DataGridColumn;
import com.gdyiko.tool.service.MyBaseGenericService;

@ParentPackage("custom-default") 
@Namespace("/")
@Action(value="SysMsgInfo",results={
	    //成功
		@Result(name="success" ,location = "/projectweb/sys_msg_manager.jsp"),
		//失败
		@Result(name="fail" ,location = "/")
		
	})
public class SysMsgInfoAction   extends MyBaseAction<SysMsgInfo, String> {
	

	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	@Resource(name="sysMsgInfoService")
	SysMsgInfoService sysMsgInfoService;
	

	
	private static final long serialVersionUID = -1478973090188121979L;
	


	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	
	public SysMsgInfoAction() {
		// TODO Auto-generated constructor stub
		setModelTitle("系统日志管理");
		
		
		   //设置表格列
		   List<DataGridColumn> columnList = new ArrayList<DataGridColumn>();
		   columnList.add(new DataGridColumn("创建人","creater","100"));
		   columnList.add(new DataGridColumn("消息","message","550"));
		   columnList.add(new DataGridColumn("创建时间","creattime","160"));
		   columnList.add(new DataGridColumn("面向用户","msguser","100"));
		   columnList.add(new DataGridColumn("id", "id", "80",true));
		   setColumnJson(columnList);
		   
		   super.setModelListUrl("SysMsgInfo!showJsonDatagridListByPage.action");
		   super.setModelSaveUrl("SysMsgInfo!save.action");
		   super.setModelModifyUrl("SysMsgInfo!modify.action?myid=");
		   super.setModelDelUrl("SysMsgInfo!remove.action");
		   super.setLazy(true);
	}

	@Resource(name="sysMsgInfoService")
	@Override
	public void setGenericService(MyBaseGenericService<SysMsgInfo, String> genericService) {
		// TODO Auto-generated method stub
		super.setGenericService(genericService);
	}

	

	
}
