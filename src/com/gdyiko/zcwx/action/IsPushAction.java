package com.gdyiko.zcwx.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.IsPush;
import com.gdyiko.zcwx.po.YuYuesFull;
import com.gdyiko.zcwx.service.IsPushService;
import com.gdyiko.zcwx.service.YuYuesFullService;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.service.GenericService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 预约业务完成时的推送功能
 * @author wu199406
 *
 */

@Namespace("/")
@Action(value = "IsPush", results = {
		
})
public class IsPushAction extends BaseAction<IsPush, String>
{
	private static final long serialVersionUID = -1047693794727285544L;
	
	@Resource(name = "isPushService")
	private IsPushService isPushService;
	
	@Resource(name = "yuYuesFullService")
	private YuYuesFullService yuYuesFullService;
	
	@Resource(name = "isPushService")
	@Override
	public void setGenericService(GenericService<IsPush, String> genericService) 
	{
		super.setGenericService(genericService);
	}
	
	/**
	 * 预约号推送方法
	 */
	public void push()
	{
		try
		{
			ActionContext context=ActionContext.getContext();
			//HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
			Map<String,Object>  parameterMap=context.getParameters(); 
	        
			String[] yynos = (String[])parameterMap.get("yyno");//获取要推送的预约号
			
			if( yynos != null )
			{
				for(String yyno:yynos)
				{
					boolean flat = this.isPushService.yynoIsPush(yyno);//判断该预约号是否已经推送了
					
					if( flat == false )
					{
//						System.out.println("推送预约号:"+yyno);
						
						YuYuesFull yuYuesFull = new YuYuesFull();
						yuYuesFull.setNo(yyno);
						
						List<YuYuesFull> list = this.yuYuesFullService.findEqualByEntity(yuYuesFull, new String[]{"no"});//获取指定的预约号的相关信息
						
						if( list != null && list.size() > 0 )
						{
							yuYuesFull = list.get(0);
							
							if( yuYuesFull != null )
							{
								//创建推送对象并推送
								SendMessageAction sendmsg = new SendMessageAction();
								sendmsg.setTitle("预约服务完成提醒：");
								String str="预约业务：" + yuYuesFull.getStypename() + "\n"; 
								str+="预约号:" + yuYuesFull.getNo() + "\n";
								str+="预约时间："+ yuYuesFull.getYdate() +"\n";
								str+="预约受理点：" + yuYuesFull.getStreetname() + "\n";
								str+="您的预约服务已经完成,现在可以进行评价!";
								sendmsg.setMsg(str);
								sendmsg.setMsgUrl("http://ymswx.pjq.gov.cn/pjWechat/PingJia!getPingJiaPage.action?myid=" + yuYuesFull.getId() );
								sendmsg.setOpenid( yuYuesFull.getOpenid() );
								sendmsg.send();
							}
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Struts2Utils.renderText(this.getResultJson(FLAG_FAIL));
		}
		
		Struts2Utils.renderText(this.getResultJson(FLAG_SUCCESS));
	}
}
