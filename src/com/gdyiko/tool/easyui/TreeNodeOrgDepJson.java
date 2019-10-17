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
public class TreeNodeOrgDepJson {
	
	private String id;
	private String text;
	private String state="closed";
	private boolean checked =true;
	private HashMap attributes ;
	private String icon;
	private List children;
	private String address ;
	private String fax ;
	private String officephone ;
	private String postno ;
	private String synopsis ; 
	private String parentid;
	
	
	
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
	public List<TreeNodeOrgDepJson> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNodeOrgDepJson> children) {
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getOfficephone() {
		return officephone;
	}
	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}
	public String getPostno() {
		return postno;
	}
	public void setPostno(String postno) {
		this.postno = postno;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	
}
