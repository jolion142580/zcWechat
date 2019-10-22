<%@page import="com.gdyiko.zcwx.weixinUtils.WeixinHttpConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@page import="com.gdyiko.zcwx.weixinUtils.TokenHepl"%>


<%
    WeixinHttpConnect httpconnect = new WeixinHttpConnect();
	TokenHepl.accessToken = httpconnect.getAccess_token();
	out.print(TokenHepl.getaccessToken().getAccessToken());
%>


