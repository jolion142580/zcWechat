<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.WxJSSignUtil" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.OAuth" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.TokenHepl" %>

<%
    String path = request.getContextPath();
    //获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<%--<%
    String code = request.getParameter("code");
    System.out.println("code:::::" + code);
    String openid = (String) session.getAttribute("openid");

    if (code != null && openid == null) {
        OAuth oauth = new OAuth();
        openid = oauth.getOppenid(code);
    }
    String token = TokenHepl.getaccessToken().getAccessToken();
    String jsapi_ticket = TokenHepl.jsapi_ticket;
    String url = WxJSSignUtil.getUrl();
    System.out.println("==url==" + url);
    System.out.println("jsapi_ticket==" + jsapi_ticket);
    Map map = WxJSSignUtil.sign(jsapi_ticket, url);
%>--%>

<%
    String token = TokenHepl.getaccessToken().getAccessToken();
    String jsapi_ticket = TokenHepl.jsapi_ticket;
    String url = WxJSSignUtil.getUrl();
    System.out.println("==url==" + url);
    System.out.println("jsapi_ticket==" + jsapi_ticket);
    Map map = WxJSSignUtil.sign(jsapi_ticket, url);

    String code = request.getParameter("code");
    String type = request.getParameter("type");

    System.out.println("code:::::" + code + "----type-----" + type);
    String openid = (String) session.getAttribute("openid");

    if (code != null && openid == null) {
        OAuth oauth = new OAuth();
        openid = oauth.getOppenid(code);
    }
    System.out.println("----openid---" + openid);
	/* String openid="1111";
	String code="22222";
	String type="33333";*/
    //System.out.println("----openid---"+openid);
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
            color: #FFFFFF;
        }

        .weui-vcode-btn {
            margin: 0px;
        }

        .weui-search-bar__box .weui-icon-search {
            line-height: 44px;
        }

        .weui-search-bar__form {
            background-color: #FFF;
        }

        .weui-search-bar__form:after {
            border-radius: 0px;
        }
    </style>
    <script type="text/javascript" src="js/config.js"></script>
</head>
<body ontouchstart>
<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav"
                style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
            <!-- <img src="images/bar_nav_left_brown.png" style="position: absolute;left: -0.5rem;height: 2.2rem;" /> -->
            <a class="button button-link button-nav pull-left" href="javascript:WeixinJSBridge.call('closeWindow');">
                <div class="icon icon-left main_color"></div>
            </a>
            <h1 class="title main_color">办件跟踪</h1></header>
        <!-- <div class="header-bar" style="background-color: #f27b03"></div> -->
        <div class="content">
            <div style="margin:10px;background-color:#FFF;padding:10px;border-radius: 6px;height: 98%;">
                <div class="weui-cell">
                    <div class="weui-cell__hd">
                        <label class="weui-label">办事人姓名：</label>
                    </div>
                    <div class="weui-cell__bd">

                        <input name="name" class="weui-input" readonly
                               id="name" type="text" placeholder="请输入办事人姓名">
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="weui-cells__title" style="font-size:18px">受理编号</div>
                <div class="weui-cells weui-cells_form">
                    <div class="weui-cell weui-cell_vcode" style="padding-left:0px;">
                        <div class="weui-cell__bd">
                            <div class="weui-search-bar2" id="searchBar">
                                <form id="accountform" action="" method="POST" class="weui-search-bar__form"
                                      style="line-height:44px;">
                                    <div class="weui-search-bar__box">
                                        <i class="weui-icon-search"></i>
                                        <input type="search" class="weui-search-bar__input" name="currCode"
                                               id="currCode" placeholder="输入查询码" required="">
                                        <a href="javascript:" class="weui-icon-clear" id="searchClear"></a>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="weui-cell__ft" style="background-color:#f27b03">
                            <button onClick="commit();return false;" class="weui-vcode-btn"
                                    style="color:#FFF;width: 3rem;">查询
                            </button>
                        </div>
                        <div class="weui-cell__ft" style="background-color:#61b3cb">
                            <button onClick="wxcloseWindow();return false;" class="weui-vcode-btn"
                                    style="font-size:0px;line-height:0px;width: 3rem;">
                                <%--<img src="icon/icon%20(24).png" style="line-height:44px; width:1rem"/>--%>
                                <img src="images/weixin/saoyisao.png" style="line-height:44px; width:1rem"/>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="weui-cells__title">提示：请使用受理回执中的查询码进行查询，或点击查询按钮旁的扫码查询功能扫描受理回执中的二维码进行查询。</div>
                <div class=" foot fixPosition width100 bottomPosition iff">
                    <div align="center" style="line-height:25px;">
                        <span style="font-size:12px;position: fixed;margin: 0 auto;left: 0px;right: 0px;bottom: 0;">张槎街道行政服务中心授权使用&nbsp;&nbsp;南邮信息联合开发</span>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="lib/jquery-2.1.4.js"></script>
<script src="lib/fastclick.js"></script>
<script>
    $(function () {
        FastClick.attach(document.body);
    });
</script>
<script src="js/jquery-weui.js"></script>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
    function commit() {
        //$("#accountform").submit();
        //$("#currCode").val();
        var currCode = $("#currCode").val();
        var name = $("#name").val();
        if (currCode == null || currCode == undefined || currCode == "") {
            alert("请输入受理编号!");
            return;
        }
        location.href = "affairProgressResult.jsp?currCode=" + currCode + "&username=" + encodeURI(encodeURI(name));
    }

    $(function () {
        $("#currCode").focus();
        findUserName();
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: weChat.APPID, // 必填，公众号的唯一标识
            timestamp: '<%=map.get("timestamp") %>', // 必填，生成签名的时间戳
            nonceStr: '<%=map.get("nonceStr") %>', // 必填，生成签名的随机串
            signature: '<%=map.get("signature") %>',// 必填，签名，见附录1
            jsApiList: ['scanQRCode']
            // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
    });

    function findUserName() {
        $.post("<%=basePath%>affairProgress!findUserName", function (res) {
//            alert(eval("("+res+")").userName);

//            alert("sfasf"+res.userName);
            $("#name").val(eval("(" + res + ")").userName);
        })
    }

    function wxcloseWindow() {
        wx.scanQRCode({
            needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
            scanType: ["qrCode", "barCode"], // 可以指定扫二维码还是一维码，默认二者都有
            success: function (res) {
                var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
                result= result.substring(result.lastIndexOf("=") + 1);

                var rs = "";
                rs = result;
              /*  if (result.indexOf(",") != -1) {
                    var rs1 = result.split(",");
                    var rs = rs1[1];
                }*/
                location.href = "affairProgressResult.jsp?currCode=" + rs + "&username=" + encodeURI(encodeURI($("#name").val()));//rs[1];
            }
        });
    }

    $("#searchClear").click(function () {
        $("#currCode").val("");
    });
</script>

</body>
</html>
