package com.gdyiko.base.dao;

import java.util.List;

import com.gdyiko.base.po.JmDataconfigInfo;
import com.gdyiko.tool.dao.MyBaseGenericDao;

public interface JmDataconfigInfoDao extends MyBaseGenericDao<JmDataconfigInfo, String> {
	
	/**
	 * 
	
	* @Title: findNotNullByPage 
	
	* @Description: TODO(查询父为空值的数据) 
	
	* @param @param entity
	* @param @param nullColumnName
	* @param @param firstResult
	* @param @param maxResults
	* @param @return    设定文件 
	
	* @return List<JmDataconfigInfo>    返回类型 
	
	* @throws
	 */
	
    public List<JmDataconfigInfo> findNotNullByPage(JmDataconfigInfo entity, String nullColumnName, int firstResult,
            int maxResults);
 
    /**
     * 
    
    * @Title: findChildByPage 
    
    * @Description: TODO(查询父ID内的) 
    
    * @param @param entity
    * @param @param parentid
    * @param @param firstResult
    * @param @param maxResults
    * @param @return    设定文件 
    
    * @return List<JmDataconfigInfo>    返回类型 
    
    * @throws
     */
    public List<JmDataconfigInfo> findChildByPage(JmDataconfigInfo entity, String parentid, int firstResult,
            int maxResults);
    
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
    public int findNotNullBySize(JmDataconfigInfo entity, String nullColumnName);
    
    // 查询子数量
    public int findChildBySize(JmDataconfigInfo entity, String parentid);
}
