﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path +"/";
%>
<%
    String baseDic = "", baseDicId = "", baseDicType = "";
    baseDic = (String) session.getAttribute("baseDic");
    baseDicId = (String) session.getAttribute("baseDicId");
    baseDicType = (String) session.getAttribute("baseDicType");
//      System.out.println("sessionID_2:"+session.getId());
    session.removeAttribute("baseDic");
    session.removeAttribute("baseDicId");
    session.removeAttribute("baseDicType");
%>
<%--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">--%>
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
    <link rel="stylesheet" href="lib/weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/guide-style.css" type="text/css"/>

    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="js/jquery-weui.js"></script>
    <script type="text/javascript" src="lib/zepto.js"></script>
    <script src="js/config.js"></script>
    <%--<script src="scripts/window.js" type="text/javascript"></script>--%>
    <style type="text/css">


        .main_color {

            color: #FFFFFF;
        }

        .weui-cells {
            margin-top: 0;
        }

        .weui-search-bar {
            background-color: #0490E4;
        }

        .weui-search-bar__form {
            background-color: #0490E4;
        }

        .weui-search-bar__cancel-btn {
            color: #FFFFFF;
        }
    </style>
</head>

<%--onload="vailToken();"--%>
<body  style="height: 85%"><%--ontouchstart--%>
<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav"
                style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
            <a class="button button-link button-nav pull-left"
               onclick="skip('<%=baseDicId%>','<%=baseDicType%>','<%=baseDic%>')"><span
                    class="icon icon-left main_color"></span></a>
            <%--<a href="ssBaseDicInfo!findAllByBaseDicTypeToWeb" class="btn_back link_checkJump" jslink="ssBaseDicInfo!findAllByBaseDicTypeToWeb"><span class="ic ic_back"></span></a>--%>
            <h1 class="title main_color">网上办事</h1>
        </header>

        <div class="content">

            <div class="weui-cells__title">请选择对象的类别</div>
            <div class="weui-cells weui-cells_checkbox">

                <s:iterator value="ssAffairObjectList">
                    <input type="hidden" name="iswrite" id="iswrite<s:property value="objindex"/>"
                           value='<s:property value="iswrite"/>'>
                    <input type="hidden" name="iswrite" id="affairid" value='<s:property value="affairid"/>'>

                    <s:if test='(objname == "无分类" || objname=="无") && iswrite == "true"'>
                        <script>
                            <%--location.href='http://localhost:8083/zcWechat/onlineApply!isrelation?affairid=<s:property value="affairid"/>_onlineApply_'+<s:property value="objindex"/>;--%>
                            location.href = weChat.weChatDNSURL + 'web/onlineWrite.jsp?affairid=<s:property value="affairid"/>&objindex=<s:property value="objindex"/>';
                        </script>
                    </s:if>
                    <s:if test='(objname == "无分类" || objname=="无") && iswrite == null'>
                        <script>
                            <%--location.href='http://localhost:8083/zcWechat/onlineApply!isrelation?affairid=<s:property value="affairid"/>_affairMaterials_'+<s:property value="objindex"/>;--%>
                            alert("请关注\"张槎街道行政服务中心\"微信公众号进行资料的上传");
                            window.history.go(-1);
                        </script>
                    </s:if>

                    <label name="label" onclick="demo(<s:property value="objid"/>)" id="label<s:property value="objid"/>"
                           class="weui-cell weui-check__label" for="<s:property value="objid"/>">
                        <div class="weui-cell__hd">
                            <input type="radio" class="weui-check" name="radio" id="<s:property value="objid"/>"
                                   value="<s:property value="objindex"/>">

                                <%--<i class="weui-icon-checked"></i>--%>
                        </div>
                        <div class="weui-cell__bd">
                            <span><s:property value="objname"/></span>
                        </div>
                    </label>

                </s:iterator>

            </div>

            <div class="weui-btn-area">
                <a class="weui-btn weui-btn_primary" href="javascript:" onClick="commit();"
                   id="showTooltips" style="background-color: #911edb">下一步</a>
            </div>

        </div>
    </div>
</div>
</body>
<%--初始化检测--%>
<script type="text/javascript">
    function demo(index) {
        $("label[name='label']").css("background","#fff");
        $("#label" + index).css("background", "yellow");
    }

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

    function skip(baseDicId, baseDicType, baseDic) {
        if (baseDicId == "null" || baseDicType == "null" || baseDic == "null") {
            <%--location.href = "<%=basePath%>web/search.jsp";--%>
            window.location = "<%=basePath%>web/search.jsp";
        } else {
            var href = "web/affairs.jsp?baseDicId=" + baseDicId + "&baseDicType=" + baseDicType + "&baseDic=" + baseDic;
            href = encodeURI(href);
            window.location = "<%=basePath%>" + href;
        }
    }

    function commit() {
        var objindex = $("input[name='radio']:checked").val();
        var objid = $("input[name='radio']:checked").attr('id');
        var iswrite = $("#iswrite" + objindex).val();
        var affairid = $("#affairid").val();
        if (objindex == null) {
            alert("请选择事项对象！");
            return;
        }

        if (iswrite == 'true') {
            <%--<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid='+weChat.APPID+'&redirect_uri='+weChat.weChatDNSURL+'onlineApply!isrelation?affairid=<s:property value="ssAffairGuide.affairid" escape="false" />_onlineApply&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect" class="weui-tabbar__item">--%>
            location.href = weChat.WeChatDNSURL + 'web/onlineWrite.jsp?affairid=' + affairid + '&objindex=' + objindex;

        } else {
            <%--<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid='+weChat.APPID+'&redirect_uri='+weChat.weChatDNSURL+'onlineApply!isrelation?affairid=<s:property value="ssAffairGuide.affairid" escape="false" />_affairMaterials&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect" class="weui-tabbar__item">--%>
            // 	location.href='http://localhost:8083/zcWechat/onlineApply!isrelation?affairid='+affairid+'_affairMaterials_'+objindex;
            alert("请关注\"张槎街道行政服务中心\"微信公众号进行资料的上传");
        }
    }

    /*    function commit() {
            var objindex = $("input[name='radio']:checked").val();
            var objid = $("input[name='radio']:checked").attr('id');
            var iswrite = $("#iswrite" + objindex).val();
            var affairid = $("#affairid").val();
            if (objindex == null) {
                alert("请选择事项对象！");
                return;
            }

            if (iswrite == 'true') {
<%--<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid='+weChat.APPID+'&redirect_uri='+weChat.weChatDNSURL+'onlineApply!isrelation?affairid=<s:property value="ssAffairGuide.affairid" escape="false" />_onlineApply&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect" class="weui-tabbar__item">--%>
            location.href = weChat.weChatDNSURL + 'web/onlineWrite.jsp?affairid=' + affairid + '&objindex=' + objindex;

        } else {
            <%--<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid='+weChat.APPID+'&redirect_uri='+weChat.weChatDNSURL+'onlineApply!isrelation?affairid=<s:property value="ssAffairGuide.affairid" escape="false" />_affairMaterials&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect" class="weui-tabbar__item">--%>
            // 	location.href='http://localhost:8083/zcWechat/onlineApply!isrelation?affairid='+affairid+'_affairMaterials_'+objindex;
            alert("请关注\"张槎街道行政服务中心\"微信公众号进行资料的上传");
        }
    }*/

</script>
</html>
