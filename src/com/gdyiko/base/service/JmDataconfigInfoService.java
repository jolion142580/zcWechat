package com.gdyiko.base.service;

import java.util.List;

import com.gdyiko.base.po.JmDataconfigInfo;
import com.gdyiko.tool.service.MyBaseGenericService;

public interface JmDataconfigInfoService extends MyBaseGenericService<JmDataconfigInfo, String> {

	public List<JmDataconfigInfo> getRootOj();
	
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

	public int findChildBySize(JmDataconfigInfo model, String id);

	public int findNotNullBySize(JmDataconfigInfo model, String string);
}
