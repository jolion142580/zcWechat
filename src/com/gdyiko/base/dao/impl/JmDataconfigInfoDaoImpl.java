package com.gdyiko.base.dao.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.gdyiko.base.dao.JmDataconfigInfoDao;
import com.gdyiko.base.po.JmDataconfigInfo;
import com.gdyiko.base.po.MemGeninf;
import com.gdyiko.tool.dao.impl.MyBaseGenericDaoImpl;

@Repository("jmDataconfigInfoDao")
public class JmDataconfigInfoDaoImpl extends MyBaseGenericDaoImpl<JmDataconfigInfo, String> implements JmDataconfigInfoDao {

	@Override
	public Class<JmDataconfigInfo> getEntityClass() {
		// TODO Auto-generated method stub
		return JmDataconfigInfo.class;
	}


	// 查询父为空值的数据
    public List<JmDataconfigInfo> findNotNullByPage(JmDataconfigInfo entity, String nullColumnName, int firstResult,
            int maxResults) {
        Criteria criteria = this.createCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); 
        criteria.setFirstResult(firstResult);  
        criteria.setMaxResults(maxResults); 
        criteria.add(Restrictions.isNull(nullColumnName));

        return (List<JmDataconfigInfo>) criteria.list();
    }
	
    /**
     * 
    
    * @Title: findNotNullBySize 
    
    * @Description: TODO(查询顶层数量) 
    
    * @param @param entity
    * @param @param nullColumnName
    * @param @return    设定文件 
    
    * @return int    返回类型 
    
    * @throws
     */
    public int findNotNullBySize(JmDataconfigInfo entity, String nullColumnName) {
        Criteria criteria = this.createCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); 
        criteria.add(Restrictions.isNull(nullColumnName));
        return criteria.list().size();
    }
    
    
 // 查询父ID内的
    public List<JmDataconfigInfo> findChildByPage(JmDataconfigInfo entity, String parentid, int firstResult,
            int maxResults) {
    	System.out.println("findChildByPage----------->"+parentid+"-->"+firstResult+"-->"+maxResults);
        Criteria criteria = this.createCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); 
        criteria.setFirstResult(firstResult);  
        criteria.setMaxResults(maxResults); 
        //criteria.add(Restrictions.eq("parentid", parentid));
        criteria.add(Restrictions.eq("parent", this.findById(parentid)));
        System.out.println("info:------------?"+criteria.list().size());
        return (List<JmDataconfigInfo>) criteria.list();
    }
    
    
    // 查询子数量
    public int findChildBySize(JmDataconfigInfo entity, String parentid) {
    	System.out.println("findChildByPage----------->"+parentid);
        Criteria criteria = this.createCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); 
        criteria.add(Restrictions.eq("parent", this.findById(parentid)));
        System.out.println("info:------------?"+criteria.list().size());
        return criteria.list().size();
    }
    
	
	public static void main(String[]xxxx){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		JmDataconfigInfoDao jmDataconfigInfoDao=(JmDataconfigInfoDao)context.getBean("jmDataconfigInfoDao");
		///List<MemGeninf> list = memGeninfDAO.findAll();
		//System.out.println(memGeninfDAO.findAll().size());
		JmDataconfigInfo jdc = jmDataconfigInfoDao.findById("1");
		Set<JmDataconfigInfo> jdcSet= jdc.getChildren();
		System.out.println();
	}
	
	
	
}
