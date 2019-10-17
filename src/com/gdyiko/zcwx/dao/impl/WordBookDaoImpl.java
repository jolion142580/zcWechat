package com.gdyiko.zcwx.dao.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.gdyiko.zcwx.dao.WordBookDao;
import com.gdyiko.zcwx.po.WordBook;
import com.gdyiko.tool.dao.impl.GenericDaoImpl;


@Repository("wordBookDao")
public class WordBookDaoImpl extends GenericDaoImpl<WordBook, String> implements WordBookDao {

	@Override
	public Class<WordBook> getEntityClass() {
		// TODO Auto-generated method stub
		return WordBook.class;
	}
	
	
/*	public static void main(String[]xxxx){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		WordBookDao wordBookDao = (WordBookDao)context.getBean("wordBookDao");
		WordBook wordBook=wordBookDao.findById("15");
		//List<JmCydepartmentWorkerInfo> list = jmCydepartmentWorkerInfoService.getListByCydep("144108824432446765292",  0,  20);
		System.out.println(wordBook.getV());
	}*/

}
