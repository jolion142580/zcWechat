package com.gdyiko.tool.dao;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.type.Type;

/**
 * <p/>
 * 使用泛型作为DAO的通用接口 这里没有提供按名称精确查找,和模糊查找 上述两个方法应该由各自的具体接口去定义
 * <p/>
 *
 * @author Abu
 * @param <T> :
 * 持久化的实体Bean
 * @param <ID> :
 * 实体Bean的id
 */

public interface GenericDao<T, ID extends Serializable> {
    /**
     * 保存实体
     *
     * @param entity :
     *               实体
     * @return 保存后得到的id
     */
    public ID save(T entity);
    /**
     * 在查找所有记录的时候,使用提供查询语句,查询匹配的记录,否则将使用默认的查询语句查询数据的所有记录.
     *
     * @param hql : 自定义的HQL语句
     */
    public void setHql(String hql);
    /**
     * 
     * @return 自定义的HQL语句
     */
    public String getHql();
    /**
     * <p>
     * 删除实体
     * </p>
     *
     * @param entity :
     *               实体
     */
    public void remove(T entity);
    /**
     * <p>
     * 删除实体集合
     * </p>
     *
     * @param entities :
     *                 实体
     */
    public void removeAll(Collection<T> entities);
    /**
     * <p>
     * 修改实体
     * </p>
     *
     * @param entity :
     *               实体
     */
    public void modify(T entity);
    /**
     * <p>
     * 通过名字查找
     * </p>
     *
     * @param id :
     *           id
     * @return 找到的实体
     */
    public T findById(ID id);
    /**
     * <p/>
     * 查找全部实体
     * <p/>
     *
     * @return 所有实体的列表
     */
    public List<T> findAll();
    /**
     * <p>
     * 计算匹配查询条件的记录总数,如果没有注入或者设置hql语句,将使用默认的查询语句返回数据库中所有记录
     * </p>
     *
     * @return 记录总数
     */
    public int getTotalRows();
    /**
     * <p>
     * 根据每页记录的数量,计算出总的分页数
     * </p>
     *
     * @param size 每页记录的数量
     * @return 分页总数
     */
    public int getPageSize(int size);
    /**
     * <p/>
     * 根据给定的页码进行分页查找,这是纯Hibernate分页.
     * <p/>
     *
     * @param page : 要查询的页码
     *             查询的hql语句
     * @param size : 每页记录数
     *             分页信息,参见PageInfo
     * @return 匹配的实体列表
     */
	public List<T> findByPage(final int page, final int size);
	
	
	/**
     * <p/>
     * 创建与会话无关的检索标准.
     * <p/>
     *
 
     */
    public DetachedCriteria createDetachedCriteria();
    
    
    /**
     * <p/>
     * 创建与会话绑定的检索标准
     * <p/>
     *
 
     */
    public Criteria createCriteria() ;
	
    // 
    /**
     * <p/>
     * 检索满足标准的数据
     * <p/>
     *
 
     */
    public List findByCriteria(DetachedCriteria criteria) ;

   
    /**
     * <p/>
     *  检索满足标准的数据，返回指定范围的记录
     * <p/>
     *
 
     */
    public List findByCriteria(DetachedCriteria criteria, int firstResult,
            int maxResults) ;
    
    
    /**
     * <p/>
     *  使用指定的实体及属性检索（满足除主键外属性＝实体值）数据
     * <p/>
     *
 
     */
    public List<T> findEqualByEntity(T entity, String[] propertyNames) ;
    

    /**
     * <p/>
     *  使用指定的实体及属性检索（满足属性 like 串实体值）数据
     * <p/>
     *
 
     */
    public List<T> findLikeByEntity(T entity, String[] propertyNames) ;
    /**
     * <p/>
     *  使用指定的实体及属性检索（满足属性 like 串实体值）数据 补充分页
     * <p/>
     *
 
     */
    public List<T> findLikeByEntity(T entity, String[] propertyNames, int firstResult,
            int maxResults);
    // 
    /**
     * <p/>
     *  计算获得数据量
     * <p/>
     *
 
     */
    
    public Integer getRowCount(DetachedCriteria criteria) ;

    
    /**
     * <p/>
     *  使用指定的检索标准检索数据，返回指定统计值(max,min,avg,sum)
     * <p/>
     *
 
     */
    public Object getStatValue(DetachedCriteria criteria, String propertyName,
            String StatName) ;
    
    /*
     * 获得模糊查询数量
     * */
    public int findLikeByEntitySize(T entity, String[] propertyNames);
    
    public List findByHql(String hql);
    
    public List<T> findByHqlParam(String hql,Object[] values);
    
    public  List getCriteriaSqlListID(Criteria criteria, int firstResult, int maxResults);
    
    public List findBySqlID(String sql, Object[] objs, Type[] types, int firstResult, int maxResults);
    
    public void setExvalue(boolean exvalue);
    
    public boolean isExvalue();
}
