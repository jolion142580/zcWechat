package com.gdyiko.zcwx.weixinUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gdyiko.zcwx.po.resp.Token;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class WeixinHttpConnect {
	private static Logger log = LoggerFactory
			.getLogger(WeixinHttpConnect.class);

	public JSONObject httpRequest(String requestUrl, String requestMethod,
			String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			// System.out.println(buffer.toString());
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			ce.printStackTrace();
			// log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("https request error:{}", e);
		}
		return jsonObject;
	}

	public Token getAccess_token() throws IOException {
		Token accessToken = null;
		// 通过获取凭证接口获取到access_token
		String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

		InputStream in = getClass().getResourceAsStream("/wechat.properties");
		Properties props = new Properties();

		props.load(in);
		in.close();
		String appid = props.getProperty("APPID");
		String appsecret = props.getProperty("APPSECRET");
//		System.out.println("========================"+appid);
		String requestUrl = access_token_url.replace("APPID", appid).replace(
				"APPSECRET", appsecret);

		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				accessToken = new Token();
				System.out.println("-=-=-="+jsonObject);
				accessToken.setAccessToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
				// accessToken=jsonObject.getString("access_token");
				// System.out.println("access_token::::"+accessToken);
			} catch (JSONException e) {
				// 获取token失败
				e.printStackTrace();
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}

	public String getTicket(String accessToken) throws IOException {

		// 通过获取凭证接口获取到jsapi_ticket
		// String access_token_url
		// ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

		String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN",
				accessToken);

		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		String jsapi_ticket = "";
		if (null != jsonObject) {
			try {
				jsapi_ticket = jsonObject.getString("ticket");
//				System.out.println("jsapi_ticket::::" + jsapi_ticket);
			} catch (JSONException e) {
				// 获取jsapi_ticket失败
				e.printStackTrace();
			}
		}
		return jsapi_ticket;
	}

	/*
	 * sha1加密
	 * 
	 * @param str
	 * 
	 * @return
	 */
	public static String sha1Encrypt(String str) {
		String signature = null;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(str.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return signature;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
	/*
	 * public static void main(String[] args) { WeixinHttpConnect httpconnect =
	 * new WeixinHttpConnect(); try { String jsapi_ticket =
	 * httpconnect.getTicket(); System.out.println(jsapi_ticket); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * } }
	 */
}
