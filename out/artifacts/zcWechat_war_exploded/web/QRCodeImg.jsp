<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>张槎街道行政服务中心</title>

    <meta name="viewport" content="width=device-width,height=device-height,initial-scale=1,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible"  content="IE=edge,chrome=1">

    <link rel="stylesheet" href="content/reflow.css">

    <link rel="stylesheet" href="content/interaction_pc.css"></head>

<script src="scripts/jquery.min.js" type="text/javascript"></script>
<script src="scripts/base.js" type="text/javascript"></script>
<script src="scripts/hammer.min.js" type="text/javascript"></script>

</head>

<body>

<div>
    <img id="imgId" src="QRCode!createQRCode" width="200px" height="200px">
    <div style="text-align:center;bottom:10px">使用手机微信扫一扫</div>
</div>

</body>
</html>
