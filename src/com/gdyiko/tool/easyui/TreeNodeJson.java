package com.gdyiko.tool.easyui;

import java.util.HashMap;
import java.util.List;
/**
 * 
*    
* 项目名称：ssfydc   
* 类名称：TreeNodeJson   
* 类描述：   封装TREENODE的数据
* 创建人：asus   
* 创建时间：2015-9-8 上午10:19:21   
* 修改人：asus   
* 修改时间：2015-9-8 上午10:19:21   
* 修改备注：   
* @version    
*
 */
public class TreeNodeJson {
	
	private String id;
	private String text;
	private String state="closed";
	private boolean checked =true;
	private HashMap attributes ;
	private String icon;
	private String iconCls; 
	private List children;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<TreeNodeJson> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNodeJson> children) {
		this.children = children;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public HashMap getAttributes() {
		return attributes;
	}
	public void setAttributes(HashMap attributes) {
		this.attributes = attributes;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	
}
