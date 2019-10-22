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
    <script type="text/javascript" src="js/jquery-weui.js"></script>

    <script type="text/javascript" src="js/config.js"></script>
    <style type="text/css">
        .main_color {
            color: #FFFFFF;
        }

        .weui-cells {
            margin-top: 0;
        }

        .weui-search-bar {
            background-color: #0490E4;
        }

        .weui-search-bar__form {
            background-color: #0490E4;
        }

        .weui-search-bar__cancel-btn {
            color: #FFFFFF;
        }
    </style>
</head>

<body ontouchstart>
<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav"
                style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
            <a class="button button-link button-nav pull-left" href="ssBaseDicInfo!findAllByBaseDicType">
                <span class="icon icon-left main_color"></span></a>
            <h1 class="title main_color">办事记录</h1>
        </header>

        <div class="content">
            <s:iterator value="onlineApplyList">
                <%--approvedOrNot 0表示审核通过 1表示审核不通过--%>
                <%--<s:if test="%{onlineData != null and (state=='预审不通过' || state=='待资料上传' )}">--%>
            <s:if test="%{(state=='已审核' and  approvedOrNot ==0)}">
                <%--<s:if test="%{onlineData != null and (state=='预审不通过' || state=='待资料上传' )}">--%>
            <a href="onlineModify.jsp?onlineApplyId=<s:property value="id"/>">

                </s:if>

                <s:else>
                <s:if test="%{onlineData == null and (approvedOrNot==0 || state=='待资料上传')}">
                    <%--<s:if test="%{onlineData == null and (state=='预审不通过' || state=='待资料上传')}">--%>

                <a id="a_href_SET" href="onlineApply!modifyAffairMaterials?id=<s:property value="id"/>">
                    </s:if>
                    </s:else>
                    <div class="weui-form-preview">
                        <div class="weui-form-preview__hd" style="text-align:left">
                            <label><s:property value="affairName"/></label>
                        </div>
                        <div class="weui-form-preview__bd" style="text-align:left">
                            <div class="weui-form-preview__item">
                                <label class="weui-form-preview__label">办件状态</label>
                                    <%--<span class="weui-form-preview__value"><s:property value="state"/></span>--%>
                                <s:if test="%{(state=='已审核' and  approvedOrNot ==0)}">
                                    <span class="weui-form-preview__value">预审不通过</span>
                                </s:if>
                                <s:if test="%{(state=='已审核' and  approvedOrNot ==1)}">
                                    <span class="weui-form-preview__value">预审通过</span>
                                </s:if>
                            </div>
                            <div class="weui-form-preview__item">
                                <label class="weui-form-preview__label">申请时间</label>
                                <span class="weui-form-preview__value"><s:property value="creattime"/></span>
                            </div>
                                <%--<div class="weui-form-preview__item">--%>
                                <%--<label class="weui-form-preview__label">test6</label>--%>
                                <%--<s:if test="%{(state=='已审核' and  approvedOrNot ==0)}">--%>
                                <%--<span class="weui-form-preview__value">预审不通过</span>--%>
                                <%--</s:if>--%>
                                <%--<s:if test="%{(state=='已审核' and  approvedOrNot ==1)}">--%>
                                <%--<span class="weui-form-preview__value">预审通过</span>--%>
                                <%--</s:if>--%>
                                <%--</div>--%>
                        </div>
                    </div>
                </a>
                <br>
                </s:iterator>

        </div>
    </div>
</div>
<div class=" foot fixPosition width100 bottomPosition iff">
    <div align="center" style="line-height:25px;">
        <span style='font-size:12px;'>张槎街道行政服务中心授权使用&nbsp;&nbsp;南邮信息联合开发</span>
    </div>
</div>
<script type="text/javascript" src="lib/jquery-2.1.4.js"></script>
<script type="text/javascript" src="lib/fastclick.js"></script>
<script>
    $(function () {
        /*  var HREF =weChat.WeChatDNSURL+'onlineApply!modifyAffairMaterials?id=





        <s:property value="id"/>';
        $("#a_href_SET").attr("href",HREF);*/
        FastClick.attach(document.body);
    });
</script>

</body>
</html>

