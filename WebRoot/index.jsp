<<<<<<< HEAD
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html class="pixel-ratio-1"><head>
    <title>张槎街道行政服务中心</title>
    <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">

<meta name="description" content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.
">

<link rel="stylesheet" href="lib/weui.min.css" type="text/css"></link>
<link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css"></link>
<!-- <link rel="stylesheet" href="lib/demos.css" type="text/css"></link> -->

  </head>

  <body ontouchstart="">

<%--正在开发中....--%>

  

</body></html>
=======
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<html class="pixel-ratio-1">
<head>
    <title>张槎街道行政服务中心</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">


    <link rel="stylesheet" href="lib/weui.min.css" type="text/css"></link>
    <link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css"></link>

    <link href="css/newIndex.css" rel="stylesheet" type="text/css"/>
    <style>
        .banner img {
            width: 100%;
        }
    </style>
    <script type="text/javascript" src="js/newiscroll.js"></script>
    <script type="text/javascript">
        var myScroll;

        function loaded() {
            myScroll = new iScroll('wrapper', {
                snap: true,
                momentum: false,
                hScrollbar: false,
                onScrollEnd: function () {
                    document.querySelector('#indicator > li.active').className = '';
                    document.querySelector('#indicator > li:nth-child(' + (this.currPageX + 1) + ')').className = 'active';
                }
            });
        }

        document.addEventListener('DOMContentLoaded', loaded, false);
    </script>
</head>


<body>


<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">

</script>

<script type="text/javascript">

    $(function () {

        $.post("ssUserInfoNoLogin!getUser", function (data) {

            if (data == null) {
                $("#log").css("display", "block");
            } else {
                $("#log").css("display", "none");
            }
            console.log(data);
        });
    });

</script>


<div class="banner">

    <div id="wrapper">
        <div id="scroller">
            <ul id="thelist">
                <li><a href="javascript:void(0)"><img src="images\weixin\wangshangyuyue.gif"/></a></li>

            </ul>
        </div>
    </div>
    <%--
        <div id="nav">
            <ul id="indicator">
                <li class="active" ></li>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </div>--%>

</div>

<div id="log" style="background-color: #eee;display: block">
    <a style="margin-left: 1em">切换用户</a>
</div>

<ul class="mainmenu">
    <li><a href="ssBaseDicInfo!findAllByBaseDicType"><b><img src="images\weixin\zhinan.png"/></b><span>办事点我</span></a>
    </li>
    <%--<li><a href="YuYues!yuYues" ><b><img src="images\weixin\newyuyue1.png" /></b><span>排队预约</span></a></li>--%>
    <li><a href="newyuyueAgree.jsp"><b><img src="images\weixin\newyuyue1.png"/></b><span>排队预约</span></a></li>
    <li><a href="zcQueueInfo.jsp"><b><img src="images\weixin\queue.png"/></b><span>排队信息</span></a></li>
    <li><a href="affairProgress.jsp"><b><img src="images\weixin\genzong.png"/></b><span>办件跟踪</span></a></li>
    <li><a href="complaint!getComplaintPage"><b><img src="images\weixin\fg.png"/></b><span>市民留言</span></a></li>
    <li><a href="ssUserInfo"><b><img src="images\weixin\grxx.png"/></b><span>个人信息</span></a></li>
</ul>


<script type="text/javascript">
    var count = document.getElementById("thelist").getElementsByTagName("img").length;

    var count2 = document.getElementsByClassName("menuimg").length;
    for (i = 0; i < count; i++) {
        document.getElementById("thelist").getElementsByTagName("img").item(i).style.cssText = " width:" + document.body.clientWidth + "px";
    }
    document.getElementById("scroller").style.cssText = " width:" + document.body.clientWidth * count + "px";

    setInterval(function () {
        myScroll.scrollToPage('next', 0, 400, count);
    }, 3500);

    window.onresize = function () {
        for (i = 0; i < count; i++) {
            document.getElementById("thelist").getElementsByTagName("img").item(i).style.cssText = " width:" + document.body.clientWidth + "px";
        }
        document.getElementById("scroller").style.cssText = " width:" + document.body.clientWidth * count + "px";
    }
</script>

<div class="copyright"><br/><br/>张槎街道行政服务中心授权使用&nbsp;&nbsp;南邮信息联合开发</div>

</body>
</html>
>>>>>>> withoutWechatInterface
