<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@page import="com.gdyiko.zcwx.weixinUtils.TokenHepl"%>


<%
	System.out.println("--jsapi_ticket--"+TokenHepl.jsapi_ticket);
	out.print(TokenHepl.jsapi_ticket);
%>


