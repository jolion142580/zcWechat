<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport"
          content="width=device-width,height=device-height, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <title>张槎街道行政服务中心</title>
    <link rel="stylesheet" href="css/sm-extend.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/sm.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="lib/weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/guide-style.css" type="text/css"/>
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="js/config.js"></script>
    <style type="text/css">
        .main_color {
            color: #ffffff;
        }

        .weui-cell__bd .number {
            color: #c40e24;
        }
    </style>
</head>

<body>
<div class="page-group">
    <div class="page page-current">
        <%--<header class="bar bar-nav"
            style="background-image:url(images/bar_nav_bg_purple.png);background-size: contain;background-repeat: repeat-x;background-color:#FFF ">
        <img src="images/bar_nav_left_purple.png"
            style="position: absolute; left: -0.5rem;height: 2.2rem;" /> <a
            class="button button-link button-nav pull-left" href="javascript:;"
            onclick="javascript:history.back(-1);"> <span
            class="icon icon-left" style=" top:10px;color:#911edb "></span> </a>
        <h1 class="title" style="color:#911edb">预约须知</h1>
        </header>
        <div class="header-bar" style="background-color: #911edb">
            <div>PERSONAL INFORMATION REGISTRATION</div>
        </div>--%>
        <header class="bar bar-nav"
                style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
            <a class="button button-link button-nav pull-left" href="javascript:;"
               onclick="javascript:history.back(-1);">
                <span class="icon icon-left main_color" style="margin-top: 11px;"></span></a>
            <h1 class="title main_color">绑定须知</h1>
        </header>
        <div class="content">

            <form id="accountform" action="" method="post"
                  onsubmit="return checkYanzhengma()"
                  style="padding:15px 15px 30px 15px; margin:15px; border-radius:10px; background:#FFF;"
                  class="fonts">
                <fieldset>
                    <legend>
                        <span>注意事项</span>
                    </legend>

                    <div class="form-group">
                        <p>
                        <h4>一、总则</h4>
                        1.1
                        用户在认证绑定之前，应当仔细阅读本协议，并同意遵守本协议后方可成为认证绑定。一旦绑定成功，则用户与张槎街道行政服务中心自动形成协议关系，用户应当受本协议的约束。用户在使用特殊的服务或产品时，应当同意接受相关协议后方能使用。<br>
                        </p>

                        <p>
                        <h4>二、隐私保护</h4>
                        2.1 本站不对外公开或向第三方提供单个用户的注册资料及用户在使用网络服务时存储在本站的非公开内容，但下列情况除外：<br>
                        (1)事先获得用户的明确授权；<br>
                        (2)根据有关的法律法规要求；<br>
                        (3)按照相关政府主管部门的要求；<br>
                        (4)为维护社会公众的利益。<br>
                        2.2 本站可能会与第三方合作向用户提供相关的网络服务，在此情况下，如该第三方同意承担与本站同等的保护用户隐私的责任，则本站有权将用户的注册资料等提供给该第三方。<br>
                        2.3 在不透露单个用户隐私资料的前提下，本站有权对整个用户数据库进行分析并对用户数据库进行商业上的利用。<br>
                        </p>

                        <p>
                        <h4>三、责任声明</h4>
                        3.1 用户明确同意其使用本站网络服务所存在的风险及一切后果将完全由用户本人承担，张槎街道行政服务中心对此不承担任何责任。<br>
                        3.2 本站无法保证网络服务一定能满足用户的要求，也不保证网络服务的及时性、安全性、准确性。<br>
                        3.3 本站不保证为方便用户而设置的外部链接的准确性和完整性，同时，对于该等外部链接指向的不由本站实际控制的任何网页上的内容，本站不承担任何责任。<br>
                        3.4 对于因不可抗力或本站不能控制的原因造成的网络服务中断或其它缺陷，本站不承担任何责任，但将尽力减少因此而给用户造成的损失和影响。<br>
                        3.5 对于站向用户提供的下列产品或者服务的质量缺陷本身及其引发的任何损失，本站无需承担任何责任：<br>
                        (1)本站向用户免费提供的各项网络服务；<br>
                        (2)本站向用户赠送的任何产品或者服务。<br>
                        3.6 本站有权于任何时间暂时或永久修改或终止本服务(或其任何部分)，而无论其通知与否，本站对用户和任何第三人均无需承担任何责任。<br>
                        </p>
                    </div>
                    <%--<div class="weui-cells">--%>

                        <%--<div class="weui-cell">--%>
                            <%--<div class="weui-cell__bd">--%>
                                <%--<button name="Submit" class="weui-btn weui-btn_primary"--%>
                                        <%--type="button" id="button1id" onclick="yuyueSure()">确认--%>
                                <%--</button>--%>
                            <%--</div>--%>
                            <%--&nbsp;&nbsp;&nbsp;--%>
                            <%--<div class=" weui-cell__bd">--%>
                                <%--<button class="weui-btn weui-btn_warn" id="button2id"--%>
                                        <%--type="button" onClick="WeixinJSBridge.call('closeWindow');">取消--%>
                                <%--</button>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </fieldset>
            </form>
            <input type="hidden" id="codeResult"></input>
            <div class=" foot fixPosition width100 bottomPosition iff">
                <div align="center" style="line-height:25px;">
                    <span style='font-size:12px;'>张槎街道行政服务中心授权使用&nbsp;&nbsp;南邮信息联合开发</span>
                </div>
            </div>
        </div>

    </div>
</div>

</body>

<%--<script type="text/javascript">--%>
    <%--function yuyueSure() {--%>
<%--//		location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaec78dd064e22ada&redirect_uri=http://zhengqiao.ss.gov.cn/ssWechat/YuYues!yuYues&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";--%>
        <%--location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChat.APPID+"&redirect_uri="+weChat.WeChatDNSURL+"YuYues!yuYues&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";--%>
    <%--}--%>
<%--</script>--%>

</html>