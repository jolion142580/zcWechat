<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.gdyiko.zcwx.weixinUtils.TokenHepl"%>


<%
	System.out.println("--accessToken--"+TokenHepl.getaccessToken().getAccessToken());
	out.print(TokenHepl.getaccessToken().getAccessToken());
%>

