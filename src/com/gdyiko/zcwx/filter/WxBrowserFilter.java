package com.gdyiko.zcwx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WxBrowserFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;    
	    HttpServletResponse response = (HttpServletResponse) servletResponse;
	    HttpSession session = request.getSession();  
	    
		//判断 是否是微信浏览器
		String userAgent = request.getHeader("user-agent").toLowerCase();
		if(userAgent.indexOf("micromessenger")>-1){//微信客户端
		    //request.setAttribute("isWx", "1");
		    filterChain.doFilter(servletRequest, servletResponse); 
		}else{
		    //request.setAttribute("isWx", "0");
			return;
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
