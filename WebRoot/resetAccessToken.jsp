<%@page import="com.gdyiko.zcwx.weixinUtils.WeixinHttpConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.gdyiko.zcwx.weixinUtils.TokenThread"%>


<%
    WeixinHttpConnect httpconnect = new WeixinHttpConnect();
    TokenThread.accessToken = httpconnect.getAccess_token();
	out.print(TokenThread.accessToken.getAccessToken());
%>

