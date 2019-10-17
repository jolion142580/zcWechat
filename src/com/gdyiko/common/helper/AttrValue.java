package com.gdyiko.common.helper;

/**
 * 用于Excel导出的辅助类
 */
public class AttrValue {

	private String colName;
	
	private String name;
	
	public AttrValue() {

	}


	/**
	 * 构造函数
	 * 
	 * @param name
	 *            显示列名，在Excel内的显示，可以是任何文字
	 */
	public AttrValue(String colName,String name) {
		this.colName = colName;
		this.name = name;
	}

	/**
	 * 显示列名，在Excel内的显示，可以是任何文字
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}


	/**
	 * 表列名
	 * 
	 * @return
	 */
	public String getColName() {
		return colName;
	}


	public void setColName(String colName) {
		this.colName = colName;
	}
	
	
	/**
	 * 列值统一转换方法
	 * 
	 * @return
	 */
	public String convert(Object param) {
		if (param != null) {
			return param.toString();
		}
		return "";
	}


}
