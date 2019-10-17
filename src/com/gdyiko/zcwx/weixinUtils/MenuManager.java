package com.gdyiko.zcwx.weixinUtils;

import java.io.File;

import com.gdyiko.base.service.WeChatPropertieService;
import com.gdyiko.tool.Xml2Json;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class MenuManager {


	/** 
	 * 菜单管理器类 
	 */  
	public static void main(String[] args) { 
		StringBuffer jsonMenu = new StringBuffer();

//		 jsonMenu.append("{").append("\"button\":[{\"name\": \"软件产品\",\"sub_button\": [{\"type\": \"view\",\"name\": \"信息发布\",\"url\": \"http://localhost"},{\"type\": \"view\",\"name\": \"稽查执法\",\"url\": \"http://dbyjzf.wicp.net/weixin/jichazhifa.htm\"},{\"type\": \"view\",\"name\": \"稽查移动\",\"url\":\"http://dbyjzf.wicp.net/weixin/jichayidong.htm\"},{\"type\": \"view\",\"name\": \"门户网站\",\"url\": \"http://dbyjzf.wicp.net/weixin/menhuwangzhan.htm\"},{\"type\": \"view\",\"name\": \"工程项目\",\"url\": \"http://localhost\"}]},");
//		 jsonMenu.append("{").append("\"name\": \"工程案例\",\"sub_button\": [{\"type\": \"view\",\"name\": \"数字法庭\",\"url\": \"http://localhost\"}, {\"type\": \"view\",\"name\": \"视频监控\",\"url\": \"http://localhost\"},{\"type\": \"view\",\"name\":\"排队叫号\",\"url\": \"http://dbyjzf.wicp.net/weixin/paiduijiaohao.htm\"},{\"type\": \"view\",\"name\": \"停车场系统\",\"url\": \"http://localhost\" }]},");
//		 jsonMenu.append("{").append("\"type\": \"view\",\"name\": \"联系我们\",\"url\": \"http://dbyjzf.wicp.net/weixin/touch.htm\"");
//       jsonMenu.append("}]}"); 

         /*jsonMenu.append("{").append("\"button\":[{\"name\": \"服务信息\",\"sub_button\": [{\"type\": \"view\",\"name\": \"首页\",\"url\": \"http://www.swxzzx.gov.cn\"},{\"type\": \"click\",\"name\": \"办事指南\",\"key\":\"banshizhinan\"},{\"type\": \"click\",\"name\": \"新闻动态\",\"key\": \"xinwen\"},{\"type\": \"view\",\"name\": \"佛山天气\",\"url\": \"http://wap.gz121.com/html/weixinportal/weixinportal.html?districtcode=440600\"},{\"type\": \"view\",\"name\": \"交通指引\",\"url\": \"http://58.252.105.173/weixin/jiaotongzhiyin.jsp\"}]},");
		 jsonMenu.append("{").append("\"name\": \"微服务\",\"sub_button\": [{\"type\": \"click\",\"name\": \"审批即办件\",\"key\": \"shenpibanjian\"},{\"type\": \"click\",\"name\": \"业务咨询\",\"key\": \"yewuzixun\"}, {\"type\": \"click\",\"name\": \"行政投诉\",\"key\": \"xingzhengtousu\"},{\"type\": \"click\",\"name\": \"网上预约\",\"key\": \"wangshangyuyue\"}, {\"type\": \"click\",\"name\": \"办件查询\",\"key\": \"banjianchaxun\"}]},");
		 jsonMenu.append("{").append("\"name\": \"微互动\",\"sub_button\": [{\"type\": \"view\",\"name\": \"建言献策 \",\"url\": \"http://www.foshan.gov.cn/fsjyxc\"},{\"type\": \"view\",\"name\": \"四风投诉\",\"url\": \"http://12345.fsxzfw.gov.cn/main/main!sfzt.action\"},{\"type\": \"view\",\"name\": \"网络发言人\",\"url\": \"http://wz.foshan.gov.cn\"},{\"type\": \"view\",\"name\": \"空中一门式\",\"url\": \"http://58.252.105.173/weixin/yimenshi.jsp\"},{\"type\": \"view\",\"name\": \"禅城区网上办事大厅\",\"url\": \"http://wssp.fsxzfw.gov.cn/?areaId=440604\"}]");
         jsonMenu.append("}]}"); */
         
//         jsonMenu.append("{").append("\"button\":[{\"name\": \"服务信息\",\"sub_button\": [{\"type\": \"click\",\"name\": \"绑定\",\"key\":\"bangding\"}]");
//         jsonMenu.append("}]}"); 
        String APPID = WeChatPropertieService.getPropertieByStatic("APPID");
        String APPSECRET = WeChatPropertieService.getPropertieByStatic("APPSECRET");
	    String WeChatDNSURL = WeChatPropertieService.getPropertieByStatic("WeChatDNSURL");
		jsonMenu=Xml2Json.xml2JSON("src/Menu2.xml");

        while ( jsonMenu.indexOf("${APPID}")!= -1 ) {
            jsonMenu.replace(jsonMenu.indexOf("${APPID}"),jsonMenu.indexOf("${APPID}")+8,APPID);
        }
        while (  jsonMenu.indexOf("${WeChatDNSURL}")!= -1 ) {
            jsonMenu.replace(jsonMenu.indexOf("${WeChatDNSURL}"),jsonMenu.indexOf("${WeChatDNSURL}")+15,WeChatDNSURL);
        }
        //  jsonMenu.re
         //通过获取凭证接口获取到access_token
         String access_token_url ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";



//         String requestUrl = access_token_url.replace("APPID", APPID).replace("APPSECRET", "459511a62974570c78125863dcad0070");

         //todo appid要改微信公众号测试
         String requestUrl = access_token_url.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
         
         JSONObject jsonObject = CreateMenu.httpRequest(requestUrl, "GET", null);  
         String accessToken="";
         if (null != jsonObject) {  
             try { 
            	 System.out.println("jsonObject::::"+jsonObject);
            	 accessToken=jsonObject.getString("access_token"); 
            	 
             } catch (JSONException e) {    
                 // 获取token失败  
                 e.printStackTrace();  
             }  
         }  
  
//         System.out.println(jsonMenu.toString());
        // 调用接口创建菜单  
        int result = CreateMenu.createMenu(jsonMenu.toString(),accessToken);  

        // 判断菜单创建结果  
        if (0 == result)  
           System.out.println("菜单创建成功！");
           //log.info("菜单创建成功！");  
        else  
           System.out.println("菜单创建失败，错误码："+result);
           //log.info("菜单创建失败，错误码：" + result);  

	}

}
