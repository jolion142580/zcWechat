<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.gdyiko.zcwx.weixinUtils.OAuth" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.WxJSSignUtil" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.TokenHepl" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%
    String token = TokenHepl.getaccessToken().getAccessToken();
    String jsapi_ticket =TokenHepl.jsapi_ticket;
    String url = WxJSSignUtil.getUrl();
    System.out.println("==url=="+url);
    System.out.println("jsapi_ticket=="+jsapi_ticket);
    Map map = WxJSSignUtil.sign(jsapi_ticket, url);
    String code = request.getParameter("code");
    String type = "";
    System.out.println("==code=="+code);
    String openid=(String) session.getAttribute("openid");

    if(code!=null && openid == null){
          OAuth oauth = new OAuth();
          openid=oauth.getOppenid(code);
    }
      System.out.println("----openid---"+openid);
    /*session.setAttribute("openid", "123");
    String openid = (String) session.getAttribute("openid");*/
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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

    <link rel="stylesheet"
          href="http://g.alicdn.com/msui/sm/0.6.2/css/sm.min.css"/>
    <link rel="stylesheet"
          href="http://g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css"/>

    <link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="lib/weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="<%=basePath %>css/style.css"/>

    <%--<link rel="stylesheet" href="<%=basePath %>css/mystyle.css" />--%>
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="js/jquery-weui.js"></script>
    <script type="text/javascript" src="js/config.js"></script>
    <style type="text/css">
        .weui-media-box__desc {
            line-height: 1.5;
            -webkit-line-clamp: 99;
        }

        p {
            margin: 0.2rem;
        }

        .weui-panel {
            margin-left: 6px;
            margin-right: 6px;
            border-radius: 6px;
            border-color: #ccc;
            border-width: thin;
            border-style: solid;

        }
.weui-cell{
		padding: 10px 15px 30px;
}

        .main_color {
            color: #FFFFFF;
        }
    </style>
</head>

<body>
<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav"
                style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
            <a class="button button-link button-nav pull-left" href="javascript:;"
               onclick="javascript:WeixinJSBridge.call('closeWindow');">
                <span class="icon icon-left main_color" style="margin-top: 11px;"></span></a>
            <h1 class="title main_color">留言</h1>
        </header>

        <div class="content">
            <div class="weui-cells__title">用户留言</div>
            <div id="tshf">
            </div>


            <div class="gl_r_m_go" align="center">

            </div>

            <div align="center" style="line-height:25px;width:100%;position: fixed;bottom: 0;z-index: 600;background-color: #efeff4;"><span style='font-size:12px;'>张槎街道行政服务中心授权使用&nbsp;&nbsp;南邮信息联合开发</span></div>
            <div style="height:60px">

            </div>
            <div style="display: none;"><P id="kt"></P>
                <P id="ys"></P>
                <P id="ysl"></P></div>
        </div>


        <div class="weui-tabbar">
            <a class="weui-cell weui-cell_access" id="yanz" style="margin: 0 auto;background-color:transparent">
                <div class="weui-cell__hd"><img src="icon/icon (25).png" style="width:1rem"/></div>
                <div class="weui-cell__bd">
                    <p>我要留言</p>
                </div>
            </a>
        </div>
    </div>
</div>
<script type="text/javascript">
    var kt = 1;
    var ys = 0;
    $(function () {
        authen();
        gotofy(kt, ys);
        $("#yanz").click(function () {
            // window.location.href = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaec78dd064e22ada&redirect_uri=http://zhengqiao.ss.gov.cn/wxfb/WxServlet?tiaoznum=2&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect';
            $.post("${path}ssUserInfo!findById", function (data) {
                if (!data.success) {
                    window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChat.APPID+"&redirect_uri="+weChat.WeChatDNSURL+"relation.jsp?type=tousu&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                }
                else{
                    window.location.href = "submitcomplaint.jsp";
                }
            })


        });
    });

    function authen() {
        $.post("${path}ssUserInfo!findById", function (data) {
            if (!data.success) {
//                        window.location.href="relation.jsp";
                window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChat.APPID+"&redirect_uri="+weChat.WeChatDNSURL+"relation.jsp?type=tousu&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
            }
        })
    }

    function gotoUp() {
        if (parseInt($("#kt").val()) == 1 || parseInt($("#kt").val()) == "") {
            alert("当前为第一页");
            return
        }
        kt = parseInt($("#kt").val()) - 1;
        //alert($("#ys").text());
        ys = 0;
        gotofy(kt, ys);
    }

    function gotoDown() {

        var kt = parseInt($("#kt").val()) + 1;//从kt获取回来的，加一页进入下一页

        var p = parseInt($("#ys").val()); //余数
        var ysl = parseInt($("#ysl").val());//页数
        //alert(p);
        if (ysl < kt) {
            alert("当前为最后一页");
            return
        }
        if (ysl == kt) {
            ys = p;
        }
        gotofy(kt, ys);
    }


    function gotofy(kt, ys) {
        // alert();
        $("#kt").val(kt);//把初始值传到p保存
        $("#ys").val(ys);//把余数参数传到p保存
        $("#tshf").empty();
        //	alert(kt);
        var iy = 0;
        var ih = 5;
        if (kt == 1) {
            iy = 1 * kt - 1;
        } else {
            iy = 5 * kt - 5;
        }
        //if(yushu ==0){
        if (ys == 0) {
            ih = 5 * kt;
        } else {
            ih = 5 * (kt - 1) + ys;
        }


        $.ajax(
            {
                type: "post",
                url: "<%=basePath %>complaint!findLikeByEntitypy",
                data: {},
                //contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    //alert(data);
                    //obj = JSON.parse(data);


                    var p = "";
                    var a = "<div align='center'><span onclick='gotoUp()'>上一页</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    console.log(data);
                    //alert(data.length);
                    var yushu = data.length % 5;
                    //alert(yushu);1
                    var abq = Math.ceil(data.length / 5);
                    $("#ysl").val(abq);
                    if (yushu != 0 && data.length < 5) {
                        ih = yushu;
                    }

                    /* if(5 <= data.length ){


                    }else{} */

                    //var abq = yushu -1;
                    for (k = 0; k < abq; k++) {
                        if (k == (abq - 1)) {
                            a += "<a  onclick='gotofy(kt=this.text," + yushu + ")'>" + (k + 1) + "</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                            //alert(yushu);
                            $("#ys").val(yushu);
                        } else {
                            a += "<a onclick='gotofy(kt=this.text,0)'>" + (k + 1) + "</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                        }

                        //alert(a);
                    }
                    a += "<span onclick='gotoDown()' >下一页</span></div>";
                    for (i = iy; i < ih; i++) {
                        //alert("111");

                        for (j = 0; j < data[i].ssUserInfo.name.length; j++) {
                            var na = data[i].ssUserInfo.name[0];

                        }
                        if (na == "匿") {
                            na = "匿名用户";
                        } else if (na == "" || na == null) {
                            na = "匿名用户";
                        } else {
                            na = na + "**";
                        }
//								    var name =data[i].name;
//								    var name =(data[i].name.substring(1,0))+"**";
                        p += "<div class='weui-panel weui-panel_access' >";
                        p += "<div class='weui-panel__bd' >";
                        p += "<div class='weui-media-box weui-media-box_text' >";
                        p += "<p class='weui-media-box__desc'>" + na + "  于   " + data[i].complaintTime + " 发表：</p>";
                        p += "<p class='weui-media-box__desc'>" + data[i].complaint_Content + "</p>";
                        p += "<p class='weui-media-box__desc' style='color:#222'><span style='color:#1dabec;'>回复</span>" + data[i].complaint_Reply + "</p>";
                        p += "<div class='weui-textarea-counter'>" + data[i].complaintReplyTime + "</div>";
                        p += "</div>";
                        p += "</div>";
                        p += "</div>";
                        na = "";
                    }
                    p += a;
                    $("#tshf").append(p);


                },
                error: function () {
//                    alert("加载信息失败，请重试！");
                    return;
                }
            });
    }
</script>
</body>
</html>
