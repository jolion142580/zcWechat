<%--
  Created by IntelliJ IDEA.
  User: jolion
  Date: 2019-04-03
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.WxJSSignUtil" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.TokenHepl" %>

<%
        String token = TokenHepl.getaccessToken().getAccessToken();
    String jsapi_ticket = TokenHepl.jsapi_ticket;
    String url = WxJSSignUtil.getUrl();
    System.out.println("==url==" + url);
    System.out.println("jsapi_ticket==" + jsapi_ticket);
    Map map = WxJSSignUtil.sign(jsapi_ticket, url);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
