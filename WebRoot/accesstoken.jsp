<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.gdyiko.zcwx.weixinUtils.TokenThread"%>


<%
	System.out.println("--accessToken--"+TokenThread.accessToken.getAccessToken());
	out.print(TokenThread.accessToken.getAccessToken());
%>

