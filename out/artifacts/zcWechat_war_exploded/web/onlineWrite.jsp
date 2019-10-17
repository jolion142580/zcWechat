<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="com.gdyiko.zcwx.po.resp.Token" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%
    String affairid = request.getParameter("affairid");
    String objindex = request.getParameter("objindex");
    String uuid = (String) session.getAttribute("uuid");
//      System.out.println("sessionID_3:"+session.getId());
    String openid = (String) session.getAttribute("openid");
    if (uuid != null) {
//        openid = (String) application.getAttribute(uuid);
        openid = (String) ActionContext.getContext().getApplication().get(uuid);
    }

%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta name="generator"
          content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39"/>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>张槎街道行政服务中心</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1"/>
    <%--<link rel="shortcut icon" href="/favicon.ico"/>--%>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="stylesheet" href="css/sm-extend.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/sm.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css"/>

    <link rel="stylesheet" href="lib/weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/guide-style.css" type="text/css"/>
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script src="scripts/jquery.js"></script>
    <script src="scripts/jquery.qrcode.min.js"></script>
    <script type="text/javascript" src="js/config.js"></script>
    <script type="text/javascript" src="js/layer/layer.js"></script>
    <%--<script src="scripts/window.js" type="text/javascript"></script>--%>
    <style type="text/css">
        * {
            text-align: center;
        }
    </style>
    <%--初始化检测--%>
    <script type="text/javascript">
        function vailToken() {
            $.ajaxSettings.async = false;
            $.get("web!vailToken", function (data) {
                data = eval("(" + data + ")");
                if (data.flag == 'false') {
                    parent.window.location = "<%=basePath%>web!login";
                }
            })
            $.ajaxSettings.async = true;
        };
    </script>
    <script type="text/javascript">
        var onlineApplyId;
        $(function () {
            var r = confirm("直接上传表格？")
            if (r == true) {
                var content = "1";
                loadEWM(content);
            }
        });

        /* 生成二维码微信扫一扫上传资料 */
        function loadEWM(content) {
            var param = "content=" + content + "&affairid=<%=affairid%>&objindex=<%=objindex%>&onlineApplyId=" + onlineApplyId;
            window.location = "<%=basePath%>web/upLoadQRCode.jsp?" + param;
        }

        function onlineDataFun(inputData) {
            //防止token过期
            vailToken();
            $.post("<%=basePath %>onlineApply!onlineApplySaveToWeb", {
                affairid:<%=affairid%>,
                objindex:<%=objindex%>,
                openid: '<%=openid%>',
                /*  iscommit: 'true',*/
                onlineData: inputData
            }, function (res) {
                //res 事务ID
                res = eval("(" + res + ")");
                onlineApplyId = res.onlineApplyId;
                if (onlineApplyId == null || onlineApplyId == undefined) {
                    layer.alert("提交失败，请重新提交！");
                    return;
                }
                layer.alert("提交成功！");
                var content = "2";
                loadEWM(content);
            })
        }
    </script>
</head>

<%-- onload="vailToken();" --%>
<body onload="vailToken();" style="background-color:transparent;width: 100%;height:90%">
<iframe src="<%=basePath %>dist/#/preview?affairid=<%=affairid%>&objindex=<%=objindex %>"
        style=" width:100%; height:98%;"
        align="center" scrolling="auto" frameborder="0" allowTransparency="true">
</iframe>
<%--<div id="pc_qr_code">
</div>--%>
</body>
</html>
