<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="com.gdyiko.zcwx.weixinUtils.OAuth" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.WxJSSignUtil" %>
<%@ page import="java.util.Map" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>张槎街道行政服务中心</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="stylesheet" href="css/sm-extend.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/sm.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="lib/weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/guide-style.css" type="text/css"/>

    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/infoYz.js"></script>
    <script type="text/javascript" src="js/smscheck.js"></script>
    <script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="js/jquery-weui.js"></script>
    <script type="text/javascript" src="lib/zepto.js"></script>
    <script type="text/javascript" src="js/security/aes.js"></script>
    <script type="text/javascript" src="js/security/security.js"></script>


    <style type="text/css">
        .weui-cell__ft {
            text-align: center;
        }

        .weui-cell__ft .board {
            background-color: #efefef;
            border-radius: 10px;
            padding: 10px;
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
               onclick="javascript:WeixinJSBridge.call('closeWindow');"><span class="icon icon-left main_color"></span></a>
            <h1 class="title main_color">便民服务</h1>
        </header>

        <div class="content">

            <div class="weui-panel">
                <div class="weui-panel__bd">
                    <div class="weui-media-box weui-media-box_small-appmsg">
                        <div class="weui-cells">
                            <%--<a class="weui-cell weui-cell_access" href="http://zcxzfwzx.chancheng.gov.cn/zcWechat/wxfb/zdsj/index.htm">
                              <div class="weui-cell__hd"><img src="images/weixin/zxzx.png" alt="" style="width:20px;margin-right:5px;display:block"></div>
                              <div class="weui-cell__bd weui-cell_primary">
                                <p>最新资讯</p>
                              </div>
                              <span class="weui-cell__ft"></span>
                            </a>--%>
                            <a class="weui-cell weui-cell_access"
                               href="http://web.chelaile.net.cn/wwd/index?src=webapp_foshantraffic#">
                                <div class="weui-cell__hd"><img src="images/weixin/gj.png" alt=""
                                                                style="width:20px;margin-right:5px;display:block"></div>
                                <div class="weui-cell__bd weui-cell_primary">
                                    <p>实时公交查询(引用车来了app)</p>
                                </div>
                                <span class="weui-cell__ft"></span>
                            </a>
                            <a class="weui-cell weui-cell_access"
                               href="http://wx.fsga.gov.cn/fsweixin/FsIndexZAServlet?service=18A761AB75264F79ACEC116290651D9F&pageIndex=1&pageSize=10&key=aad1d82b-f2d6-4658-89cb-8eaf6bc0a4bb">
                                <div class="weui-cell__hd"><img src="images/weixin/txz.png" alt=""
                                                                style="width:20px;margin-right:5px;display:block"></div>
                                <div class="weui-cell__bd weui-cell_primary">
                                    <p>港澳通行证续签(引用佛山公安微信公众平台)</p>
                                </div>
                                <span class="weui-cell__ft"></span>
                            </a>
                            <a class="weui-cell weui-cell_access"
                               href="http://wx.fsga.gov.cn/fsweixin/QueryRoadInfoServlet2?oder=imgtitle&q_code=QS&key=f5fe29ba-c84e-448e-a4f9-b0e0da9f9207">
                                <div class="weui-cell__hd"><img src="images/weixin/lk.png" alt=""
                                                                style="width:20px;margin-right:5px;display:block"></div>
                                <div class="weui-cell__bd weui-cell_primary">
                                    <p>路况快照(引用佛山公安微信公众平台)</p>
                                </div>
                                <span class="weui-cell__ft"></span>
                            </a>
                            <a class="weui-cell weui-cell_access"
                               href="http://wx.fsga.gov.cn/fsweixin/fsweb2/fsga/wscgs.jsp">
                                <div class="weui-cell__hd"><img src="images/weixin/jg.png" alt=""
                                                                style="width:20px;margin-right:5px;display:block"></div>
                                <div class="weui-cell__bd weui-cell_primary">
                                    <p>交管业务(引用佛山公安微信公众平台)</p>
                                </div>
                                <span class="weui-cell__ft"></span>
                            </a>
                            <a class="weui-cell weui-cell_access"
                               href="http://wx.fsga.gov.cn/fsweixin/FsIndexZAServlet?service=A92D95C287AF4ECFAE20FEFCD9201980&pageIndex=1&pageSize=10&key=1d83931b-0d84-4019-abd5-c219ebe3839d">
                                <div class="weui-cell__hd"><img src="images/weixin/hz.png" alt=""
                                                                style="width:20px;margin-right:5px;display:block"></div>
                                <div class="weui-cell__bd weui-cell_primary">
                                    <p>户政业务(引用佛山公安微信公众平台)</p>
                                </div>
                                <span class="weui-cell__ft"></span>
                            </a>

                            <a class="weui-cell weui-cell_access"
                               href="https://sicx.fssi.gov.cn/ydcx/login.jsp">
                                <div class="weui-cell__hd"><img src="icon/icon (1).png" alt=""
                                                                style="width:20px;margin-right:5px;display:block"></div>
                                <div class="weui-cell__bd weui-cell_primary">
                                    <p>社保查询（引用禅城社保公众号）</p>
                                </div>
                                <span class="weui-cell__ft"></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</body>


</html>
