package com.gdyiko.zcwx.weixinUtils;

import net.sf.json.JSONObject;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Shorturl {
	
	private static Logger log = LoggerFactory
			.getLogger(Shorturl.class);
	
	
	public String geturl(String long_url){
		
		String url ="https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";
		
		String access_token = TokenHepl.getaccessToken().getAccessToken();//TokenThread.accessToken.getAccessToken();
		
		String rsurl = url.replace("ACCESS_TOKEN", access_token);
		
		String json = "{\"action\":\"long2short\",\"long_url\":\""+long_url+"\"}";
		
		HttpContent hc = new HttpContent();
		String j="";
		try {
			 j=hc.getHttpContent(rsurl, json, "", "post");
			 log.info("Shorturl geturl j值:{}", j);
			 JSONObject jsono = JSONObject.fromObject(j);
				String errcode = jsono.getString("errcode");
				System.out.println(errcode);
				if(errcode.equals("40001")){
					WeixinHttpConnect httpconnect = new WeixinHttpConnect();
					try {
						TokenThread.accessToken = httpconnect.getAccess_token();
						
						 j=hc.getHttpContent(rsurl, json, "", "post");
						 log.info("Shorturl geturl 出错后重新获取j值:{}", j);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(j);
		
		return j;
	}
	
public String getpic(String long_url){
		
		String url ="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKENPOST";
		
		String access_token = TokenHepl.getaccessToken().getAccessToken();//TokenThread.accessToken.getAccessToken();
		
		String rsurl = url.replace("TOKENPOST", access_token);
		
		long scene_id=System.currentTimeMillis();  //场景值ID 充当为时间戳 
		
//		String json = "{\"action\":\"long2short\",\"long_url\":\""+long_url+"\"}";
		String json = "{\"expire_seconds\":\"43200\",\"action_name\":\"QR_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":"+scene_id+",\"scene_str\":\""+long_url+"\"}}}";
		
		HttpContent hc = new HttpContent();
		String j="";
		try {
			 j=hc.getHttpContent(rsurl, json, "", "post");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println();
		
		return j;
	}

public static String BaiduAPI2Url(String url)  
{  
	 String js="";
        try {
        	
            HttpContent httpost = new HttpContent();
            //String json="{\"url\":\""+url+"\"}";
            js = httpost.getHttpContent("http://api.k780.com:88/?app=shorturl.set&url="+url+"&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json", "", "", "post");
//           System.out.println(js);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        http://write.blog.csdn.net/postlist
	
	return js;
}  

/*public static void main(String[] args) {
	Shorturl.BaiduAPI2Url("http%3A%2F%2Fmeyerweb.com%2Feric%2Ftools%2Fdencoder%2F");
	
}*/
	
}
