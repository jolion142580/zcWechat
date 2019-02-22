<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta name="generator"
          content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39"/>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>张槎街道行政服务中心</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1"/>
    <link rel="shortcut icon" href="/favicon.ico"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="stylesheet" href="<%=basePath %>css/sm-extend.min.css" type="text/css"/>
    <link rel="stylesheet" href="<%=basePath %>css/sm.min.css" type="text/css"/>
    <link rel="stylesheet" href="<%=basePath %>css/jquery-weui.min.css" type="text/css"/>

    <link rel="stylesheet" href="<%=basePath %>lib/weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="<%=basePath %>css/guide-style.css" type="text/css"/>
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>

</head>

<%
    String uuid = (String) session.getAttribute("uuid");
//todo 注释了String openid = (String)session.getAttribute(uuid);  并修改未 session.getAttribute("openid")
    String openid = (String) session.getAttribute("openid");
//String openid="1111";
    String affairid = request.getParameter("affairid");
    String objindex = request.getParameter("objindex");
    if (openid == null) {
        response.sendRedirect("Index.jsp");
    }
%>

<body style="background-color:transparent">

<iframe src="<%=basePath %>dist/#/preview?affairid=<%=affairid%>&objindex=<%=objindex %>" width="100%" height="100%"
        align="center" scrolling="auto" frameborder="0" allowTransparency="true">
</iframe>

</body>
</html>
<script>
    function onlineDataFun(inputData) {
//alert(inputData);
        $.post("<%=basePath %>onlineApply!onlineApplySaveToWeb", {
            affairid:<%=affairid%>,
            objindex:<%=objindex%>,
            openid: '<%=openid%>',
            onlineData: inputData,
            iscommit: 'true'
        }, function (res) {

            alert("提交成功，请关注\"张槎街道行政服务中心\"微信公众号进行资料的上传");
            location.href = "<%=basePath %>web/Index.jsp";

        })

    }
</script>
