package com.gdyiko.zcwx.weixinUtils;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

public class OAuth {

	public String getOppenid(String code) {

		String user_openid = "";
		int flag = 0;
		String path = getClass().getResource("/").toString();

		String get_access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid=APPID"
				+ "&secret=SECRET&"
				+ "code=CODE&grant_type=authorization_code";

		// String
		// get_userinfo="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

		InputStream in = getClass().getResourceAsStream("/wechat.properties");
		Properties props = new Properties();

		try {
			props.load(in);
			in.close();

			String appid = props.getProperty("APPID");
			String appsecret = props.getProperty("APPSECRET");
/*			System.out.println("----appid---" + appid + "---appsecret---"
					+ appsecret);


			System.out.println("【【oauth】appid】" + appid);
			System.out.println("【【oauth】appsecret】" + appsecret);
			System.out.println("【【oauth】code】" + code);
*/

			// 使用code换取access_token
			get_access_token_url = get_access_token_url.replace("APPID", appid);
			get_access_token_url = get_access_token_url.replace("SECRET",appsecret);
			get_access_token_url = get_access_token_url.replace("CODE", code);

//			System.out.println("【【oauth】url】" + get_access_token_url);

			WeixinHttpConnect weixinHttpConnect = new WeixinHttpConnect();
			JSONObject jsonObject = weixinHttpConnect.httpRequest(
					get_access_token_url, "GET", null);
			// JSONObject jsonObject=JSONObject.fromObject(content);
			System.out.println("???" + jsonObject);

			// 使用access_token获取用户信息
			String access_token = jsonObject.getString("access_token");
			user_openid = jsonObject.getString("openid");
			
			HttpServletRequest request = ServletActionContext.getRequest(); 
			HttpSession session = request.getSession(); 
			session.setAttribute("openid", user_openid);

			// scope 应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
			// snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。
			// 并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
			// get_userinfo=get_userinfo.replace("ACCESS_TOKEN", access_token);
			// get_userinfo=get_userinfo.replace("OPENID", openid);
			//
			// JSONObject userInfoJO=weixinHttpConnect.httpRequest(get_userinfo,
			// "GET", null);
			// user_openid=userInfoJO.getString("openid");
			//
			// String user_nickname=userInfoJO.getString("nickname");
			// String user_sex=userInfoJO.getString("sex");
			// String user_province=userInfoJO.getString("province");
			// String user_city=userInfoJO.getString("city");
			// String user_country=userInfoJO.getString("country");
			// String user_headimgurl=userInfoJO.getString("headimgurl");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user_openid;
	}

	public String getuserinfo(String openid) {

		String accessToken = "";

		try {
			accessToken = TokenHepl.getaccessToken().getAccessToken();//TokenThread.accessToken.getAccessToken();
//			System.out.println("---accessToken---" + accessToken);

			// 通过获取凭证接口获取到jsapi_ticket
			String get_userinfo = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

			String requestUrl = get_userinfo.replace("ACCESS_TOKEN",
					accessToken).replace("OPENID", openid);
			WeixinHttpConnect weixinHttpConnect = new WeixinHttpConnect();
			JSONObject jsonObject = weixinHttpConnect.httpRequest(requestUrl,
					"GET", null);

			return jsonObject.toString();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

}
