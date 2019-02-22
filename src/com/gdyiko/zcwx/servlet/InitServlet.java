package com.gdyiko.zcwx.servlet;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gdyiko.zcwx.weixinUtils.TokenThread;
import com.gdyiko.zcwx.weixinUtils.WeixinHttpConnect;

public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory
			.getLogger(WeixinHttpConnect.class);
	
	public InitServlet(){
		super();
	}
	
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		System.out.println("----------初始化-!-----------");
		// Put your code here
		// 获取web.xml中配置的参数
		try {
			InputStream in = getClass().getResourceAsStream(
					"/wechat.properties");
			Properties props = new Properties();

			props.load(in);
			in.close();

			TokenThread.appid = props.getProperty("APPID");
			TokenThread.appsecret = props.getProperty("APPSECRET");

		

			log.info("weixin api appid:{}", TokenThread.appid);
			log.info("weixin api appsecret:{}", TokenThread.appsecret);

			// 未配置appid、appsecret时给出提示
			if ("".equals(TokenThread.appid)
					|| "".equals(TokenThread.appsecret)) {
				log.error("appid and appsecret configuration error,please check carefully.");
			} else {
				// 启动定时获取access_token的线程
				new Thread(new TokenThread()).start();
				System.out.println("1:");
				// System.out.println("1:"+TokenThread.accessToken.getAccessToken());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		init();
		super.doPost(req, resp);
	}

}
