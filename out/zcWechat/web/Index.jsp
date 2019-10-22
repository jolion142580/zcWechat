<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%
    String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path +"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <title>首页 - 张槎街道行政服务中心</title>
    <link rel="stylesheet" href="content/common.css">
    <link rel="icon" href="<%=basePath%>zcWechatImage/title.ico">
    <script type="text/javascript" src="ctrl/jquery-1.10.1.min.js"></script>
    <%--<script type="text/javascript" src="scripts/keyboard.js"></script>--%>
    <%--<link rel="stylesheet" href="content/keyboard.css" type="text/css">--%>
    <style type="text/css">
        ::-webkit-scrollbar-track {
            background-color: #ffffff;
            -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0);
        }

        ::-webkit-scrollbar {
            width: 8px;
        }

        ::-webkit-scrollbar-thumb {
            background-color: #d6d6d6;
            -webkit-box-shadow: inset 1px 1px 0 rgba(0, 0, 0, .1);
        }

        ::-webkit-scrollbar-thumb:hover {
            background-color: #bcbcbc;
        }

        ::-webkit-scrollbar-thumb:active {
            background-color: #a8a8a8;
        }

        ::-webkit-scrollbar-track-piece {
            background-color: rgba(0, 0, 0, 0.1);
        }

        .screensaver {
            height: 100%;
            width: 100%;
            position: relative;
            background-image: url(images/screensaver.jpg);
            display: block;
            background-repeat: no-repeat;
            background-size: cover;
            background-position: center center;
            -ms-behavior: url(content/backgroundsize.min.htc);
            behavior: url(content/backgroundsize.min.htc);
            overflow: hidden
        }

        .mydiv {
            width: 300px;
            height: auto;
            top: 33%;
            left: 35%;
            display: block;
            position: fixed !important; /* FF IE7*/
            position: absolute; /*IE6*/
            _top: expression(eval(document.compatMode &&
				 document.compatMode == 'CSS1Compat')? 
				documentElement.scrollTop+  (document.documentElement.clientHeight-this.offsetHeight
		)/2: /*IE6*/ 
				document.body.scrollTop+  (document.body.clientHeight-  this.clientHeight
		)/2 ); /*IE5 IE5.5*/
        }

        .gh_div {
            height: auto;
            border-radius: 5px;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            background: #fff;
            border: 3px solid #F93;
        }

        .gh_text {
            line-height: 50px;
            padding: 0 10px 0 10px;
            border-bottom: 1px solid #ccc;
            clear: both;
        }

        .gh_bdiv {
            height: 80px;
        }

        .textf11 {
            font-size: 20px;
            color: #000;
            text-align: right;
        }

        .gh_button {
            float: left;
            margin: 25px 20px 10px 0;
            width: 85px;
            height: 30px;
            line-height: 30px;
            text-align: center;
            font-size: 18px;
            list-style: none;
            color: #fff;
            border-radius: 5px;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            background: #fb510d;
            border: none;
        }

        .searchInput {
            width: 250px;
            line-height: 36px;
            background: #CCD7DB;
            border-radius: 18px;
            overflow: hidden
        }

        .searchInput.focus .ic_clearinput {
            z-index: 1;
            display: block;
            opacity: .6;
            filter: alpha(opacity=60)
        }

        .searchInput input[type=text] {
            *left: 0;
            *top: 0;
            *position: absolute;
            margin-left: 40px;

            height: 36px;
            line-height: 36px;
            border: 0;
            background: transparent;

            font-size: 14px
        }

        .searchInput .fullCover {
            z-index: 5
        }
    </style>
    <!-- 二维码添加 -->
    <style type="text/css">
        .tip {
            /*float: left;*/
            /*text-align: center;*/
            /*display: inline-block;*/
            color: red;
        }

        .numerical {
            /*float: left;*/
            /*height: 100%;*/
        }

        .explain {
            /*text-align: left;*/
        }

        .imgnews-item {
            position: relative;
        }

        .imgnews-item span {
            background-color: #000;
            opacity: 0.4;
            filter: alpha(opacity=40);
            position: absolute;
            left: 0;
            bottom: 0;
            width: 100%;
            height: 20px;
            line-height: 20px;
            overflow: hidden;
            font-size: 14px;
            text-align: center;
        }

        .imgnews-item em {
            position: absolute;
            left: 0;
            bottom: 0;
            width: 100%;
            height: 20px;
            line-height: 20px;
            overflow: hidden;
            font-size: 14px;
            text-align: center;
            color: #FFF;
            font-style: normal;

        }

    </style>
    <!--覆盖样式  -->
    <style type="text/css">
        .index-box {
            padding: 0px;
        }
    </style>
    <script src="scripts/jquery.min.js" type="text/javascript"></script>
    <script src="scripts/hammer.min.js" type="text/javascript"></script>
    <script src="scripts/base.js" type="text/javascript"></script>
    <script src="js/layer/layer.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            $.post('QRCode!createUUID', function (data) {
                $("#imgId").attr("src", "QRCode!createQRCode");
                settime();
            });
        });

        function settime() {
            listen();
            t = setTimeout(function () {
                settime();
            }, 1000);
        }


        function listen() {
            $.get('QRCode!listenUUID', function (data) {
                if (data == "true") {
                    clearTimeout(t);
                    window.location = "<%=basePath%>web/mainPage.jsp";
                } else {
                    // listen();
                }
            });
        }

        /*登录测试*/
        function demo() {
            //添加随机数避免缓存
            var random_number = Math.floor(Math.random() * ( 1000 + 1));
            $.get('web!demoLogin?'+random_number);
        }
    </script>
</head>

<body>

<div class="screensaver">
    <div class="header">
        <div class="main">
            <%--<a href="#"> <img src="images/logo.png" alt="" width="122%"> </a>--%>
            <img src="images/logo.png" width="100%">
        </div>
    </div>

    <div class="content">
        <div class="main">
            <div class="index-box">
                <div class="index-btn clear">
                    <ul>
                        <li class="left imgnews-item">
                            <img src="zcWechatImage/zcWechart.5.jpg" >
                            <span></span>
                            <em>微信公众号</em>

                        </li>
                    </ul>
                    <ul>
                        <li class="left imgnews-item">
                            <img id="imgId" width="258px" height="258px" >
                            <span></span>
                            <em>扫码登录</em>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="tip">
            <div style="text-align: center">
                <%--<button type="button" onclick="demo()">测试用户</button>--%>
            </div>
            <div style="text-align: center"><h2>操作步骤说明</h2><br/></div>
            <div style="margin-left: 40%;font-size: 16px;">

                <div style="height: 36px;">
                    <%--<span class="numerical">一、</span>--%>
                    <p class="explain">一、公众号进行认证绑定（已绑定请忽略此步骤）；</p>
                </div>
                <div style="height: 36px;">
                    <%--<span class="numerical">二、</span>--%>
                    <p class="explain">二、微信扫二维码登录；</p>
                </div>
                <div style="height: 36px;">
                    <%--<span class="numerical">三、</span>--%>
                    <p class="explain">三、【网上办事】选择事项填写；</p>
                </div>
                <div style="height: 36px;">
                    <%--<span class="numerical">四、</span>--%>
                    <p class="explain">四、微信扫码上传资料(或者到【办事记录】界面上传)。</p><%----%>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>