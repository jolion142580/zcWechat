<<<<<<< HEAD
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String content = request.getParameter("content");
    String affairid = request.getParameter("affairid");
    String objindex = request.getParameter("objindex");
    String onlineApplyId = request.getParameter("onlineApplyId");

    session.setAttribute("affairid", affairid);
    session.setAttribute("objindex", objindex);
    //onlineApplyId 可能为空 ..pc直接上传表格没记录
    if (onlineApplyId != null && !onlineApplyId.equals("") && !onlineApplyId.equals("null")) {
        session.setAttribute("onlineApplyId", onlineApplyId);
    }
    String openid = (String) session.getAttribute("openid");
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>张槎街道行政服务中心</title>
    <link rel="stylesheet" href="content/reflow.css">
    <link rel="stylesheet" href="content/interaction_pc.css">
    <script src="scripts/jquery.js"></script>
    <script src="scripts/jquery.qrcode.min.js"></script>
    <script type="text/javascript" src="js/config.js"></script>
    <%--<script src="scripts/window.js" type="text/javascript"></script>--%>
    <script type="text/javascript">
        var content = '<%=content%>';
        var affairid = '<%=affairid%>';
        var objindex = '<%=objindex%>';
        var onlineApplyId = '<%=onlineApplyId%>';
        $(function () {
            var ewmData;
            if (content == "1") {
                ewmData = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + weChat.APPID
                    + "&redirect_uri=" + weChat.weChatDNSURL
                    + "onlineApply!isrelation?affairid=<%=affairid%>_affairMaterials_<%=objindex%>&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
            } else if (content == "2") {
                ewmData = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + weChat.APPID
                    + "&redirect_uri=" + weChat.weChatDNSURL
                    + "onlineApply!isrelation?affairid=<%=affairid%>_affairMaterialsByPC2Wechart_<%=objindex%>_"
                    + onlineApplyId + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
            }
            loadEWM(ewmData);
        })

        function loadEWM(content) {
            $("#pc_qr_code").empty();
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
        }

        function skip() {
            if (content == "1") { //pc直接上传表格的..没有onlineApplyId
                $.ajax({
                    url: "<%=basePath%>onlineApply!onlineApplySaveToWeb",
                    type: "post",
                    async: false,
                    data: {
                        "affairid": affairid,
                        "objindex": objindex,
                        // "onlineData": null,
                        "openid": '<%=openid%>'
                    },
                    success: function (res) {

                    }
                })
            }
            window.location = "<%=basePath%>web/affairMaterials.jsp";
        }
    </script>
    <style type="text/css">
        button {
            margin-top: 12px;
            font-size: 18px;
            min-width: 120px;
        }

        .tip {
            /*margin-top: 36px;*/
            text-align: center;
            color: red;
            margin-bottom: 36px;
        }

        .main {
            text-align: center;
            background-color: #fff;
            border-radius: 20px;
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
        }

        .header {
            z-index: 1;
            display: block;
            width: 100%;
            height: 56px;
            line-height: 48px;
            background: #0490E4;
            color: #fff;
            overflow: hidden !important;
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
</head>
<body onload="vailToken();">
<div class="header titlebar">
    <h2 class="title">
        返回办事记录
    </h2>
    <a href="web!newHistory" class="btn_back link_checkJump"
    ><span class="ic ic_back"></span></a>
</div>
<div class="main">
    <div class="tip">
        使用手机微信扫一扫上传资料
        <div>
            <button onclick="skip()">电脑上传</button>
        </div>
    </div>
    <div id="pc_qr_code">
    </div>

</div>
</body>
</html>
=======
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String content = request.getParameter("content");
    String affairid = request.getParameter("affairid");
    String objindex = request.getParameter("objindex");
    String onlineApplyId = request.getParameter("onlineApplyId");

    session.setAttribute("affairid", affairid);
    session.setAttribute("objindex", objindex);
    //onlineApplyId 可能为空 ..pc直接上传表格没记录
    if (onlineApplyId != null && !onlineApplyId.equals("") && !onlineApplyId.equals("null")) {
        session.setAttribute("onlineApplyId", onlineApplyId);
    }
    String openid = (String) session.getAttribute("openid");
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>张槎街道行政服务中心</title>
    <link rel="stylesheet" href="content/reflow.css">
    <link rel="stylesheet" href="content/interaction_pc.css">
    <script src="scripts/jquery.js"></script>
    <script src="scripts/jquery.qrcode.min.js"></script>
    <script type="text/javascript" src="js/config.js"></script>
    <%--<script src="scripts/window.js" type="text/javascript"></script>--%>
    <script type="text/javascript">
        var content = '<%=content%>';
        var affairid = '<%=affairid%>';
        var objindex = '<%=objindex%>';
        var onlineApplyId = '<%=onlineApplyId%>';
        $(function () {
            var ewmData;
            if (content == "1") {
                ewmData =  weChat.WeChatDNSURL
                    + "onlineApply!isrelation?affairid=<%=affairid%>_affairMaterials_<%=objindex%>";
            } else if (content == "2") {
                ewmData = weChat.WeChatDNSURL
                    + "onlineApply!isrelation?affairid=<%=affairid%>_affairMaterialsByPC2Wechart_<%=objindex%>_"
                    + onlineApplyId;
            }
            loadEWM(ewmData);
        })

        function loadEWM(content) {
            $("#pc_qr_code").empty();
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
        }

        function skip() {
            if (content == "1") { //pc直接上传表格的..没有onlineApplyId
                $.ajax({
                    url: "<%=basePath%>onlineApply!onlineApplySaveToWeb",
                    type: "post",
                    async: false,
                    data: {
                        "affairid": affairid,
                        "objindex": objindex,
                        // "onlineData": null,
                        "openid": '<%=openid%>'
                    },
                    success: function (res) {

                    }
                })
            }
            window.location = "<%=basePath%>web/affairMaterials.jsp";
        }
    </script>
    <style type="text/css">
        button {
            margin-top: 12px;
            font-size: 18px;
            min-width: 120px;
        }

        .tip {
            /*margin-top: 36px;*/
            text-align: center;
            color: red;
            margin-bottom: 36px;
        }

        .main {
            text-align: center;
            background-color: #fff;
            border-radius: 20px;
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
        }

        .header {
            z-index: 1;
            display: block;
            width: 100%;
            height: 56px;
            line-height: 48px;
            background: #0490E4;
            color: #fff;
            overflow: hidden !important;
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
</head>
<body onload="vailToken();">
<div class="header titlebar">
    <h2 class="title">
        返回办事记录
    </h2>
    <a href="web!newHistory" class="btn_back link_checkJump"
    ><span class="ic ic_back"></span></a>
</div>
<div class="main">
    <div class="tip">
        使用手机微信扫一扫上传资料
        <div>
            <button onclick="skip()">电脑上传</button>
        </div>
    </div>
    <div id="pc_qr_code">
    </div>

</div>
</body>
</html>
>>>>>>> withoutWechatInterface
