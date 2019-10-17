package com.gdyiko.zcwx.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.FAQDao;
import com.gdyiko.zcwx.po.FAQ;
import com.gdyiko.zcwx.service.FAQService;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("FAQService")
public class FAQServiceImpl extends GenericServiceImpl<FAQ, String> implements FAQService {
	@Resource(name = "FAQDao")
	FAQDao faqDao;
	
	@Resource(name = "FAQDao")
	@Override
	public void setGenericDao(GenericDao<FAQ, String> genericDao) {
		super.setGenericDao(genericDao);
	}


	public String findAnswer(String problems) {
		FAQ faq = new FAQ();
		faq.setProblem(problems);
		JSONArray json=null;
		List<FAQ> list = new ArrayList<FAQ>();

		String result = "";
		try{
			list = faqDao.findAnswer(problems);
			json=JSONArray.fromObject(list);
			result = json.toString();
		}catch(Exception e){
			e.printStackTrace();
			list=faqDao.findAnswer("");
			result ="请正确填写需要咨询的问题，如下：/n";
			for(FAQ l : list){
				//lists.add(l.getProblem());
				result+=l.getProblem()+";/n";
			}
		}
		
		return result;
	}

	
/*	@Override
	public String findProblem(String problems) {
		// TODO Auto-generated method stub
		return null;
	}*/
	

/*	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		FAQService faqservice=(FAQService)context.getBean("FAQService");
		System.out.println(System.currentTimeMillis());
		String rs = faqservice.findAnswer("怎样预约");
		System.out.println(System.currentTimeMillis());
		System.out.println(rs);
	}
*/


	
}
