package com.gdyiko.zcwx.mapping;


public class SsBaseMapping {
	
  public static void main(String[] args) {
	
}

  public  static String getDepNameByMap(String map){
	  //ss;bn;xn;lb;lp;dt;ns;ydh
	  if("ss".equals(map)){
		  return "三水";
	  }
	  if("bn".equals(map)){
		  return "白坭";
	  }
	  if("xn".equals(map)){
		  return "西南";
	  }
	  if("lb".equals(map)){
		  return "芦苞";
	  }
	  if("lp".equals(map)){
		  return "乐平";
	  }
	  if("dt".equals(map)){
		  return "大塘";
	  }
	  if("ns".equals(map)){
		  return "南山";
	  }
	  if("ydh".equals(map)){
		  return "云东海";
	  }
	  return "三水";
  }
}
