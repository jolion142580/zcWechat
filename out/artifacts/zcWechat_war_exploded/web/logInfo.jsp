<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
    String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path +"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>张槎街道行政服务中心</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1"/>
    <%--<link rel="shortcut icon" href="/favicon.ico"/>--%>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="stylesheet" href="css/sm-extend.min.css" type="text/css"></link>
    <link rel="stylesheet" href="css/sm.min.css" type="text/css"></link>
    <link rel="stylesheet" href="css/weui.min.css"/>
    <link rel="stylesheet" href="css/jquery-weui.css"/>
    <link rel="stylesheet" href="css/guide-style.css"/>
    <script src="scripts/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/layer/layer.js"></script>
    <%--<script src="scripts/window.js" type="text/javascript"></script>--%>
    <style type="text/css">
        #container {
            margin-top: 66px;
            width: 100%;
            height: 50%
        }

        .main_color {
            color: #FFFFFF;
        }
    </style>

    <style type="text/css">
        body {
            width: 100%;
            height: 100%;
        }

        tr td {
            line-height: 32px;
            font-size: 14px;
        }

        td {
            text-align: right;
        }

        td label {
            width: 20%;
        }

        td input {
            width: 38%;
        }

        body {
            background-color: white;
        }

        input[type="text"] {
            border-style: none;
            border-bottom-style: solid;
            border-bottom-width: thin;
            border-bottom-color: #0490E4;
            text-align: center;
            width: 100%;
        }

        fieldset {
            text-align: center;
        }

        .imgCard {
            height: 240px;
            width: 180px;
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
        function showImg(imgUrl) {
            var img_infor = "<img class='showImg' src='" + imgUrl + "' />";
            layer.open({
                id: "img",
                type: 1,
                closeBtn: 1,
                shade: false,
                title: false, //不显示标题
                //skin: 'layui-layer-nobg', //没有背景色
                anim: 1,
                maxmin: true,
                moveOut: true,
                shadeClose: false,
                area: ['800px', '520px'],
                content: img_infor,//捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响    
                resizing: function (layero) {
                    // console.log(layero);
                }
            });

        }
    </script>
</head>
<body onload="vailToken();">
<div>
    <header class="bar bar-nav"
            style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4;">

        <h1 class="title main_color ;">登录者认证信息</h1>
    </header>
</div>
<div id="container"><%----%>
    <div>
        <s:if test="ssUserInfo == null">
            <script>
                window.location = "<%=basePath%>web!login.action";
            </script>
        </s:if>
        <table width="100%">
            <tr>
                <td><label>姓名:</label></td>
                <td><input type="text" readonly
                           value="<s:property value="ssUserInfo.name"/>"></td>
                <td><label>性别:</label></td>
                <td><input type="text" readonly
                           value="<s:property value="ssUserInfo.sex"/>"></td>

            </tr>
            <tr>
                <td><label>出生日期:</label></td>
                <td><input type="text" readonly
                           value="<s:property value="ssUserInfo.brithday"/>"></td>
                <td><label>身份证号:</label></td>
                <td><input type="text" readonly
                           value="<s:property value="ssUserInfo.idCard"/>"></td>
            </tr>
            <tr>
                <td><label>联系电话:</label></td>
                <td><input type="text" readonly
                           value="<s:property value="ssUserInfo.phone"/>"></td>
                <td><label>住址:</label></td>
                <td><input type="text" readonly
                           value="<s:property value="ssUserInfo.address"/>"></td>
            </tr>

        </table>

        <s:if test="cardList.size() != 0">
            <br/>
            <br/>
            <fieldset class="par">
                <legend class="parLegend">登录认证身份证</legend>
                <div>
                    <s:iterator value="cardList">
                        <img class="imgCard" src="fileInfo!showImgByWeb?id=<s:property value="id" />"
                             title="点击查看大图"
                             onclick="showImg('fileInfo!showImgByWeb?id=<s:property value="id"/>')">
                    </s:iterator>
                </div>
            </fieldset>
        </s:if>

        <footer>
            <p>如需修改信息请在手机微信操作，本页只做查看。</p>
        </footer>
    </div>
</div>
</body>
</html>


