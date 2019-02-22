package com.gdyiko.tool.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gdyiko.tool.dao.MyBaseGenericDao;
import com.gdyiko.tool.po.GenericPo;

/**
 * 
 * @author Abu
 * 
 * @param <T>
 * @param <ID>
 */
// /@Component("genericDao")
public abstract class MyBaseGenericDaoImpl<T extends GenericPo, ID extends Serializable> extends HibernateDaoSupport implements MyBaseGenericDao<T, ID> {
	// 具体的实体类型
	private Class<T> type;

	// Spring提供的Hibernate工具类

	@Resource(name = "sessionFactory")
	public void setSessionFactoryOverride(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	// @Resource(name="hibernateTemplate")
	// /private HibernateTemplate hibernateTemplate;
	// 查询条件
	private String hql;

	/**
	 * <p>
	 * 必须提供的构造方法,以便创建实例的时候就知道具体实体的类型
	 * <p>
	 * 
	 * @param type
	 *            : 实体类型
	 */
	public MyBaseGenericDaoImpl() {
		this.type = getEntityClass();
		this.hql = "from " + type.getName();
	}

	/*
	 * public GenericDaoImpl(Class<T> type) { this.type = type; this.hql =
	 * "from " + type.getName(); }
	 */

	/**
	 * <p>
	 * 因为这个类没有继承HibernateDaoSupport,所以现在由Spring注入HibernateTemplate
	 * </p>
	 * 
	 * @param hibernateTemplate
	 *            : Spring提供的Hibernate工具类
	 */
	public abstract Class<T> getEntityClass();

	public Class<T> getType() {
		return type;
	}

	public void setType(Class<T> type) {
		this.type = type;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public String getHql() {
		return hql;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		String hql = "from " + type.getName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}
	
	public List findByHql(String hql) {
		return (List) this.getHibernateTemplate().find(hql);
	}

	public T findById(ID id) {
		System.out.println(id);
		return (T) this.getHibernateTemplate().get(type, id);
	}

	public void modify(T entity) {
		entity = this.getHibernateTemplate().merge(entity);
		this.getHibernateTemplate().update(entity);
		// /this.getHibernateTemplate().merge(entity);
	}

	public void remove(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	public void removeAll(Collection<T> entities) {
		this.getHibernateTemplate().deleteAll(entities);
	}

	@SuppressWarnings("unchecked")
	public ID save(T entity) {
		entity = this.getHibernateTemplate().merge(entity);
		System.out.println(entity);
		return (ID) this.getHibernateTemplate().save(entity);
		// return (ID) this.getHibernateTemplate().merge(entity);
	}

	public int getTotalRows() {
		String actualHql = "select count(*) " + hql.substring(hql.indexOf("from"));
		return ((Long) this.getHibernateTemplate().find(actualHql).get(0)).intValue();
	}

	public int getPageSize(int size) {
		// 最大页数
		int pageSize;
		// 实际每页数据条数
		int actualSize;
		// 总记录数
		int totalRows = this.getTotalRows();
		// 计算实际每页的条数,如果请求的每页数据条数大于总条数, 则等于总条数
		actualSize = (size > totalRows) ? totalRows : size;
		if (totalRows > 0) {
			pageSize = (totalRows % size == 0) ? (totalRows / actualSize) : (totalRows / actualSize + 1);
		} else {
			pageSize = 0;
		}
		return pageSize;
	}

	@SuppressWarnings("unchecked")
	public List<T> findByPage(final int page, final int size) {
		final int pageSize = this.getPageSize(size);
		final int totalRows = this.getTotalRows();
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
				// 实际页码
				int actualPage = (page > pageSize) ? pageSize : page;
				// 计算实际每页的条数,如果请求的每页数据条数大于总条数, 则等于总条数
				int actualSize = (size > totalRows) ? totalRows : size;
				// 计算请求页码的第一条记录的索引值,如果
				int startRow = (actualPage > 0) ? (actualPage - 1) * actualSize : 0;
				System.out.println(hql);
				Query query = session.createQuery(hql);
				// 设置第一条记录
				query.setFirstResult(startRow);
				query.setMaxResults(actualSize);
				System.out.println("首行数据:" + startRow);
				System.out.println("数据量大小" + actualSize);
				return (List<T>) query.list();
			}
		});
	}

	// -------------------------------- Criteria ------------------------------

	// 创建与会话无关的检索标准
	public DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(this.getEntityClass());
	}

	// 创建与会话绑定的检索标准
	public Criteria createCriteria() {
		return this.createDetachedCriteria().getExecutableCriteria(this.getHibernateTemplate().getSessionFactory().openSession());
	}

	// 检索满足标准的数据
	public List findByCriteria(DetachedCriteria criteria) {
		return getHibernateTemplate().findByCriteria(criteria);
	}

	// 检索满足标准的数据，返回指定范围的记录
	public List findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults) {
		return getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
	}

	// 使用指定的实体及属性检索（满足除主键外属性＝实体值）数据
	public List<T> findEqualByEntity(T entity, String[] propertyNames) {
		Criteria criteria = this.createCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		Example exam = Example.create(entity);
		exam.excludeZeroes();
		String[] defPropertys = getHibernateTemplate().getSessionFactory().getClassMetadata(getEntityClass()).getPropertyNames();
		for (String defProperty : defPropertys) {
			int ii = 0;
			for (ii = 0; ii < propertyNames.length; ++ii) {
				if (defProperty.equals(propertyNames[ii])) {
					criteria.addOrder(Order.asc(defProperty));
					break;
				}
			}
			if (ii == propertyNames.length) {
				exam.excludeProperty(defProperty);
			}
		}
		criteria.add(exam);
		return (List<T>) criteria.list();
	}

	// 使用指定的实体及属性检索（满足属性 like 串实体值）数据
	public List<T> findLikeByEntity(T entity, String[] propertyNames) {
		Criteria criteria = this.createCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		for (String property : propertyNames) {
			try {
				Object value = PropertyUtils.getProperty(entity, property);
				if (value instanceof String) {
					criteria.add(Restrictions.like(property, (String) value, MatchMode.ANYWHERE));
					criteria.addOrder(Order.asc(property));
				}else if (value instanceof Set) {
					GenericPo po =null ;
					Iterator iterator = ((Set) value).iterator();
					String objname = "";
					Object[] vals;
					List<Object> vallist = new ArrayList<Object>();
					while (iterator.hasNext()) {
						 po = (GenericPo) iterator.next();
						if (po != null) {
							objname = po.getClass().getName().substring(po.getClass().getName().lastIndexOf(".") + 1, po.getClass().getName().length());
							vallist.add(po.getId());
						}
					}
					vals =  vallist.toArray();
					
					objname = objname.substring(0,1).toLowerCase()+objname.substring(1);
					System.out.println(objname+"************************************************"+vals.length);
					criteria.createAlias(property,property).add(Restrictions.in(property+".id" ,vals));
				} else {
					criteria.add(Restrictions.eq(property, value));
				    criteria.addOrder(Order.asc(property));
				}
			} catch (Exception ex) {
				// 忽略无效的检索参考数据。
			}
		}

		return (List<T>) criteria.list();
	}

	// 使用指定的实体及属性检索（满足属性 like 串实体值）数据
	public int findLikeByEntitySize(T entity, String[] propertyNames) {
		Criteria criteria = this.createCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		for (String property : propertyNames) {
			try {
				Object value = PropertyUtils.getProperty(entity, property);
				if (value instanceof String) {
					if(((String) value).indexOf("date")!=-1){
						//System.out.println("property::::::::::"+property);
						String beginDate=((String) value).substring(((String) value).indexOf("_")+1,((String) value).lastIndexOf("_"));
						String endDate=((String) value).substring(((String) value).lastIndexOf("_")+1);
						//System.out.println("beginDate--------->>>>>"+beginDate+"------endDate---"+endDate);
						String date1=((String) value).substring(((String) value).indexOf("_")+1);
						criteria.add(Restrictions.between(property,beginDate ,endDate));
					}
					else if(((String) value).indexOf("Stringin")!=-1){
						//in查询
						//模糊查询
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("Stringin%3A", "");
						String[] vals = tempStr.split("!_!");
						criteria.add(Restrictions.in(property ,vals));
					}
					else if(((String) value).indexOf("likeand")!=-1){
						//in查询
						//模糊查询
						Criterion criterion =null;
						String tempStr = (String) value;
						System.out.println("likeand--------->"+tempStr);
						tempStr = tempStr.replaceAll("likeand%3A", "");
						String[] vals = tempStr.split("!_!");
						for(int i=0;i<vals.length;i++){
							System.out.println("vals["+i+"] ------->"+vals[i] );
							if(i==0){
								criterion=Restrictions.like(property, "%" + vals[i] + "%", MatchMode.ANYWHERE);
							}else{
								criterion=Restrictions.and(criterion, Restrictions.like(property, "%" + vals[i] + "%", MatchMode.ANYWHERE));
							}
						}
						criteria.add(criterion);
					}
					else if(((String) value).indexOf("likeor")!=-1){
						//in查询
						//模糊查询
	
						Criterion criterion =null;
						String tempStr = (String) value;
						System.out.println("likeor--------->"+tempStr);
						tempStr = tempStr.replaceAll("likeor%3A", "");
						String[] vals = tempStr.split("!_!");
						for(int i=0;i<vals.length;i++){
							System.out.println("vals["+i+"] ------->"+vals[i] );
							if(i==0){
								criterion=Restrictions.like(property, "%" +vals[i] + "%", MatchMode.ANYWHERE);
							}else{
								criterion=Restrictions.or(criterion, Restrictions.like(property, "%" + vals[i] + "%", MatchMode.ANYWHERE));
							}
						}
						criteria.add(criterion);

					}
					else{
						criteria.add(Restrictions.like(property, (String) value, MatchMode.ANYWHERE));
						////criteria.addOrder(Order.asc(property));
					}
					
				} else if (value instanceof Set) {
					GenericPo po =null ;
					Iterator iterator = ((Set) value).iterator();
					String objname = "";
					Object[] vals;
					List<Object> vallist = new ArrayList<Object>();
					while (iterator.hasNext()) {
						 po = (GenericPo) iterator.next();
						if (po != null) {
							objname = po.getClass().getName().substring(po.getClass().getName().lastIndexOf(".") + 1, po.getClass().getName().length());
							vallist.add(po.getId());
						}
					}
					vals =  vallist.toArray();
					
					objname = objname.substring(0,1).toLowerCase()+objname.substring(1);
					System.out.println(objname+"************************************************"+vals.length);
					criteria.createAlias(property,property).add(Restrictions.in(property+".id" ,vals));
				} else {
					criteria.add(Restrictions.eq(property, value));
					/////criteria.addOrder(Order.asc(property));
				}
			} catch (Exception ex) {
				// 忽略无效的检索参考数据。
			}
		}
		criteria.setProjection(Projections.rowCount());  
		return Integer.parseInt(criteria.uniqueResult().toString());
	}

	// 使用指定的实体及属性检索（满足属性 like 串实体值）数据
	public List<T> findLikeByEntity(T entity, String[] propertyNames, int firstResult, int maxResults) {
		System.out.println("maxResults------>" + maxResults);
		System.out.println("firstResult------>" + firstResult);
		Criteria criteria = this.createCriteria();
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		for (String property : propertyNames) {
			
			try {
				Object value = PropertyUtils.getProperty(entity, property);
				System.out.println("testing--->" + value.getClass().getName());
				if (value instanceof String) {
					if(((String) value).indexOf("date")!=-1){
						//System.out.println("property::::::::::"+property);
						String beginDate=((String) value).substring(((String) value).indexOf("_")+1,((String) value).lastIndexOf("_"));
						String endDate=((String) value).substring(((String) value).lastIndexOf("_")+1);
						//System.out.println("beginDate--------->>>>>"+beginDate+"------endDate---"+endDate);
						String date1=((String) value).substring(((String) value).indexOf("_")+1);
						criteria.add(Restrictions.between(property,beginDate ,endDate));
					}else if(((String) value).indexOf("Stringin")!=-1){
						//in查询
						//模糊查询
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("Stringin%3A", "");
						String[] vals = tempStr.split("!_!");
						criteria.add(Restrictions.in(property ,vals));
					}
					else if(((String) value).indexOf("likeand")!=-1){
						//in查询
						//模糊查询
						Criterion criterion =null;
						String tempStr = (String) value;
						System.out.println("likeand--------->"+tempStr);
						tempStr = tempStr.replaceAll("likeand%3A", "");
						String[] vals = tempStr.split("!_!");
						for(int i=0;i<vals.length;i++){
							System.out.println("vals["+i+"] ------->"+vals[i] );
							if(i==0){
								criterion=Restrictions.like(property, "%" + vals[i] + "%", MatchMode.ANYWHERE);
							}else{
								criterion=Restrictions.and(criterion, Restrictions.like(property, "%" + vals[i] + "%", MatchMode.ANYWHERE));
								
							}
						}
						criteria.add(criterion);
					}
					else if(((String) value).indexOf("likeor")!=-1){
						//in查询
						//模糊查询
	
						Criterion criterion =null;
						String tempStr = (String) value;
						System.out.println("likeor--------->"+tempStr);
						tempStr = tempStr.replaceAll("likeor%3A", "");
						String[] vals = tempStr.split("!_!");
						for(int i=0;i<vals.length;i++){
							System.out.println("vals["+i+"] ------->"+vals[i] );
							if(i==0){
								criterion=Restrictions.like(property, "%" +vals[i] + "%", MatchMode.ANYWHERE);
							}else{
								criterion=Restrictions.or(criterion, Restrictions.like(property, "%" + vals[i] + "%", MatchMode.ANYWHERE));
							}
						}
						criteria.add(criterion);

					}
					
					else{
						System.out.println("check---->" + "%" + (String) value + "%");

						criteria.add(Restrictions.like(property, "%" + (String) value + "%", MatchMode.ANYWHERE));
					}
					
					///criteria.addOrder(Order.asc(property));
				}else if (value instanceof Set) {
					GenericPo po =null ;
					Iterator iterator = ((Set) value).iterator();
					String objname = "";
					Object[] vals;
					List<Object> vallist = new ArrayList<Object>();
					while (iterator.hasNext()) {
						 po = (GenericPo) iterator.next();
						if (po != null) {
							objname = po.getClass().getName().substring(po.getClass().getName().lastIndexOf(".") + 1, po.getClass().getName().length());
							vallist.add(po.getId());
						}
					}
					vals =  vallist.toArray();
					
					objname = objname.substring(0,1).toLowerCase()+objname.substring(1);
					System.out.println(objname+"************************************************"+vals.length);
					criteria.createAlias(property,property).add(Restrictions.in(property+".id" ,vals));
				} else {
					System.out.println("objname3------->"+property);
					criteria.add(Restrictions.eq(property, value));
					///criteria.addOrder(Order.asc(property));
				}
			} catch (Exception ex) {
				// 忽略无效的检索参考数据。
			}
		}

		//加快读取速度先查询出例子ID然后IN出来
		ProjectionList   proList   =   Projections.projectionList();//设置投影集合   
		proList.add(Projections.property("id").as("id"));   
		criteria.setProjection(proList);
		List  list  = criteria.list();
		String idlist = "";
		for (Object e : list) {
			///System.out.println(e);
			idlist = idlist +(String)e+";";
		}
		System.out.println(idlist);
		criteria = this.createCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.in("id" ,idlist.split(";")));
		return (List<T>) criteria.list();
	}

	// 使用指定的检索标准获取满足标准的记录数
	public Integer getRowCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List list = this.findByCriteria(criteria, 0, 1);
		return (Integer) list.get(0);
	}

	// 使用指定的检索标准检索数据，返回指定统计值(max,min,avg,sum)
	public Object getStatValue(DetachedCriteria criteria, String propertyName, String StatName) {
		if (StatName.toLowerCase().equals("max"))
			criteria.setProjection(Projections.max(propertyName));
		else if (StatName.toLowerCase().equals("min"))
			criteria.setProjection(Projections.min(propertyName));
		else if (StatName.toLowerCase().equals("avg"))
			criteria.setProjection(Projections.avg(propertyName));
		else if (StatName.toLowerCase().equals("sum"))
			criteria.setProjection(Projections.sum(propertyName));
		else
			return null;
		List list = this.findByCriteria(criteria, 0, 1);
		return list.get(0);
	}
	
	public List<T> findByHqlParam(String hql,Object[] values){
		return (List<T>)this.getHibernateTemplate().find(hql,values);
	}
}
