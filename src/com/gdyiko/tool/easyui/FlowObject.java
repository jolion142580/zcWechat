package com.gdyiko.tool.easyui;

import java.util.HashMap;
import java.util.List;

public class FlowObject {
	String title;
	HashMap<String,Object> nodes; 
	HashMap<String,Object> areas;
	HashMap<String,Object> lines;
	
	public FlowObject(String title,HashMap<String,Object> childrens,HashMap<String,Object> slines,HashMap<String,Object> areas){
		setTitle(title);
		setNodes(childrens);
		setAreas(areas);
		setLines(slines);
		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public HashMap<String,Object> getNodes() {
		return nodes;
	}
	public void setNodes(HashMap<String,Object> childrens) {
		this.nodes = childrens;
	}
	public HashMap<String,Object> getAreas() {
		return areas;
	}
	public void setAreas(HashMap<String,Object> areas) {
		this.areas = areas;
	}
	public HashMap<String,Object> getLines() {
		return lines;
	}
	public void setLines(HashMap<String,Object> slines) {
		this.lines = slines;
	}
	
}
