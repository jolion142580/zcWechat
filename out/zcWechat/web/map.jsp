<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.TokenHepl" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.WxJSSignUtil" %>
<%@ page import="java.util.Map" %>

<%
    String token = TokenHepl.getaccessToken().getAccessToken();
    String jsapi_ticket = TokenHepl.jsapi_ticket;
    String url = WxJSSignUtil.getUrl();
    System.out.println("==url==" + url);
    System.out.println("jsapi_ticket==" + jsapi_ticket);
    Map map = WxJSSignUtil.sign(jsapi_ticket, url);
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
    <link rel="stylesheet" href="css/weui.min.css"/>
    <link rel="stylesheet" href="css/jquery-weui.css"/>
    <link rel="stylesheet" href="css/guide-style.css"/>
    <%--<link rel="stylesheet" href="css/">--%>
    <script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp&key=B6YBZ-D223U-H5WV2-B5NCT-GHR3S-2PBUF"></script>
    <script type="text/javascript" src="lib/weui.js"></script>
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
    <%--<script src="scripts/window.js" type="text/javascript"></script>--%>
    <script type="text/javascript" src="js/config.js"></script>
    <%--<script src="scripts/window.js" type="text/javascript"></script>--%>
    <script type="text/javascript">
        var init = function () {
            var latitude, longitude, name, address;
            latitude = 23.030650, longitude = 113.057040, name = '张槎街道行政服务中心', address = '张槎一路115号华南创谷11座';
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

            /* var label = new qq.maps.Label({
                 position: center,
                 map: map,
                 content: '<div style="margin: -1px; padding: 1px;"><div style="text-align:center;white-space:' +
                 'nowrap;margin:10px;">点击进行导航</div></div>'
             });*/

            qq.maps.event.addListener(marker, 'click', function () {
                console.log(latitude);
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
        #container {
            width: 100%;
            height: 50%
        }

        .weui-article {

            margin: 10px 0 -10px 20px;
            border-radius: 10px;
            padding: 0px;

        }

        .weui-article span {
            position: relative;
            top: 3px;
            left: 8px;
            height: 40px;
            width: 40px;
            color: #555555

        }

        .weui-article a {

            color: #f27c7c;

        }

        .img {
            position: relative;
            top: 9px;
            left: 0px;
            height: 25px;
            width: 25px;
        }

        .main_color {
            color: #FFFFFF;
        }

        .a-color {
            font-size: 200px;
            color: #FFFFFF !important;
        }

    </style>
    <%--<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>--%>
    <%--<script type="text/javascript" src="js/jweixin-1.0.0.js"></script>--%>
    <script type="text/javascript">
        $(function () {
            iniweixin();
        });

        function iniweixin() {
            wx.config({
                debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: weChat.APPID, // 必填，公众号的唯一标识
                timestamp: '<%=map.get("timestamp")%>', // 必填，生成签名的时间戳
                nonceStr: '<%=map.get("nonceStr")%>', // 必填，生成签名的随机串
                signature: '<%=map.get("signature")%>',// 必填，签名，见附录1
                jsApiList: ['openLocation', 'getLocation']
                // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
            });
        }
    </script>

</head>
<body onload="init();">
<div>
    <header class="bar bar-nav"
            style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
        <%--  <a class="button button-link button-nav pull-left" href="javascript:;"
             onclick="javascript:WeixinJSBridge.call('closeWindow');"><span class="icon icon-left main_color"></span></a>--%>
        <h1 class="title main_color">服务热线</h1>
    </header>
</div>
<article class="weui-article">
    <section>
        <div style="margin-top:40px; line-height: 100px; font-size: 16px;" class="tes">
            <div><img class='img' src="images/weixin/phone.png"/><span>电话：82590191、82590192</span></div>

        </div>
        <div style="clear:both;"></div>
    </section>
</article>
<div id="container"></div>
<%--
<div class=" foot fixPosition width100 bottomPosition iff">
    <div  style="line-height:25px;;"align="center" ><span style="font-size:12px;  position:fixed;bottom:0;margin: 0 auto; left: 0px;right: 0px;">
   		 张槎街道行政服务中心授权使用&nbsp;&nbsp;南邮信息联合开发</span>
    </div>
</div>
--%>
</body>
</html>
