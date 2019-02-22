package com.gdyiko.tool.easyui;

public class DataGridColumn {
	String title = "";
	String field = "";
	String width = "80";
	String align = "center";
	String halign = "center";
	String formatter = "";

	boolean sortable = true;
	boolean resizable = true;
	boolean hidden = false;
	

	public DataGridColumn(String title,String field,String width,boolean hidden){
		this.setTitle(title);
		this.setField(field);
		this.setWidth(width);
		this.setHidden(hidden);
		
	}
	
	public DataGridColumn(String title,String field,String width,boolean hidden,String align){
		this.setTitle(title);
		this.setField(field);
		this.setWidth(width);
		this.setHidden(hidden);
		this.setAlign( align);
		
	}

	public DataGridColumn(String title,String field,String width){
		this.setTitle(title);
		this.setField(field);
		this.setWidth(width);
		
	}
	
	public DataGridColumn(String title,String field){
		this.setTitle(title);
		this.setField(field);
	}

	public DataGridColumn(String title,String field,String width,String align,String halign){
		this.setTitle(title);
		this.setField(field);
		this.setWidth(width);
		this.setAlign(align);
		this.setHalign(halign);
	}

	public DataGridColumn(String title,String field,String width,String align,String halign,String formatter){
		this.setTitle(title);
		this.setField(field);
		this.setWidth(width);
		this.setAlign(align);
		this.setHalign(halign);
		this.setFormatter(formatter);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public boolean isResizable() {
		return resizable;
	}

	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public String getHalign() {
		return halign;
	}

	public void setHalign(String halign) {
		this.halign = halign;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
	
	
}
