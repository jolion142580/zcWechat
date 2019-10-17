<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta name="generator"
          content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39"/>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>张槎街道行政服务中心</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1"/>
    <%--<link rel="shortcut icon" href="/favicon.ico"/>--%>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="stylesheet" href="css/sm-extend.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/sm.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="lib/weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/guide-style.css" type="text/css"/>
    <%--<script src="scripts/window.js" type="text/javascript"></script>--%>
    <script type="text/javascript" src="js/config.js"></script>
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
    <%--初始化检测--%>
    <script type="text/javascript">
        function vailToken() {
            $.ajaxSettings.async = false;
            $.get("web!vailToken", function (data) {
                data = eval("(" + data + ")");
                if (data.flag == 'false') {
                    parent.window.location = "<%=basePath%>web!login";
                }
            })
            $.ajaxSettings.async = true;
        };
    </script>
</head>
<body ontouchstart>
<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav"
                style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
            <h1 class="title main_color">办件跟踪</h1></header>
        <div class="content" style="height: 100%;">
            <div style="margin:10px;background-color:#FFF;padding:10px;border-radius: 6px;height: 90%;">
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
                                        <%--<i class="weui-icon-search"></i>--%>
                                        <input type="search" class="weui-search-bar__input" name="currCode"
                                               id="currCode" placeholder="输入查询码" required="">
                                        <%--<a href="javascript:" class="weui-icon-clear" id="searchClear"></a>--%>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="weui-cell__ft" style="background-color:#f27b03">
                            <button onClick="commit();return false;" class="weui-vcode-btn"
                                    style="color:#FFF;width: 3rem;">查询
                            </button>
                        </div>

                    </div>
                </div>
                <div class="weui-cells__title">提示：请使用受理回执中的查询码进行查询。</div>

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
        // location.href = "affairProgressResult.jsp?currCode=" + $("#currCode").val() + "&username=" + encodeURI(encodeURI($("#name").val()));
        var currCode = $("#currCode").val();
        var name = $("#name").val();
        if (currCode == null || currCode == undefined || currCode == "") {
            alert("请输入受理编号!");
            return;
        }
        window.location = "web/affairProgressResult.jsp?currCode=" + currCode + "&username=" + encodeURI(encodeURI(name));
    }

    $(function () {
        $("#currCode").focus();
        findUserName();
    });

    function findUserName() {
        $.post("<%=basePath%>affairProgress!findUserName", function (res) {
            $("#name").val(eval("(" + res + ")").userName);
        })
    }

    $("#searchClear").click(function () {
        $("#currCode").val("");
    });
</script>

</body>
</html>
