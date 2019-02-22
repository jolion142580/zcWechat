package com.gdyiko.tool;

import java.util.ArrayList;
import java.util.List;

public class PageVo<T> {
   private Integer totalCount;
   private List<T> data =new ArrayList<T>();
   
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
}
