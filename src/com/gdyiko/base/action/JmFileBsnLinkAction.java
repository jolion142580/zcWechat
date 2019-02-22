package com.gdyiko.base.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.gdyiko.base.po.JmFileBsnLink;
import com.gdyiko.tool.action.MyBaseAction;
import com.gdyiko.tool.easyui.DataGridColumn;
import com.gdyiko.tool.service.MyBaseGenericService;

@ParentPackage("custom-default")
@Namespace("/")
@Action(value = "File", results = {
// 成功
		@Result(name = "success", location = "/projectweb/file_manager.jsp"),
		// 失败
		@Result(name = "fail", location = "/")

},
interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class JmFileBsnLinkAction extends MyBaseAction<JmFileBsnLink, String> {

	/**
	 * 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private String bsn_id;
	
	private String bsn_type;

	// /String id = "";
	public JmFileBsnLinkAction() {
		HttpServletRequest request = ServletActionContext.getRequest();
		bsn_id = request.getParameter("bsn_id");
		bsn_type = request.getParameter("bsn_type");
		// super();
		// 设置模块名称
		setModelTitle("附件管理");

		// 设置表格列
		List<DataGridColumn> columnList = new ArrayList<DataGridColumn>();
		columnList.add(new DataGridColumn("id", "id", "20", true));
		columnList.add(new DataGridColumn("文件id", "file_id", "200", true));
		columnList.add(new DataGridColumn("业务id", "bsn_id", "200", true));
		columnList.add(new DataGridColumn("业务分类", "bsn_type", "200", true));
		columnList.add(new DataGridColumn("文件名", "displayname", "250"));
		columnList.add(new DataGridColumn("备注", "remark", "80"));
		columnList.add(new DataGridColumn("创建人", "creator", "150"));
		columnList.add(new DataGridColumn("创建时间", "creattime", "200"));
		setColumnJson(columnList);

		super.setModelListUrl("File!showJsonDatagridListByPage.action?bsn_id="+bsn_id+"&bsn_type="+bsn_type);
		super.setModelSaveUrl("File!save.action");
		super.setModelModifyUrl("File!modify.action?myid=");
		super.setModelDelUrl("File!remove.action");

		super.setLazy(false);
		
		

	}

	private static final long serialVersionUID = -7209385373170290085L;

	@Resource(name = "jmFileBsnLinkService")
	@Override
	public void setGenericService(MyBaseGenericService<JmFileBsnLink, String> genericService) {
		// TODO Auto-generated method stub
		super.setGenericService(genericService);
	}

	public String showJsonDatagridListByPage() throws RuntimeException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		///System.out.println("附件加载-------->" + bsn_id + bsn_type);
		///this.model.setBsn_id(bsn_id);
		////this.model.setBsn_type(bsn_type);

		////this.model.setBsn_id(bsn_id);
		//////this.model.setBsn_type(bsn_type);

		return super.showJsonDatagridListByPage();
	}

	public String getBsn_id() {
		return bsn_id;
	}

	public void setBsn_id(String bsn_id) {
		this.bsn_id = bsn_id;
	}

	public String getBsn_type() {
		return bsn_type;
	}

	public void setBsn_type(String bsn_type) {
		this.bsn_type = bsn_type;
	}
	
}
