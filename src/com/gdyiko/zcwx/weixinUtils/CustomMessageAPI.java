package com.gdyiko.zcwx.weixinUtils;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import com.gdyiko.zcwx.po.resp.Article;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class CustomMessageAPI {
	 /**

     * 微信公共账号发送给账号

     * @param content 文本内容

     * @param toUser 微信用户  

     * @return

     */

     public  String sendTextMessageToUser(String content,String toUser){

    	String path = getClass().getResource("/").toString();
    	
       String json = "{\"touser\": \""+toUser+"\",\"msgtype\": \"text\", \"text\": {\"content\": \""+content+"\"}}";
//         System.out.println("sendTextMessageToUser----------->:"+json);
       String result="";
       
       try {
//    	   String accessToken=CompareQueue.readTxtFile(path.substring(6,path.indexOf("WEB-INF"))+"access_token.txt");
    	   String accessToken = TokenHepl.getaccessToken().getAccessToken();//TokenThread.accessToken.getAccessToken();
    	   
//	       System.out.println("accessToken-------"+accessToken);
	       //获取请求路径
	       String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+accessToken;
	       
//	       System.out.println("json:"+json);
	       HttpContent httpcontent = new HttpContent();
           result = httpcontent.getHttpContent(action, json, "", "post");
           
           JSONObject jo=JSONObject.fromObject(result);
           String errcode= jo.getString("errcode");
           if(errcode.equals("42001")){
        	   accessToken=TokenHepl.getaccessToken().getAccessToken();//TokenThread.accessToken.getAccessToken();
        	   
        	   action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+accessToken;

//    	       System.out.println("json:"+json);

               result =  httpcontent.getHttpContent(action, json, "", "post");
           }

       } catch (Exception e) {

           e.printStackTrace();

       }
       
       return result;

   }
    
    /**

     *  发送图文给所有的用户

     * @param openId 用户的id
     * @throws IOException 

     */

    public  String sendNewsToUser(String openId,ArrayList<Article> articles) throws IOException{

    	String result="";
    	

//    	ArrayList<Object> articles = new ArrayList<Object>();

//      Article a = new Article();
//
//      articles.add(a);
       
       JSONArray jsonarry = JSONArray.fromObject(articles); 
       
       String str = jsonarry.toString();//JsonUtil.getJsonStrFromList(articles);

        String json = "{\"touser\":\""+openId+"\",\"msgtype\":\"news\",\"news\":" +

                "{\"articles\":" +str +"}"+"}";

        json = json.replace("picUrl", "picurl");

//        System.out.println("sendNewsToUser===="+json);

        //获取access_token
        WeixinHttpConnect httpconnect = new WeixinHttpConnect();

       String access_token =  httpconnect.getAccess_token().getAccessToken();//TokenThread.accessToken.getAccessToken();
//       System.out.println("access_token----"+access_token);
       //获取请求路径

       String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+access_token;

       try {
    	   
    	   HttpContent httpcontent = new HttpContent();
    	   result=  httpcontent.getHttpContent(action, json, "", "post");

       } catch (Exception e) {

           e.printStackTrace();

       }
       return result;
    }
    
    
    /**
     * 发送模板消息
     * @param t
     * @param
     * @return
     */
    public String sendTemplateMessage(WxTemplate t) {
        String sendTemplateMessage_url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";  
        String result = "";
        String accessToken = TokenHepl.getaccessToken().getAccessToken();//TokenThread.accessToken.getAccessToken();
        // 拼装创建菜单的url
        System.out.println("accessToken---wx--"+accessToken);
        //bymao token
        String url = sendTemplateMessage_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(t).toString();
        // 调用接口创建菜单
        HttpContent httpcontent = new HttpContent();

        try {
			result = httpcontent.getHttpContent(url,jsonMenu,"","POST");
			JSONObject jsonObject = JSONObject.fromObject(result);
	        
	        if (null != jsonObject) {
	            if (0 != jsonObject.getInt("errcode")) {
	                System.out.println("发送模板消息失败 errcode:"+jsonObject.getInt("errcode")+"errmsg:"+ jsonObject.getString("errmsg"));
	            }
	        }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return result;
    }
    
/*    public static void main(String[] args) throws IOException {
    	ArrayList<Article> articles =new ArrayList<Article>();
    	Article a = new Article();
    	a.setTitle("测试！");
    	a.setUrl("http://test");
    	a.setPicUrl("picurl");
    	a.setDescription("1234455");
    	articles.add(a);
    	CustomMessageAPI api = new CustomMessageAPI();
    	System.out.println(api.sendNewsToUser("oxGFmwY3Dnhq3NzJXGGYPohSAf4I", articles));
	}
*/
}
