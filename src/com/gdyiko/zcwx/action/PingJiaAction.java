package com.gdyiko.zcwx.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.YuYues;
import com.gdyiko.zcwx.service.PingJiaService;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.service.GenericService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 评价功能
 * @author wu199406
 *
 */

@Namespace("/")
@Action(value = "PingJia", results = {
	@Result(name = "pingJia", location = "/pingJia.jsp"),//获取评价页面
	@Result(name = "fail", location = "/")//失败

})
public class PingJiaAction extends BaseAction<YuYues, String> 
{
	private static final long serialVersionUID = 6017554449649815478L;
	
	@Resource(name = "pingJiaService")
	private PingJiaService pingJiaService;
	
	@Resource(name = "pingJiaService")
	@Override
	public void setGenericService(GenericService<YuYues, String> genericService) 
	{
		super.setGenericService(genericService);
	}
	
	@Override
	public String execute() throws Exception 
	{
		return super.execute();
	}
	
	/**
	 * 获取评价页面
	 * 
	 * @return
	 */
	public String getPingJiaPage()
	{
		if( this.myid == null || "".equals(this.myid.trim()) )
		{
			throw new RuntimeException("myid请求参数不能为空");
		}
		
		//获取httpServletRequest
		ActionContext actionContext = ActionContext.getContext(); 
		Map<String,Object> request = (Map) actionContext.get("request"); 
		
		YuYues yuYues = this.pingJiaService.findById(myid);
		
		request.put("yuYues", yuYues);
		try
		{
			request.put("streetName", this.pingJiaService.getStreetOfNo(yuYues.getStreet()) );
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return "pingJia";
	}
	
	/**
	 * 评价处理
	 * @return
	 */
	public String pingjiaDO()
	{
		YuYues yuYues = this.model;//获取请求对象
		
		String result = null;
		
		try 
		{
			if( this.model == null )
			{
				throw new Exception("相关参数不能为空");
			}
			
			this.pingJiaService.saveAppraiseInfo(this.model.getId(), this.model.getUSING(), this.model.getAllstart(), this.model.getGlstart(), this.model.getFwstart(), this.model.getTdstart(),this.model.getUseAdvice(),this.model.getOtherAdvice());
			result = this.getResultJson(FLAG_SUCCESS);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			result = this.getResultJson(FLAG_FAIL);
		}
		
		Struts2Utils.renderText(result);
		return null;
	}
	
	/**
	 * 根据id主键更新现场号码
	 * @return
	 */
	public String setQueueNum()
	{
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
        
        String id = request.getParameter("id");//获取id主键
        String queueNum = request.getParameter("queueNum");//获取现场号码
        
        String result = null;
        try 
        {
			this.pingJiaService.setQueueNum(id, queueNum);
			result = this.getResultJson(FLAG_SUCCESS);
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
			result = this.getResultJson(FLAG_FAIL);
		}
        
        Struts2Utils.renderText(result);
        return null;
	}
	
	/**
	 * 获取预约的处理结果信息
	 * 
	 * @return	成功返回处理结果的json字符串;失败返回{flat:"0"}
	 */
	public String getQueueInfo()
	{
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
        
        String id = request.getParameter("id");//应该的预约号
        String streetStr = request.getParameter("streetStr");//获取街道编号
        
        String result = null;
        try 
        {
			result = this.pingJiaService.getQueueInfo(id, streetStr);
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
			result = this.getResultJson(FLAG_FAIL);
		}
        
        Struts2Utils.renderText(result);
        return null;
	}
	
	
	
	//评价成功后的回调页面的路径
	private String backUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxeae24884fc77e6ff&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/wdyy_2.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
}
