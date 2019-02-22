<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/config.js"></script>
    <style type="text/css">
        .main_color {
            color: #FFFFFF;
        }

        .weui-article {
            padding: 20px 11px;
        }

        .weui-tabbar__icon {
            display: inline-block;
            width: 90px;
            height: auto;
        }

        .weui-tabbar__item {
            top: -40px;
        }

        .weui-tabbar {
            height: 80px;
        }

        .weui-article {
            margin-bottom: 100px;
        }

        p.bt {
            color: #35af98;
            font-weight: bolder;
        }
    </style>
</head>
<body>
<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav"
                style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
            <a class="button button-link button-nav pull-left" href="javascript:;"
               onclick="javascript:history.back(-1);"><span class="icon icon-left main_color"></span></a>
            <h1 class="title main_color">办事指南</h1>
        </header>

        <div class="content">
            <article class="weui-article">
                <div
                        style=" padding:15px 15px 30px 15px; margin:15px; border-radius:10px; background:#FFF;"
                        class="fonts">
                    <p class="bt">一、项目名称：</p>
                    <p>
                        <s:property value="ssAffairGuide.affairname" escape="false"/>
                    </p>
                    <%-- <p class="pt">二、政策依据</p>
        <p><s:property value="ssAffairGuide.according" escape="false" /></p> --%>
                    <p class="bt">二、受理条件：</p>
                    <p>
                        <s:property value="ssAffairGuide.condition" escape="false"/>
                    </p>
                    <p class="bt">三、申请材料</p>
                    <p>
                        <s:property value="ssAffairGuide.material" escape="false"/>
                    </p>
                    <%--  <p class="pt">五、办理程序：</p>
          <p><s:property value="ssAffairGuide.procedures" escape="false"/></p> --%>
                    <p class="bt">四、办理部门：</p>
                    <p>
                        <s:property value="ssAffairGuide.xrndomu" escape="false"/>
                    </p>
                    <p class="bt">五、受理地址：</p>
                    <p>
                        <s:if
                                test='<s:property value="ssAffairGuide.institution" /> != ""'>
                            <s:property value="ssAffairGuide.institution" escape="false"/>
                        </s:if>
                        <s:else>
                            <s:property value="ssAffairGuide.site" escape="false"/>
                        </s:else>
                    </p>
                    <p class="bt">六、办结时限：</p>
                    <p>
                        <s:property value="ssAffairGuide.time" escape="false"/>
                    </p>
                    <p class="bt">七、咨询查询：</p>
                    <p>
                        <s:property value="ssAffairGuide.inquire" escape="false"/>
                    </p>
                    <p class="bt">八、收费标准（政策依据）：</p>
                    <p>
                        <s:property value="ssAffairGuide.charge" escape="false"/>
                    </p>
                    <p class="bt">九、特别说明：</p>
                    <p>
                        <s:property value="ssAffairGuide.sepcialversion" escape="false"/>
                    </p>
                </div>
            </article>
        </div>
        <div class="weui-tabbar">
            <%--<a href="" class="weui-tabbar__item">--%>
                <%--<div class="weui-tabbar__icon">--%>
                    <%--<img src="icon/icon (23).png" alt=""/>--%>
                <%--</div>--%>
                <%--<p class="weui-tabbar__label" style="margin:0px;color:#35af98">网上预约</p>--%>
            <%--</a>--%>
            <s:if test="#ssAffairGuide.isonline=='true'">
                <%-- <a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid='+weChat.APPID+'&redirect_uri='+weChat.WeChatDNSURL+'onlineApply!isrelation?affairid=<s:property value="ssAffairGuide.affairid" escape="false" />_onlineApply&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect" class="weui-tabbar__item"> --%>
                <%-- <a href="http://localhost:8083/zcWechat/ssAffairsObjectInfo!findByAffairId?affairid=<s:property value="ssAffairGuide.affairid" escape="false" />" class="weui-tabbar__item"> --%>
                <a id="a_href_SET" <%-- href="https://open.weixin.qq.com/connect/oauth2/authorize?appid='+weChat.APPID+'&redirect_uri='+weChat.WeChatDNSURL+'ssAffairsObjectInfo!findByAffairId?affairid=<s:property value="ssAffairGuide.affairid" escape="false" />&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect"--%>
                   class="weui-tabbar__item">
                    <div class="weui-tabbar__icon">
                        <img src="icon/online.png" alt="">
                    </div>
                    <p class="weui-tabbar__label" style="margin:0px;color:#35af98">网上办事</p>
                </a>
            </s:if>

        </div>
    </div>
</div>
</body>
</html>
<script>
    $(function () {
        var HREF = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid='+weChat.APPID+'&redirect_uri='+weChat.WeChatDNSURL+'ssAffairsObjectInfo!findByAffairId?affairid=<s:property value="ssAffairGuide.affairid" escape="false" />&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect';
        $("#a_href_SET").attr("href",HREF);
    })

</script>