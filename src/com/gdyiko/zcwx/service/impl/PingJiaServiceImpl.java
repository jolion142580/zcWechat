package com.gdyiko.zcwx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.json.JSONException;
import org.springframework.stereotype.Service;

import com.gdyiko.base.service.PropertieService;
import com.gdyiko.zcwx.dao.StreetDao;
import com.gdyiko.zcwx.dao.YuYuesDao;
import com.gdyiko.zcwx.po.Street;
import com.gdyiko.zcwx.po.YuYues;
import com.gdyiko.zcwx.service.PingJiaService;
import com.gdyiko.zcwx.weixinUtils.HttpContent;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("pingJiaService")
public class PingJiaServiceImpl extends GenericServiceImpl<YuYues, String> implements PingJiaService 
{
	@Resource(name = "yuYuesDao")
	private YuYuesDao yuYuesDao;
	
	@Resource(name = "streetDao")
	private StreetDao streetDao;

	@Resource(name = "yuYuesDao")
	@Override
	public void setGenericDao(GenericDao<YuYues, String> genericDao) 
	{
		super.setGenericDao(genericDao);
	}
	
	@Resource(name="propertieService")
	PropertieService propertieService;
	
	/**
	 * 保存评价信息
	 * @param id	预约信息主键
	 * @param appraiseResult	服务评价
	 * @param appraiseAdvice	评价建议
	 */
	public void saveAppraiseInfo(String id , String USING,String allstart,String glstart,
				String fwstart ,String tdstart ,String UseAdvice,String OtherAdvice)
	{
		if( id == null || "".equals( id.trim() ) )
		{
			throw new RuntimeException("保存的对象和id主键均不能为null");
		}
		
		YuYues yuYues = this.yuYuesDao.findById(id);
		
		if( yuYues != null )
		{
			yuYues.setUSING(USING);
			yuYues.setAllstart(allstart);
			yuYues.setGlstart(glstart);
			yuYues.setFwstart(fwstart);
			yuYues.setTdstart(tdstart);
//			yuYues.setAppraiseResult(appraiseResult);
//			yuYues.setAppraiseAdvice(appraiseAdvice);
			yuYues.setUseAdvice(UseAdvice);
			yuYues.setOtherAdvice(OtherAdvice);
			this.yuYuesDao.modify(yuYues);
		}
		else
		{
			throw new RuntimeException("根据id查找失败");
		}
		
		this.yuYuesDao.modify(yuYues);
	}
	
	/**
	 * 根据id主键更新现场号码
	 * 
	 * @param id id主键
	 * @param queueNum	现场号码
	 */
	public void setQueueNum(String id, String queueNum)
	{
		if( id == null || "".equals( id.trim() ) )
		{
			throw new RuntimeException("id主键不能为null");
		}
		
		YuYues yuYues = this.yuYuesDao.findById(id);
		
		if( yuYues != null )
		{
			yuYues.setQueueNum(queueNum);
			this.yuYuesDao.modify(yuYues);
		}
		else
		{
			throw new RuntimeException("根据id查找失败");
		}
	}
	
	/**
	 * 根据street获取预约网点的名称
	 * 
	 * @param street	预约网点的编码
	 * 
	 * @return	成功,返回预约网点的名称
	 */
	public String getStreetOfNo(String street)
	{
		if( street == null || "".equals( street.trim() ) )
		{
			throw new RuntimeException("预约网点的编码street参数不能为null");
		}
		
		List<Street> list = this.streetDao.findByHqlParam("from Street s where s.no = ?", new String[]{street});
		
		if( list != null && list.size() > 0 )
		{
			return list.get(0).getName();
		}
		else
		{
			throw new RuntimeException("根据street获取预约网点的名称失败");
		}
	}
	
	/**
	 * 从另一个服务器中获取办理信息
	 * 
	 * @param id	预约信息的id主键
	 * @param streetStr	街道的拼音编码，即环市hs、白沙bs、潮连cl、荷塘ht、棠下tx、杜阮dr、堤东td
	 * @return
	 */
	public String getQueueInfo(String id, String streetStr)
	{
		if( id == null || "".equals( id.trim() ) )
		{
			throw new RuntimeException("预约信息的id主键不能为空");
		}
		if( streetStr == null || "".equals( streetStr.trim() ) )
		{
			throw new RuntimeException("街道的拼音编码不能为空");
		}
		
		String content = "";
		try 
		{	
			HttpContent httpConnect = new HttpContent();
			content=httpConnect.getHttpContent( propertieService.getPropertie("queuePath") + "/XzfwzxInterface/Queue!getQueueInfo?address="+streetStr+"&yyno="+id, "", "", "POST");
		} 
		catch (JSONException e) 
		{
			throw new RuntimeException("通过http调用远程服务器中的服务失败");
		}
		
		return content;
	}
}
