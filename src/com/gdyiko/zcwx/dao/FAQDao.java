package com.gdyiko.zcwx.dao;

import java.util.List;

import com.gdyiko.zcwx.po.FAQ;
import com.gdyiko.tool.dao.GenericDao;

public interface FAQDao extends GenericDao<FAQ, String> {

	List<FAQ> findAnswer(String string);
	//public List<FAQ> findAnswer(String problems); 
}
