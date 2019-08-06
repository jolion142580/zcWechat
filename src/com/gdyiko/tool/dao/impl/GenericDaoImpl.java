package com.gdyiko.tool.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.LoadQueryInfluencers;
import org.hibernate.engine.QueryParameters;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.loader.criteria.CriteriaJoinWalker;
import org.hibernate.loader.criteria.CriteriaQueryTranslator;
import org.hibernate.persister.entity.OuterJoinLoadable;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.po.GenericPo;

/**
 * 
 * @author Abu
 * 
 * @param <T>
 * @param <ID>
 */
// /@Component("genericDao")
public abstract class GenericDaoImpl<T extends GenericPo, ID extends Serializable>
		extends HibernateDaoSupport implements GenericDao<T, ID> {
	// 具体的实体类型
	private Class<T> type;

	private boolean exvalue = false;

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
	 *            : 实体类型
	 */
	public GenericDaoImpl() {
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
		return (T) this.getHibernateTemplate().get(type, id);
	}

	public void modify(T entity) {
		try {
			entity = this.getHibernateTemplate().merge(entity);
			this.getHibernateTemplate().update(entity);

		} catch (Exception e) {
			System.out.println(e);
		}
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
		return (ID) this.getHibernateTemplate().save(entity);
		
		// return (ID) this.getHibernateTemplate().merge(entity);
	}

	public int getTotalRows() {
		String actualHql = "select count(*) "
				+ hql.substring(hql.indexOf("from"));
		return ((Long) this.getHibernateTemplate().find(actualHql).get(0))
				.intValue();
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
			pageSize = (totalRows % size == 0) ? (totalRows / actualSize)
					: (totalRows / actualSize + 1);
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
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				// 实际页码
				int actualPage = (page > pageSize) ? pageSize : page;
				// 计算实际每页的条数,如果请求的每页数据条数大于总条数, 则等于总条数
				int actualSize = (size > totalRows) ? totalRows : size;
				// 计算请求页码的第一条记录的索引值,如果
				int startRow = (actualPage > 0) ? (actualPage - 1) * actualSize
						: 0;
				Query query = session.createQuery(hql);
				// 设置第一条记录
				query.setFirstResult(startRow);
				query.setMaxResults(actualSize);
				return (List<T>) query.list();
			}
		});
	}

	// 创建与会话无关的检索标准
	public DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(this.getEntityClass());
	}

	// 创建与会话绑定的检索标准
	public Criteria createCriteria() {
		return this.createDetachedCriteria().getExecutableCriteria(
				this.getHibernateTemplate().getSessionFactory().openSession());
	}

	// 检索满足标准的数据
	public List findByCriteria(DetachedCriteria criteria) {
		return getHibernateTemplate().findByCriteria(criteria);
	}

	// 检索满足标准的数据，返回指定范围的记录
	public List findByCriteria(DetachedCriteria criteria, int firstResult,
			int maxResults) {
		return getHibernateTemplate().findByCriteria(criteria, firstResult,
				maxResults);
	}

	// 使用指定的实体及属性检索（满足除主键外属性＝实体值）数据
	public List<T> findEqualByEntity(T entity, String[] propertyNames) {
		Criteria criteria = this.createCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		Example exam = Example.create(entity);
		exam.excludeZeroes();
		String[] defPropertys = getHibernateTemplate().getSessionFactory()
				.getClassMetadata(getEntityClass()).getPropertyNames();
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
		String ordersql = "";
		for (String property : propertyNames) {
			try {
				Object value = PropertyUtils.getProperty(entity, property);
				// 排序
				if (value instanceof String) {
					/*
					 * 排序格式 例子“值orderby1_desc_” orderby 分离符号 desc 升降序 1
					 */
					if (((String) value).indexOf("orderby") != -1) {
						String[] tempValue = ((String) value).split("orderby");
						value = tempValue[0];

						ordersql = ordersql + tempValue[1] + property + ";";
						/*
						 * if(((String) value).indexOf("desc")!=-1){
						 * criteria.addOrder(Order.desc(property)); value =
						 * ((String) value).replaceAll("orderbydesc",""); }else{
						 * criteria.addOrder(Order.asc(property)); value =
						 * ((String) value).replaceAll("orderby",""); }
						 */

					}
					if (((String) value).indexOf("date") != -1) {
						String beginDate = ((String) value).substring(
								((String) value).indexOf("_") + 1,
								((String) value).lastIndexOf("_"));
						String endDate = ((String) value)
								.substring(((String) value).lastIndexOf("_") + 1);
						String date1 = ((String) value)
								.substring(((String) value).indexOf("_") + 1);
						criteria.add(Restrictions.between(property, beginDate,
								endDate));
					}
					//2016.12.29解决>大于，<小于问题(find...size方法未同步修改)
					//例子："lt<"+value,"lt<="+value
					else if (((String) value).indexOf("lt<") != -1) {
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("lt<", "");
						if(tempStr.indexOf("=")!=-1){
							tempStr = tempStr.replaceAll("=", "");
							criteria.add(Restrictions.le(property, tempStr));
						}else{
							criteria.add(Restrictions.lt(property, tempStr));
						}
					}else if (((String) value).indexOf("gt>") != -1) {
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("gt>", "");
						if(tempStr.indexOf("=")!=-1){
							tempStr = tempStr.replaceAll("=", "");
							criteria.add(Restrictions.ge(property, tempStr));
						}else{
							criteria.add(Restrictions.gt(property, tempStr));
						}
					}
					else if (((String) value).indexOf("Stringin") != -1) {
						// in查询
						// 模糊查询
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("Stringin%3A", "");
						String[] vals = tempStr.split("!_!");
						criteria.add(Restrictions.in(property, vals));
					} else if (((String) value).indexOf("likeand") != -1) {
						// in查询
						// 模糊查询
						Criterion criterion = null;
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("likeand%3A", "");
						String[] vals = tempStr.split("!_!");
						for (int i = 0; i < vals.length; i++) {
							if (i == 0) {
								criterion = Restrictions.like(property, "%"
										+ vals[i] + "%", MatchMode.ANYWHERE);
							} else {
								criterion = Restrictions.and(criterion,
										Restrictions.like(property, "%"
												+ vals[i] + "%",
												MatchMode.ANYWHERE));

							}
						}
						criteria.add(criterion);
					} else if (((String) value).indexOf("likeor") != -1) {
						// in查询
						// 模糊查询
						Criterion criterion = null;
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("likeor%3A", "");
						String[] vals = tempStr.split("!_!");
						for (int i = 0; i < vals.length; i++) {
							if (i == 0) {
								criterion = Restrictions.like(property, "%"
										+ vals[i] + "%", MatchMode.ANYWHERE);
							} else {
								criterion = Restrictions.or(criterion,
										Restrictions.like(property, "%"
												+ vals[i] + "%",
												MatchMode.ANYWHERE));
							}
						}
						criteria.add(criterion);
					} else if (((String) value).indexOf("equals") != -1) {
						// in查询
						// 模糊查询
						Criterion criterion = null;
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("equals%3A", "");

						criterion = Restrictions.eq(property, tempStr);

						criteria.add(criterion);
					} else {
						if (((String) value).trim().equals("")) {
							continue;
						}
						criteria.add(Restrictions.like(property, "%"
								+ (String) value + "%", MatchMode.ANYWHERE));
					}

				} else if (value instanceof Set) {
					GenericPo po = null;
					Iterator iterator = ((Set) value).iterator();
					String objname = "";
					Object[] vals;
					List<Object> vallist = new ArrayList<Object>();
					while (iterator.hasNext()) {
						po = (GenericPo) iterator.next();
						if (po != null) {
							objname = po
									.getClass()
									.getName()
									.substring(
											po.getClass().getName()
													.lastIndexOf(".") + 1,
											po.getClass().getName().length());
							vallist.add(po.getId());
						}
					}
					vals = vallist.toArray();

					objname = objname.substring(0, 1).toLowerCase()
							+ objname.substring(1);
					criteria.createAlias(property, property).add(
							Restrictions.in(property + ".id", vals));
				} else {
					criteria.add(Restrictions.eq(property, value));
					// /criteria.addOrder(Order.asc(property));
				}
			} catch (Exception ex) {
				// 忽略无效的检索参考数据。
			}
		}
		if ("".equals(ordersql) == false) {
			String[] orderstrs = ordersql.split(";");
			Arrays.sort(orderstrs);
			for (int i = 0; i < orderstrs.length; i++) {
				String temporderstr = orderstrs[i];
				String[] temporderstrs = temporderstr.split("_");
				// 属性名
				String pname = temporderstrs[2];
				// 排序方式
				String order = temporderstrs[1];
				if ("desc".equals(order)) {
					criteria.addOrder(Order.desc(pname));
				}
				if ("asc".equals(order)) {
					criteria.addOrder(Order.asc(pname));
				}
			}
		}
		return (List<T>) criteria.list();
	}

	// 使用指定的实体及属性检索（满足属性 like 串实体值）数据
	public int findLikeByEntitySize(T entity, String[] propertyNames) {
		Criteria criteria = this.createCriteria();
		boolean flag = false;// 作为equalsOR的标记
		String ordersql = "";
		Disjunction dis = Restrictions.disjunction();//
		// criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		for (String property : propertyNames) {
			try {
				Object value = PropertyUtils.getProperty(entity, property);
				/*
				 * if("exvalue".equals(property)){ //开启特殊查询模式
				 * this.setExvalue(true); }
				 */
				if (value instanceof String) {
					if (((String) value).indexOf("orderby") != -1) {

						String[] tempValue = ((String) value).split("orderby");
						value = tempValue[0];
						/*
						 * if (((String) value).indexOf("desc") != -1) { value =
						 * ((String) value).replaceAll("orderbydesc", ""); }
						 * else { value = ((String) value).replaceAll("orderby",
						 * ""); }
						 */
						if (((String) value).trim().equals("")) {
							continue;
						}
					}
					// 5.5
					if (((String) value).indexOf("StringNe") != -1) {// 设置不等于条件
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("StringNe", "");
						criteria.add(Restrictions.ne(property, tempStr));
					}
					//
					// 5.18针对 and(property1 = value1 or property2 = value2)
					else if (((String) value).indexOf("equalsOR") != -1) {
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("equalsOR", "");
						flag = true;
						dis.add(Restrictions.eq(property, tempStr));// 考虑到读取速度，使用了全等
					}
					//
					else if (((String) value).indexOf("date") != -1) {
						String beginDate = ((String) value).substring(
								((String) value).indexOf("_") + 1,
								((String) value).lastIndexOf("_"));
						String endDate = ((String) value)
								.substring(((String) value).lastIndexOf("_") + 1);
						String date1 = ((String) value)
								.substring(((String) value).indexOf("_") + 1);
						criteria.add(Restrictions.between(property, beginDate,
								endDate));
					} else if (((String) value).indexOf("Stringin") != -1) {
						// in查询
						// 模糊查询
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("Stringin%3A", "");
						String[] vals = tempStr.split("!_!");
						criteria.add(Restrictions.in(property, vals));
					} else if (((String) value).indexOf("likeand") != -1) {
						// in查询
						// 模糊查询
						Criterion criterion = null;
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("likeand%3A", "");
						String[] vals = tempStr.split("!_!");
						for (int i = 0; i < vals.length; i++) {
							if (i == 0) {
								criterion = Restrictions.like(property, "%"
										+ vals[i] + "%", MatchMode.ANYWHERE);
							} else {
								criterion = Restrictions.and(criterion,
										Restrictions.like(property, "%"
												+ vals[i] + "%",
												MatchMode.ANYWHERE));
							}
						}
						criteria.add(criterion);
					} else if (((String) value).indexOf("likeor") != -1) {
						// in查询
						// 模糊查询

						Criterion criterion = null;
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("likeor%3A", "");
						String[] vals = tempStr.split("!_!");
						for (int i = 0; i < vals.length; i++) {
							if (i == 0) {
								criterion = Restrictions.like(property, "%"
										+ vals[i] + "%", MatchMode.ANYWHERE);
							} else {
								criterion = Restrictions.or(criterion,
										Restrictions.like(property, "%"
												+ vals[i] + "%",
												MatchMode.ANYWHERE));
							}
						}
						criteria.add(criterion);

					} else if (((String) value).indexOf("equals") != -1) {
						// in查询
						// 模糊查询
						Criterion criterion = null;
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("equals%3A", "");

						criterion = Restrictions.eq(property, tempStr);

						criteria.add(criterion);
					} else {

						criteria.add(Restrictions.like(property,
								(String) value, MatchMode.ANYWHERE));
						// //criteria.addOrder(Order.asc(property));
					}

				} else if (value instanceof Set) {
					GenericPo po = null;
					Iterator iterator = ((Set) value).iterator();
					String objname = "";
					Object[] vals;
					List<Object> vallist = new ArrayList<Object>();
					while (iterator.hasNext()) {
						po = (GenericPo) iterator.next();
						if (po != null) {
							objname = po
									.getClass()
									.getName()
									.substring(
											po.getClass().getName()
													.lastIndexOf(".") + 1,
											po.getClass().getName().length());
							vallist.add(po.getId());
						}
					}
					vals = vallist.toArray();

					objname = objname.substring(0, 1).toLowerCase()
							+ objname.substring(1);
					criteria.createAlias(property, property).add(
							Restrictions.in(property + ".id", vals));
				} else {
					criteria.add(Restrictions.eq(property, value));

				}
			} catch (Exception ex) {
				// 忽略无效的检索参考数据。
			}
		}
		// 5-18针对 and(property1 = value1 or property2 = value2)
		if (flag) {
			criteria.add(dis);
		}
		criteria.setProjection(Projections.rowCount());
		// /criteria.setProjection(Projections.rowCount());

		List findBySql = getCriteriaSqlCount(criteria);
		Map hm = (Map) (findBySql.get(0));
		Set set = hm.keySet();
		Object[] name = set.toArray();
		System.out.println(name[0]);

		// System.out.println(hm.get(name[0]));
		// String i = (String) hm.get(name[0]);
		return (Integer) hm.get(name[0]);
		// return Integer.parseInt(criteria.uniqueResult().toString());
	}

	// 使用指定的实体及属性检索（满足属性 like 串实体值）数据
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gdyiko.tool.dao.GenericDao#findLikeByEntity(java.lang.Object,
	 * java.lang.String[], int, int)
	 */
	public List<T> findLikeByEntity(T entity, String[] propertyNames,
			int firstResult, int maxResults) {
		Criteria criteria = this.createCriteria();
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		boolean flag = false;// 作为equalsOR的标记
		Disjunction dis = Restrictions.disjunction();//
		String temporder = "";
		String ordersql = "";
		for (String property : propertyNames) {
			try {
				Object value = PropertyUtils.getProperty(entity, property);
				// 排序
				if (value instanceof String) {
					/*
					 * 排序格式 例子“值orderby1_desc_” orderby 分离符号 desc 升降序 1
					 */
					if (((String) value).indexOf("orderby") != -1) {
						String[] tempValue = ((String) value).split("orderby");
						value = tempValue[0];

						ordersql = ordersql + tempValue[1] + property + ";";
						/*
						 * if(((String) value).indexOf("desc")!=-1){
						 * criteria.addOrder(Order.desc(property)); value =
						 * ((String) value).replaceAll("orderbydesc",""); }else{
						 * criteria.addOrder(Order.asc(property)); value =
						 * ((String) value).replaceAll("orderby",""); }
						 */
					}
					// 5.5
					if (((String) value).indexOf("StringNe") != -1) {// 设置不等于条件
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("StringNe", "");
						criteria.add(Restrictions.ne(property, tempStr));
					}
					//
					// 5.18针对 and(property1 = value1 or property2 = value2)
					else if (((String) value).indexOf("equalsOR") != -1) {
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("equalsOR", "");
						flag = true;
						dis.add(Restrictions.eq(property, tempStr));// 考虑到读取速度，使用了全等
					}
					//
					else if (((String) value).indexOf("date") != -1) {
						// 按日期查询的格式为 date_2015-11-10_2015-11-20
						String beginDate = ((String) value).substring(
								((String) value).indexOf("_") + 1,
								((String) value).lastIndexOf("_"));
						String endDate = ((String) value)
								.substring(((String) value).lastIndexOf("_") + 1);
						String date1 = ((String) value)
								.substring(((String) value).indexOf("_") + 1);
						criteria.add(Restrictions.between(property, beginDate,
								endDate));
						// criteria.add Add a restriction to constrain the
						// results to be retrieved
						// Restrictions.between Apply a "between" constraint to
						// the named property
					} else if (((String) value).indexOf("Stringin") != -1) {
						// in查询
						// 模糊查询
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("Stringin%3A", "");
						String[] vals = tempStr.split("!_!");
						criteria.add(Restrictions.in(property, vals));
						// Restrictions.in Apply an "in" constraint to the named
						// property
					} else if (((String) value).indexOf("likeand") != -1) {
						// in查询
						// 模糊查询
						Criterion criterion = null;
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("likeand%3A", "");
						String[] vals = tempStr.split("!_!");
						for (int i = 0; i < vals.length; i++) {
							if (i == 0) {
								criterion = Restrictions.like(property, "%"
										+ vals[i] + "%", MatchMode.ANYWHERE);
								// Restrictions.like Apply a "like" constraint
								// to the named property
							} else {
								criterion = Restrictions.and(criterion,
										Restrictions.like(property, "%"
												+ vals[i] + "%",
												MatchMode.ANYWHERE));
							}
						}
						criteria.add(criterion);
					} else if (((String) value).indexOf("likeor") != -1) {
						// in查询
						// 模糊查询
						Criterion criterion = null;
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("likeor%3A", "");
						String[] vals = tempStr.split("!_!");
						for (int i = 0; i < vals.length; i++) {
							if (i == 0) {
								criterion = Restrictions.like(property, "%"
										+ vals[i] + "%", MatchMode.ANYWHERE);
							} else {
								criterion = Restrictions.or(criterion,
										Restrictions.like(property, "%"
												+ vals[i] + "%",
												MatchMode.ANYWHERE));
							}
						}
						criteria.add(criterion);
					} else if (((String) value).indexOf("equals") != -1) {
						// in查询
						// 模糊查询

						Criterion criterion = null;
						String tempStr = (String) value;
						tempStr = tempStr.replaceAll("equals%3A", "");
						criterion = Restrictions.eq(property, tempStr);

						criteria.add(criterion);
					} else {
						if (((String) value).trim().equals("")) {
							continue;
						}
						criteria.add(Restrictions.like(property, "%"
								+ (String) value + "%", MatchMode.ANYWHERE));
					}

				} else if (value instanceof Set) {
					GenericPo po = null;
					Iterator iterator = ((Set) value).iterator();
					String objname = "";
					Object[] vals;
					List<Object> vallist = new ArrayList<Object>();
					while (iterator.hasNext()) {
						po = (GenericPo) iterator.next();
						if (po != null) {
							objname = po
									.getClass()
									.getName()
									.substring(
											po.getClass().getName()
													.lastIndexOf(".") + 1,
											po.getClass().getName().length());
							vallist.add(po.getId());
						}
					}
					vals = vallist.toArray();

					objname = objname.substring(0, 1).toLowerCase()
							+ objname.substring(1);
					criteria.createAlias(property, property).add(
							Restrictions.in(property + ".id", vals));
				} else {
					criteria.add(Restrictions.eq(property, value));
					// /criteria.addOrder(Order.asc(property));
				}
			} catch (Exception ex) {
				// 忽略无效的检索参考数据。
			}
		}
		// 排序 readinho 优化
		if ("".equals(ordersql) == false) {
			String[] orderstrs = ordersql.split(";");
			Arrays.sort(orderstrs);
			for (int i = 0; i < orderstrs.length; i++) {
				String temporderstr = orderstrs[i];
				String[] temporderstrs = temporderstr.split("_");
				// 属性名
				String pname = temporderstrs[2];
				// 排序方式
				String order = temporderstrs[1];
				if ("desc".equals(order)) {
					criteria.addOrder(Order.desc(pname));
				}
				if ("asc".equals(order)) {
					criteria.addOrder(Order.asc(pname));
				}
			}
		}
		// 5-18针对 and(property1 = value1 or property2 = value2)
		if (flag) {
			criteria.add(dis);
		}
		//
		// 加快读取速度先查询出例子ID然后IN出来
		ProjectionList proList = Projections.projectionList();// 设置投影集合
		proList.add(Projections.property("id").as("id"));
		criteria.setProjection(proList);
		System.out.println("数据库取id前--------->" + System.currentTimeMillis());
		// List list = criteria.list();//仅仅是“我的桌面-待办公文”调用此方法是响应速度特别慢
		List list = getCriteriaSqlListID(criteria, firstResult, maxResults);
		System.out.println("数据库取id后--------->" + System.currentTimeMillis());
		String idlist = "";
		Vector vt = new Vector();
		for (Object e : list) {
			// idlist = idlist +e.toString()+";";
			// vt.add(e);
			idlist = idlist
					+ e.toString().replaceAll("\\[", "").replaceAll("\\]", "")
					+ ";";

			// vt.add(e.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
			vt.add(e.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
		}
		criteria = this.createCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		// 因为jmoa数据库的主键类型为String，所以在此处做出修改！
		if (vt.toArray().length < 1) {
			List<T> templist = new ArrayList<T>();
			return templist;
		}
		criteria.add(Restrictions.in("id", vt.toArray()));
		/*
		 * String[] orderStr = temporder.split(";");//orderStr输出空 for (int i =
		 * 0; i < orderStr.length; i++) { if ("".equals(orderStr[i])) continue;
		 * String[] propertyname = orderStr[i].split("="); if
		 * (orderStr[i].indexOf("desc") != -1) {
		 * criteria.addOrder(Order.desc(propertyname[0]));
		 * 
		 * } else { criteria.addOrder(Order.asc(propertyname[0])); } }
		 */
		// 排序 readinho 优化
		if ("".equals(ordersql) == false) {
			String[] orderstrs = ordersql.split(";");
			Arrays.sort(orderstrs);
			for (int i = 0; i < orderstrs.length; i++) {
				String temporderstr = orderstrs[i];
				String[] temporderstrs = temporderstr.split("_");
				// 属性名
				String pname = temporderstrs[2];
				// 排序方式
				String order = temporderstrs[1];
				if ("desc".equals(order)) {
					criteria.addOrder(Order.desc(pname));
				}
				if ("asc".equals(order)) {
					criteria.addOrder(Order.asc(pname));
				}
			}
		}
		return (List<T>) criteria.list();

	}

	// 使用指定的检索标准获取满足标准的记录数
	public Integer getRowCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List list = this.findByCriteria(criteria, 0, 1);
		return (Integer) list.get(0);
	}

	// 使用指定的检索标准检索数据，返回指定统计值(max,min,avg,sum)
	public Object getStatValue(DetachedCriteria criteria, String propertyName,
			String StatName) {
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

	// 根据hql语句,与传入的参数进行查询，返回查询结果集
	public List<T> findByHqlParam(String hql, Object[] values) {
		return (List<T>) this.getHibernateTemplate().find(hql, values);
	}

	/*
	 * public String getCriteriaSql(Criteria criteria) { CriteriaImpl
	 * criteriaImpl = (CriteriaImpl) criteria;//转型 SessionImplementor session =
	 * criteriaImpl.getSession();//获取SESSION SessionFactoryImplementor factory =
	 * session.getFactory();//获取FACTORY CriteriaQueryTranslator translator = new
	 * CriteriaQueryTranslator(factory, criteriaImpl, criteriaImpl
	 * .getEntityOrClassName(), CriteriaQueryTranslator.ROOT_SQL_ALIAS);
	 * String[] implementors =
	 * factory.getImplementors(criteriaImpl.getEntityOrClassName());
	 * CriteriaJoinWalker walker = new CriteriaJoinWalker((OuterJoinLoadable)
	 * factory .getEntityPersister(implementors[0]), translator, factory,
	 * criteriaImpl, criteriaImpl .getEntityOrClassName(),
	 * session.getEnabledFilters()); translator.getQueryParameters();
	 * System.out.println(); return walker.getSQLString(); }
	 * 
	 * public List getListBySql(String sql,Object[]
	 * values,org.hibernate.type.Type [] types) { // TODO Auto-generated method
	 * stub List list = this.getHibernateTemplate().getSessionFactory()
	 * .openSession().createSQLQuery(sql).setParameters(values, types)
	 * .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list(); return
	 * list; }
	 */

	public List getCriteriaSqlCount(Criteria criteria) {
		System.out.println(System.currentTimeMillis());
		CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;// 转型
		SessionImplementor session = criteriaImpl.getSession();// 获取SESSION
		SessionFactoryImplementor factory = session.getFactory();// 获取FACTORY
		CriteriaQueryTranslator translator = new CriteriaQueryTranslator(
				factory, criteriaImpl, criteriaImpl.getEntityOrClassName(),
				CriteriaQueryTranslator.ROOT_SQL_ALIAS);
		String[] implementors = factory.getImplementors(criteriaImpl
				.getEntityOrClassName());
		CriteriaJoinWalker walker = new CriteriaJoinWalker(
				(OuterJoinLoadable) factory.getEntityPersister(implementors[0]),
				translator, factory, criteriaImpl, criteriaImpl
						.getEntityOrClassName(), (LoadQueryInfluencers) session.getEnabledFilters());
		QueryParameters queryParameters = translator.getQueryParameters();
		String sql = walker.getSQLString();
		System.out
				.println("isExvalue--------------------------------------------------------"
						+ this.isExvalue());

		System.out.println("checkmy-------------------------------->" + sql);
		List list = findBySql(walker.getSQLString(),
				queryParameters.getPositionalParameterValues(),
				queryParameters.getPositionalParameterTypes());
		return list;
	}

	public List findBySql(String sql, Object[] objs, Type[] types) {
		// TODO Auto-generated method stub
		for (int i = 0; i < types.length; i++) {
			System.out.println(types[i].getClass());
			if (types[i] instanceof org.hibernate.type.StringType) {
				sql = sql.replaceFirst("\\?", "'" + objs[i].toString() + "'");

			}
			if (types[i] instanceof org.hibernate.type.IntegerType) {

				sql = sql.replaceFirst("\\?", objs[i].toString());
			}
		}
		System.out.println("info-------11111-->" + sql);
		if (this.isExvalue()) {
			String mysql = sql;
			// 截断排序
			if (mysql.indexOf("order by") != -1) {
				mysql = mysql.substring(0, mysql.lastIndexOf("order by"));
				System.out.println("mysql3------->" + mysql);
			}

			mysql = mysql.replaceAll(" this_.listid as y0_",
					" max(this_.listid) as y0_");
			if (mysql.indexOf("count") != -1) {// 替换count
				// /.replaceAll(" count(\\*) as y0_ ",
				// " max(this_.listid) as y0_")
				mysql = mysql.substring(0, mysql.indexOf("count"))
						+ " max(this_.listid) as y0_ "
						+ mysql.substring(mysql.indexOf("count") + 15,
								mysql.length());
			}
			System.out.println("mysql4------->" + mysql);
			// 解决慢的问题
			mysql = mysql.replace("v_art_list this_", "v_artclelisttemp this_");

			mysql = mysql + " group by this_.articleid";
			System.out.println("mysql5------->" + mysql);

			if (sql.indexOf("order by") != -1) {
				sql = sql.substring(0, sql.indexOf("where")) + " where 1=1 "
						+ sql.substring(sql.indexOf("order by"), sql.length());
			} else {
				sql = sql.substring(0, sql.indexOf("where")) + " where 1=1 ";

			}

			if (sql.indexOf("order by") != -1) {
				// 如果有order 就插入到order前面
				sql = sql.replaceFirst("order by", " and this_.listid in("
						+ mysql + ") order by");
			} else {
				// 若没有条件直接放后面
				sql = sql + " and this_.listid in(" + mysql + ")";
			}
			// sql = sql.replaceFirst("where ",
			// "where this_.listid in("+mysql+") and ");
			System.out.println("mysql6------->" + mysql);
		}
		// modify by readinho
		List list = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}

	public static void main(String[] args) {
		String sql = "select count(*) as y0_ from v_art_list this_ where this_.isclose=0 and (  this_.jieshouren='陈雄耀' or this_.dailiren='陈雄耀' )";
		if (sql.indexOf("order by") != -1) {
			sql = sql.substring(0, sql.indexOf("where")) + " where 1=1 "
					+ sql.substring(sql.indexOf("order by"), sql.length());
		} else {
			sql = sql.substring(0, sql.indexOf("where")) + " where 1=1 ";

		}
		System.out.println(sql);
	}

	public List getCriteriaSqlListID(Criteria criteria) {
		CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;// 转型
		SessionImplementor session = criteriaImpl.getSession();// 获取SESSION
		SessionFactoryImplementor factory = session.getFactory();// 获取FACTORY
		CriteriaQueryTranslator translator = new CriteriaQueryTranslator(
				factory, criteriaImpl, criteriaImpl.getEntityOrClassName(),
				CriteriaQueryTranslator.ROOT_SQL_ALIAS);
		String[] implementors = factory.getImplementors(criteriaImpl
				.getEntityOrClassName());
		CriteriaJoinWalker walker = new CriteriaJoinWalker(
				(OuterJoinLoadable) factory.getEntityPersister(implementors[0]),
				translator, factory, criteriaImpl, criteriaImpl
						.getEntityOrClassName(), (LoadQueryInfluencers) session.getEnabledFilters());
		QueryParameters queryParameters = translator.getQueryParameters();
		String sql = walker.getSQLString();
		System.out
				.println("isExvalue--------------------------------------------------------"
						+ this.isExvalue());

		System.out.println("checkmy-------------------------------->" + sql);
		List list = findBySqlID(sql,
				queryParameters.getPositionalParameterValues(),
				queryParameters.getPositionalParameterTypes());

		return list;
	}

	public List findBySqlID(String sql, Object[] objs, Type[] types) {
		// TODO Auto-generated method stub
		for (int i = 0; i < types.length; i++) {
			System.out.println(types[i].getClass());
			if (types[i] instanceof org.hibernate.type.StringType) {
				sql = sql.replaceFirst("\\?", "'" + objs[i].toString() + "'");

			}
			if (types[i] instanceof org.hibernate.type.IntegerType) {

				sql = sql.replaceFirst("\\?", objs[i].toString());
			}
		}
		System.out.println("eeeeeeeeeeeeeeeeeeeee--------->" + sql);
		List list = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql)
				.setResultTransformer(Transformers.TO_LIST).list();
		return list;
	}

	public List getCriteriaSqlListID(Criteria criteria, int firstResult,
			int maxResults) {
		CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;// 转型
		SessionImplementor session = criteriaImpl.getSession();// 获取SESSION
		SessionFactoryImplementor factory = session.getFactory();// 获取FACTORY
		CriteriaQueryTranslator translator = new CriteriaQueryTranslator(
				factory, criteriaImpl, criteriaImpl.getEntityOrClassName(),
				CriteriaQueryTranslator.ROOT_SQL_ALIAS);
		String[] implementors = factory.getImplementors(criteriaImpl
				.getEntityOrClassName());
		CriteriaJoinWalker walker = new CriteriaJoinWalker(
				(OuterJoinLoadable) factory.getEntityPersister(implementors[0]),
				translator, factory, criteriaImpl, criteriaImpl
						.getEntityOrClassName(), (LoadQueryInfluencers) session.getEnabledFilters());
		QueryParameters queryParameters = translator.getQueryParameters();
		String sql = walker.getSQLString();
		List list = findBySqlID(sql,
				queryParameters.getPositionalParameterValues(),
				queryParameters.getPositionalParameterTypes(), firstResult,
				maxResults);
		return list;
	}

	public List findBySqlID(String sql, Object[] objs, Type[] types,
			int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		for (int i = 0; i < types.length; i++) {
			System.out.println(types[i].getClass());
			if (types[i] instanceof org.hibernate.type.StringType) {
				sql = sql.replaceFirst("\\?", "'" + objs[i].toString() + "'");

			}
			if (types[i] instanceof org.hibernate.type.IntegerType) {

				sql = sql.replaceFirst("\\?", objs[i].toString());
			}
		}

		System.out.println("info--------->" + sql);

		if (this.isExvalue()) {
			String mysql = sql;
			// 截断排序
			if (mysql.indexOf("order by") != -1) {
				mysql = mysql.substring(0, mysql.lastIndexOf("order by"));
				System.out.println("mysql3------->" + mysql);
			}

			mysql = mysql.replaceAll(" this_.listid as y0_",
					" max(this_.listid) as y0_");
			if (mysql.indexOf("count") != -1) {// 替换count
				// /.replaceAll(" count(\\*) as y0_ ",
				// " max(this_.listid) as y0_")
				mysql = mysql.substring(0, mysql.indexOf("count"))
						+ " max(this_.listid) as y0_ "
						+ mysql.substring(mysql.indexOf("count") + 15,
								mysql.length());
			}
			System.out.println("mysql4------->" + mysql);
			// 解决慢的问题
			mysql = mysql.replace("v_art_list this_", "v_artclelisttemp this_");

			mysql = mysql + " group by this_.articleid";
			System.out.println("mysql5------->" + mysql);
			// 修改条件顺序不同查询快慢不同IN只能放在最后
			if (sql.indexOf("order by") != -1) {
				sql = sql.substring(0, sql.indexOf("where")) + " where 1=1 "
						+ sql.substring(sql.indexOf("order by"), sql.length());
			} else {
				sql = sql.substring(0, sql.indexOf("where")) + " where 1=1 ";

			}

			if (sql.indexOf("order by") != -1) {
				// 如果有order 就插入到order前面
				sql = sql.replaceFirst("order by", " and this_.listid in("
						+ mysql + ") order by");
			} else {
				// 若没有条件直接放后面
				sql = sql + " and this_.listid in(" + mysql + ")";
			}
			// /sql = sql.replaceFirst("where ",
			// "where this_.listid in("+mysql+") and ");
			System.out.println("mysql6------->" + mysql);
		}
		System.out.println("-------------my check1-----------------------");
		List list = null;
		try {
			System.out.println("-------------my check2-----------------------");
			list = this.getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createSQLQuery(sql)
					.setFirstResult(firstResult).setMaxResults(maxResults)
					.setResultTransformer(Transformers.TO_LIST).list();
			System.out.println("-------------my check3-----------------------");
		} catch (Exception e) {
			System.out.println("-------------my check4-----------------------");
			System.out.println(e.toString());
			e.fillInStackTrace();
		}
		System.out.println(list);
		return list;
	}

	public boolean isExvalue() {
		return exvalue;
	}

	public void setExvalue(boolean exvalue) {
		this.exvalue = exvalue;
	}

}