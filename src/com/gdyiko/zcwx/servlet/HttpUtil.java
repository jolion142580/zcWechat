package com.gdyiko.zcwx.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdyiko.zcwx.weixinUtils.TokenHepl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gdyiko.zcwx.weixinUtils.TokenThread;
import com.gdyiko.zcwx.weixinUtils.WeixinHttpConnect;

public class HttpUtil extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory
			.getLogger(WeixinHttpConnect.class);

	public HttpUtil() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		WeixinHttpConnect httpconnect = new WeixinHttpConnect();
		long timestamp = System.currentTimeMillis() / 1000;
		String nonceStr = UUID.randomUUID().toString();

		String param = request.getQueryString();
		String url = request.getScheme() + "://" + request.getServerName()
				+ request.getRequestURI();
		if (param != null) {
			url = url + "?" + request.getQueryString();
		}
//		url = "http://zhengqiao.ss.gov.cn/ssWechat/HttpUtil.servlet";
		System.out.println("==url==" + url);
		// 特别注意的是调用微信js，url必须是当前页面(转发的不行)
		// http://www.fsyiko.com/ssWechat/affairProgress.jsp
		// String str =
		// "jsapi_ticket="+TokenThread.jsapi_ticket+"&noncestr="+nonceStr+"×tamp="+timestamp+"&url=http://www.fsyiko.com/ssWechat/HttpUtil";
		String string1 = "jsapi_ticket=" + TokenHepl.jsapi_ticket  //TokenThread.jsapi_ticket
				+ "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url="
				+ url;// "http://www.fsyiko.com/ssWechat/HttpUtil.servlet";

		String signature = httpconnect.sha1Encrypt(string1);
		RequestDispatcher rd = request
				.getRequestDispatcher("affairProgress.jsp");
		 String accerssToken = TokenHepl.getaccessToken().getAccessToken();//TokenThread.accessToken.getAccessToken();
//		 System.out.println("--accerssToken:-"+accerssToken);
		String jsApiTicket = TokenHepl.jsapi_ticket; //TokenThread.jsapi_ticket;
		request.setAttribute("timestamp", timestamp);
		request.setAttribute("nonceStr", nonceStr);
		request.setAttribute("signature", signature);
		// request.setAttribute("accessToken", accerssToken);
		request.setAttribute("jsapi_ticket", jsApiTicket);
		rd.forward(request, response);
	}

	/*
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException if an error occurs
	 */


}
