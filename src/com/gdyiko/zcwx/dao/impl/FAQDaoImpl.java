package com.gdyiko.zcwx.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.gdyiko.zcwx.dao.FAQDao;
import com.gdyiko.zcwx.po.FAQ;
import com.gdyiko.tool.dao.impl.GenericDaoImpl;

@Repository("FAQDao")
public class FAQDaoImpl extends GenericDaoImpl<FAQ, String> implements FAQDao {

	@Override
	public Class<FAQ> getEntityClass() {
		// TODO Auto-generated method stub
		return FAQ.class;
	}
	
	@Resource(name = "sessionFactory")
	public void setSessionFactoryOverride(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public List<FAQ> findAnswer(String problems) {
		// TODO Auto-generated method stub

		
		String hql = "from FAQ where ('"+problems+"' like '%'+ problem +'%' or problem like '%'+ '"+problems+"' +'%')";
//		System.out.println(problems);
		List<FAQ> faqlist = this.findByHqlParam(hql,new String[]{});
		
		return faqlist;
	}
	
/*	public static void main(String[]xxxx){
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	FAQDao faqdao = (FAQDao)context.getBean("FAQDao");
	List<FAQ> faqlist = faqdao.findAnswer("怎样法人");
	
	//List<JmCydepartmentWorkerInfo> list = jmCydepartmentWorkerInfoService.getListByCydep("144108824432446765292",  0,  20);
	System.out.println(faqlist.get(0).getAnswer());
	}
*/

}
