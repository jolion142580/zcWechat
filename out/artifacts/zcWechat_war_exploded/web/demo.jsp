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
    <%--<link rel="shortcut icon" href="/favicon.ico"/>--%>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="stylesheet" href="<%=basePath %>css/sm-extend.min.css" type="text/css"/>
    <link rel="stylesheet" href="<%=basePath %>css/sm.min.css" type="text/css"/>
    <link rel="stylesheet" href="<%=basePath %>css/jquery-weui.min.css" type="text/css"/>

    <link rel="stylesheet" href="<%=basePath %>lib/weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="<%=basePath %>css/guide-style.css" type="text/css"/>
    <script type="text/javascript" src="../js/jquery-1.12.4.min.js"></script>
    <script src="../scripts/jquery.js"></script>
    <script src="../scripts/jquery.qrcode.min.js"></script>
    <script type="text/javascript" src="../js/config.js"></script>
    <script type="text/javascript" src="../js/layer/layer.js"></script>
    <%--<script src="scripts/window.js" type="text/javascript"></script>--%>
</head>

<body>
<div id="pc_qr_code">

</div>
<button type="button" onclick="loadEWM()"> 按钮</button>
</body>

<script>
    /* 生成二维码微信扫一扫上传资料 */
    function loadEWM() {
        $("#pc_qr_code").empty();
        var content = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + weChat.APPID + "&redirect_uri=" + weChat.WeChatDNSURL + "onlineApply!isrelation?affairid=107_affairMaterialsByWeb_1&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
        ;
        $('#pc_qr_code').qrcode({
            render: "canvas",
            width: 200,
            height: 200,
            correctLevel: 0,
            text: content,
            background: "#ffffff",
            foreground: "black",
            src: ""
        });
        alert("提交成功\n请微信扫一扫进行资料的上传");
        layer.open({
            type: 1,
            closeBtn: 1,
            shade: false,
            skin: 'layui-layer-nobg',
            title: "扫码上传资料",
            anim: 1,
            area: ['auto', 'auto'],
            content: $('#pc_qr_code'),
            end: function () {
                <%--location.href = "<%=basePath %>web!history";--%>
            }
        })
        ;
    }
</script>
</html>
