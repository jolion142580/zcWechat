package com.gdyiko.zcwx.service;

import com.gdyiko.zcwx.po.FAQ;
import com.gdyiko.tool.service.GenericService;

public interface FAQService extends GenericService<FAQ,String>{

	/**
	 * 查找答案
	 * @param problems
	 * @return
	 */
	public String findAnswer(String problems);
	
//	public String findProblem(String problems);
	
}
