package com.gdyiko.zcwx.service;

import com.gdyiko.zcwx.po.YuYues;
import com.gdyiko.tool.service.GenericService;

/**
 * 评价服务
 * 
 * @author wu199406
 *
 */
public interface PingJiaService extends GenericService<YuYues, String>
{
	/**
	 * 保存评价信息
	 * @param id	预约信息主键
	 * @param appraiseResult	服务评价
	 * @param appraiseAdvice	评价建议
	 */
	public void saveAppraiseInfo(String id , String USING,String allstart,String glstart,String fwstart ,String tdstart ,String UseAdvice,String OtherAdvice);
	
	/**
	 * 根据id主键更新现场号码
	 * 
	 * @param id id主键
	 * @param queueNum	现场号码
	 */
	public void setQueueNum(String id, String queueNum);
	
	/**
	 * 根据street获取预约网点的名称
	 * 
	 * @param street	预约网点的编码
	 * 
	 * @return	成功,返回预约网点的名称
	 */
	public String getStreetOfNo(String street);
	
	/**
	 * 从另一个服务器中获取办理信息
	 * 
	 * @param id	预约信息的id主键
	 * @param streetStr	街道的拼音编码，即环市hs、白沙bs、潮连cl、荷塘ht、棠下tx、杜阮dr、堤东td
	 * @return
	 */
	public String getQueueInfo(String id, String streetStr);
}
