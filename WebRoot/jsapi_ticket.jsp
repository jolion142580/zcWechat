<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.gdyiko.zcwx.weixinUtils.TokenThread"%>


<%
	System.out.println("--jsapi_ticket--"+TokenThread.jsapi_ticket);
	out.print(TokenThread.jsapi_ticket);
%>

