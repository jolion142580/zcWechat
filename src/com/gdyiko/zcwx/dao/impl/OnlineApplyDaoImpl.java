package com.gdyiko.zcwx.dao.impl;

import com.gdyiko.zcwx.service.SsAffairsObjectService;
import org.hibernate.Hibernate;
import org.hibernate.dialect.SQLServerDialect;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.gdyiko.zcwx.dao.OnlineApplyDao;
import com.gdyiko.zcwx.po.OnlineApply;
import com.gdyiko.tool.dao.impl.GenericDaoImpl;

import java.util.List;
import java.util.Map;


@Repository("onlineApplyDao")
public class OnlineApplyDaoImpl extends GenericDaoImpl<OnlineApply, String> implements OnlineApplyDao {

	@Override
	public Class<OnlineApply> getEntityClass() {
		// TODO Auto-generated method stub
		return OnlineApply.class;
	}

	public List listByOpenId(String openid) {
		StringBuffer sql =new StringBuffer();
		sql.append("select a.id as id,a.state as state,a.approved_or_not as approvedOrNot,c.affairname as affairName,")
				.append(" a.creattime as creattime from onlineApply as a ")
				.append(" LEFT join ss_affairs as c on c.affairid = a.affairid ")
				.append(" WHERE a.openid='").append(openid).append("' ")
				.append(" ORDER BY a.creattime desc ");
		System.out.println("pc查看个人办事记录sql----:"+sql.toString());

		List list = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql.toString())
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

	/*	List<Map<String,String>> list = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql.toString())
				.addScalar("id", Hibernate.STRING)
				.addScalar("state", Hibernate.STRING)
				.addScalar("affairName", Hibernate.STRING)
				.addScalar("approvedOrNot", Hibernate.STRING)
//				.addScalar("creattime", Hibernate.DATE)
				.addScalar("creattime", Hibernate.STRING)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();*/
		return list;
	}
	/*
	public static void main(String[]xxxx){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		SsUserInfoDao ssUserInfoDao = (SsUserInfoDao)context.getBean("ssUserInfoDao");
		SsUserInfo ssUserInfo=ssUserInfoDao.findById("1");
		//List<JmCydepartmentWorkerInfo> list = jmCydepartmentWorkerInfoService.getListByCydep("144108824432446765292",  0,  20);
		System.out.println(ssUserInfo.getName());
		
	}*/

}
