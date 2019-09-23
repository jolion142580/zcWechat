package com.gdyiko.zcwx.service;

import com.gdyiko.tool.service.GenericService;
import com.gdyiko.zcwx.po.Days;

public interface DaysService extends GenericService<Days, String> {
	
	public String saveInfo(String Jban, String Jjia);
	
	//public String findDays(String name);
	
	public String showdays(String name);

}
