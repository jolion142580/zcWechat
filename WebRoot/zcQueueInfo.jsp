<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<%--<%@page import="utils.CustomMessage" %>
<%@ page import="utils.GetOpenid" %>
<%@ page import="listener.CompareQueue" %>
<%@ page import="listener.TimToTicket" %>
<%@ page import="utils.WxJSSignUtil" %>--%>

<%
    String path = request.getContextPath();

    //获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String nowDate = sdf.format(new Date());
    String code = request.getParameter("code");
    System.out.println("111code---" + code);
    String street = request.getParameter("street");

    String id = "";

//    String jsapi_ticket = TimToTicket.jsapi_ticket;

    String param = request.getQueryString();
    String url = request.getScheme() + "://" + request.getServerName()
            + request.getRequestURI();
    if (param != null) {
        url = url + "?" + request.getQueryString();
    }

%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>张槎街道行政服务中心</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1"/>
    <link rel="shortcut icon" href="/favicon.ico"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="stylesheet" href="css/sm-extend.min.css" type="text/css"></link>
    <link rel="stylesheet" href="css/sm.min.css" type="text/css"></link>
    <link rel="stylesheet" href="lib/weui.min.css"/>
    <link rel="stylesheet" href="css/jquery-weui.css"/>
    <link rel="stylesheet" href="css/guide-style.css"/>

    <%--<script type="text/javascript" src="bootstrap3/js/jquery-1.8.0.min.js"></script>--%>
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
    <%--<script type="text/javascript" src="js/weui.js"></script>--%>
    <script type="text/javascript" src="lib/weui.js"></script>
    <script>

        var init = function () {
            var latitude, longitude, name, address;
            var street = $("#street").val();
            var center = new qq.maps.LatLng(latitude, longitude);
            var map = new qq.maps.Map(document.getElementById('container'), {
                center: center,
                zoom: 16
            });
            //创建marker
            var marker = new qq.maps.Marker({
                position: center,
                map: map,
                //设置Marker被添加到Map上时的动画效果为反复弹跳
                animation: qq.maps.MarkerAnimation.BOUNCE
            });

            var label = new qq.maps.Label({
                position: center,
                map: map,
                content: '<div style="margin: -1px; padding: 1px;"><div style="text-align:center;white-space:' +
                'nowrap;margin:10px;">点击进行导航</div></div>'
            });

            qq.maps.event.addListener(marker, 'click', function () {
                wx.openLocation({
                    latitude: latitude,
                    longitude: longitude,
                    name: name,
                    address: address,
                    scale: 14,
                    infoUrl: 'http://weixin.qq.com'
                });

            });

        };


    </script>
    <style type="text/css">
        .main_color {
            color: #e25355;
        }

        .main_color2 {
            color: #FFFFFF;
        }

        .weui-article {
            padding: 20px 11px;
        }

        .weui-cell__ft {
            text-align: center;
        }

        .weui-cell__ft .board {
            background-color: #efefef;
            border-radius: 10px;
            padding: 10px;
        }

        .number {
            font-size: 3rem;
        }

        .call_cell {
            color: #727272;
            font-size: 14px;
        }

        .call_cell img {
            height: 18px;
            vertical-align: middle;
            margin-right: 15px;
        }

        .call_title span {
            vertical-align: middle;
        }

        .call_title {
            line-height: 35px;
            color: #3e3a39;
            font-weight: bold;
        }

        .call_cell_item {
            margin-left: 29px;
            margin-bottom: 5px;
        }

        .call_tips {
            background-color: #f39801;
            border-radius: 5px;
            color: #FFF;
            font-size: 12px;
            padding: 5px;
            margin-left: 2rem;
        }

        /* #container {
             width: 100%;
             height: 50%
         }*/
    </style>
</head>
<body onload="displayDate();">


<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav"
                style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
            <a class="button button-link button-nav pull-left" href="javascript:;"
               onclick="javascript:history.back(-1);">
                <span class="icon icon-left main_color2"></span></a>
            <h1 class="title main_color2">现场办理情况</h1>
        </header>
        <%-- <div class="header-bar" style="background-color: #e25355">
            <div>PERSONAL INFORMATION REGISTRATION</div>
         </div>--%>
        <div class="content">

            <div class="weui-cells" id="content"></div>
            <%--  <div id="container"></div>--%>
            <div class=" foot fixPosition width100 bottomPosition iff">
                <div align="center" style="line-height:25px;">
                    <span style='font-size:12px;'>张槎街道行政服务中心授权使用&nbsp;&nbsp;南邮信息联合开发</span>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
<script type="text/javascript">

    $(function () {
        setInterval(displayDate, 30 * 1000);

    });

    function displayDate() {

//        var checkValue = $("#street").val();

        $.post("${path}queueinfo!send", function (data) {

            if (null != data && data.length > 0) {
                $("#content").empty();
                var json = eval(data); //数组
                if (json != null) {
                    $.each(json, function (index, item) {
                        //循环获取数据
                        var serialName = json[index].name;
                        var wait = json[index].wait;
                        var lastestnum = json[index].lastestnum;
                        if (serialName == '综合业务' || serialName == '收费发证(补交材料)') {
                            var html = "<div class='weui-cell'><div class='weui-cell__bd call_cell'><div class='call_title'><img src=\"images/icon (26).png\"/><span >" + serialName + "</span>";
                            html += "</div><div class=\"call_cell_item\"><span>在办编号：</span><span class=\"main_color\"></span><span>" + lastestnum + "</span></div>" +
                                "<div class=\"call_cell_item\"></div>" +
                                "</div><div class=\"weui-cell__ft\"><div class=\"board\"><div>等待人数</div><div class=\"main_color number\">" + wait + "</div></div></div>";
                            $("#content").append(html);
                        }
                    });
                }
            }
//            else{
//                var html ='<p>暂无数据</p>'
//                $("#content").append(html);
//            }


        });

    }
</script>
