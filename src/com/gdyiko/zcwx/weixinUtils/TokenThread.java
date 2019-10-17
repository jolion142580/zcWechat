package com.gdyiko.zcwx.weixinUtils;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gdyiko.zcwx.po.resp.Token;
import com.gdyiko.tool.StringUtils;


public class TokenThread implements Runnable {
	public void run() {

	}

    private static Logger log = LoggerFactory.getLogger(TokenThread.class);    
    // 第三方用户唯一凭证    
    public static String appid = "";    
    // 第三方用户唯一凭证密钥    
    public static String appsecret = "";    
    public static Token accessToken = null;    
    public static String jsapi_ticket=null;
    /*
    public void run() {
    	WeixinHttpConnect httpconnect;
		// TODO Auto-generated method stub
		while(true){
			try{
				accessToken = null;
				jsapi_ticket=null;
				httpconnect = new WeixinHttpConnect();
				accessToken = httpconnect.getAccess_token();
				//获取JSAPI_Ticket
				jsapi_ticket = httpconnect.getTicket(accessToken.getAccessToken());

				if(null != accessToken&&StringUtils.isNotBlank(accessToken.getAccessToken())){
					log.info("获取access_token成功，有效时长{}秒 token:{}",accessToken.getExpiresIn(),accessToken.getAccessToken());
					log.info("获取jsapi_ticket成功， jsapi_ticket:{}",jsapi_ticket);
					//休眠6200秒
					Thread.sleep((accessToken.getExpiresIn()-4000)*1000);
				}
				else{
					//如果access_token未null，60秒后在获取
					Thread.sleep(60*1000);
				}
*/
/*				OAuth oa = new OAuth();
				String userlist =oa.getuserinfo("oXBkJs6TUx_-iuNQa0Enh9kHWOe0");
				JSONObject userjson = JSONObject.fromObject(userlist);
				String user = userjson.getString("subscribe");
				if(user==null){
					
					Thread.sleep(60*1000);
				}*//*

				
			}catch(InterruptedException e){
				accessToken = null;
				jsapi_ticket=null;
				try{
					Thread.sleep(60*1000);
				}catch(InterruptedException e1){
					log.error("{}",e1);
				}
				log.error("{}",e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				accessToken = null;
				jsapi_ticket=null;
				e.printStackTrace();
			}
		}
	}*/
}
