<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@page import="java.util.*" %>
<%@page import="com.gdyiko.zcwx.weixinUtils.OAuth" %>


<%
    String code = request.getParameter("code");
    OAuth oauth = new OAuth();
    String openid = oauth.getOppenid(code);
    //String openid="ovh5dxGh-9EXBe-fFYD5IU1fSW4k";
    //System.out.println("2222---"+openid);

%>
<!DOCTYPE html>
<html>
<head>
    <meta name="generator"
          content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39"/>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>张槎行政服务中心</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1"/>
    <link rel="shortcut icon" href="/favicon.ico"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="stylesheet" href="css/sm-extend.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/sm.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="lib/weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/guide-style.css" type="text/css"/>
    <style type="text/css">
        .main_color {
            color: #911edb;
        }

        .weui-navbar + .weui-tab__bd {
            padding-top: 6rem;
        }

        p {
            margin: 0;
        }

        .content {
            background-color: #FFF;
        }

        .re_num {
            font-size: 2rem;
            color: #8fc320;
        }

        .weui-bar__item--on .re_num {
            color: #f39801;
        }

        .weui-navbar__item.weui-bar__item--on {
            background-image: url(icon/selected.png);
            background-repeat: no-repeat;
            background-size: 100%;
            background-position-y: 100%;
            background-color: #FFF;
        }

        .weui-navbar__item {
            background-color: #FFF;
        }

        .weui-navbar__item:after {
            border-right: 0px;
        }

        .placeholder {
            color: #888;
        }

        .rtext {
            font-size: 14px;
            color: #555;
        }
        .main_color2{
            color:#ffffff;
        }

        .weui-cells {
            margin-top: 0px;
        }
    </style>
</head>
<body>
<div class="page-group">
    <div class="page page-current">
        <%--<header class="bar bar-nav"
                style="background-image:url(images/bar_nav_bg_purple.png);background-size: contain;background-repeat: repeat-x;background-color:#FFF">
            <img src="images/bar_nav_left_purple.png" style="position: absolute;left: -0.5rem;height: 2.2rem;"/>
            <a class="button button-link button-nav pull-left" href="javascript:;"
               onclick="javascript:history.back(-1);">
                <span class="icon icon-left main_color"></span>
            </a>
            <h1 class="title" style="color:#911edb">我的预约记录</h1></header>--%>
            <header class="bar bar-nav"
                    style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
                <a class="button button-link button-nav pull-left" href="javascript:;"
                   onclick="javascript:history.back(-1);">
                    <span class="icon icon-left main_color2" ></span></a>
                <h1 class="title main_color2">我的预约记录</h1>
            </header>

        <div class="content">
            <div class="weui-flex" style="text-align:center;background-color:#efefef;margin: 0.5rem;padding: 0.5rem;">
                <div class="weui-flex__item">
                    <div class="placeholder">预约时段</div>
                </div>
                <div class="weui-flex__item">
                    <div class="placeholder">预约编号</div>
                </div>
            </div>
            <div id="newMessage">




            </div>
        </div>
    </div>
</div>
<script src="lib/jquery-2.1.4.js"></script>
<script src="js/jquery-weui.js"></script>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
    $(function () {

        getList();

    });

    //地址
    function getList() {
        $.ajax({
            url: '${path}YuYues!userYuYuesJson',
            type: 'POST',
            dataType: "JSON",
            data: {
                //code:code
                openid: '<%=openid%>'
            },
            success: function (r) {
                console.log(r.old);
                console.log(r.future);
                layoutPage(r);

            },
            error: function (e) {

            }
        });
    }

    function layoutPage(r) {
//        var old = r.data;
        var future = r.data;
        //加载往期预约
        /*$("#old").text(old.length);
        $("#new").text(future.length);
        if (old.length > 0) {
            for (var i in old) {
                var time = old[i].ydate.substring(0, 10) + " " + old[i].ystime + "-" + old[i].yetime;
                $("#oldMessage").append(
                    "<div class='weui-cell'><div class='weui-cell__bd'>" +
                    "<a class='weui-flex' style='text-align:center;' href='YuYues!singleYuYue?no=" + old[i].no + "&idcard=" + old[i].idcard + "&type=' >" +
                    "<div class='weui-flex__item'>" +
                    "<div class='rtext'>" + time + "</div>" +
                    "</div><div class='weui-flex__item'>" +
                    "<div class='rtext'>" + old[i].no + "</div>" +
                    "</div></a></div></div>");
            }
            ;
        } else {
            $("#oldMessage").append(
                "<div class='weui-cell'><div class='weui-cell__bd'><div class='weui-flex' style='text-align:center;'><div class='weui-flex__item'>没有相关预约信息" +
                "</div></div></div></div>");
        }*/



        //加载今天以后的预约
        if (future.length > 0) {
            for (/*var j in future*/var j =future.length-1;j>=0;j--) {
                var time = future[j].v_time;
                $("#newMessage").append(
                    "<div class='weui-cell'><div class='weui-cell__bd'>" +
                    "<a class='weui-flex' style='text-align:center;' href='YuYues!singleYuYue?no=" + future[j].v_appointment_id+ "&idcard="+future[j].people_cardid+"&type=remove' >" +
                    "<div class='weui-flex__item'>" +
                    "<div class='rtext'>" + time + "</div>" +
                    "</div><div class='weui-flex__item'>" +
                    "<div class='rtext'>" + future[j].v_appointment_id + "</div>" +
                    "</div></a></div></div>");
            }
        } else {
            $("#newMessage").append(
                "<div class='weui-cell'><div class='weui-cell__bd'><div class='weui-flex' style='text-align:center;'><div class='weui-flex__item'>没有相关预约信息" +
                "</div></div></div></div>");
        }

    };

</script>
</body>
</html>
