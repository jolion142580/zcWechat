package com.gdyiko.tool.easyui;

import java.util.List;

public class DataGridJson {
	int total = 0;
	List rows = null;
	
	public DataGridJson(List list,int total){
		this.total = total;
		this.rows = list;
		
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

}
