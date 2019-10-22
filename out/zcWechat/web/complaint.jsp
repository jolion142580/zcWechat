<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path +"/";
    String openid = (String) session.getAttribute("openid");
%>


<%--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">--%>
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

    <link rel="stylesheet"
          href="http://g.alicdn.com/msui/sm/0.6.2/css/sm.min.css"/>
    <link rel="stylesheet"
          href="http://g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css"/>

    <link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="lib/weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/style.css"/>

    <%--<link rel="stylesheet" href="<%=basePath %>css/mystyle.css" />--%>
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="js/jquery-weui.js"></script>
    <script type="text/javascript" src="js/config.js"></script>
    <%--<script src="scripts/window.js" type="text/javascript"></script>--%>
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

        .weui-cell {
            padding: 10px 15px 30px;
        }

        .main_color {
            color: #FFFFFF;
        }

        .footer {
            background-color: #f7f7fa;
            height: 64px;
            text-align: center;
        }

        .weui-tabbar {
            display: flex;
            position: absolute;
            z-index: 500;
            bottom: 0;
            width: 100%;
            height: 100px;
            background-color: #f7f7fa;
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
<%--onload="vailToken();"--%>
<body style="height: 100%;">
<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav"
                style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
            <h1 class="title main_color">留言</h1>
        </header>

        <div class="content" style="height: 80%;">
            <div class="weui-cells__title">用户留言</div>
            <div id="tshf">
            </div>

            <%--<div class="gl_r_m_go" align="center">
            </div>--%>
            <%-- <div style="height:24px">

             </div>--%>
            <div style="display: none;">
                <p id="kt"></p>
                <p id="ys"></p>
                <p id="ysl"></p>
            </div>
            <div style="text-align: center; ">
                <button id="yanz">
                    <span style="text-align: center;line-height: 32px;">我要留言</span>
                </button>
            </div>


            <%-- <div class="weui-tabbar">&lt;%&ndash;class="weui-tabbar"&ndash;%&gt;
                 <b class="weui-cell weui-cell_access"
                    id="yanz">
                     <div class="weui-cell__hd" >
                         <img src="icon/icon (25).png" style="width:1rem"/>
                     </div>
                     <div class="weui-cell__bd">
                         <span>我要留言</span>
                     </div>
                 </b>
             </div>--%>

        </div>
    </div>
</div>
<script type="text/javascript">
    var kt = 1;
    var ys = 0;
    $(function () {
        gotofy(kt, ys);
        $("#yanz").click(function () {
            if (!window.parent.vailFlag()) {
                return;
            }
            window.location = "web/submitcomplaint.jsp";
        })
    });

    function gotofy(kt, ys) {
        $("#kt").val(kt);//把初始值传到p保存
        $("#ys").val(ys);//把余数参数传到p保存
        $("#tshf").empty();
        var iy = 0;
        var ih = 5;
        if (kt == 1) {
            iy = 1 * kt - 1;
        } else {
            iy = 5 * kt - 5;
        }
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
                    if (data.length == 0) {
                        return;
                    }
                    var loginOpenId = '<%=openid%>';
                    var p = "";
                    var a = "<div align='center'><span onclick='gotoUp()'>上一页</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    // console.log(data);
                    var yushu = data.length % 5;
                    var abq = Math.ceil(data.length / 5);
                    $("#ysl").val(abq);
                    if (yushu != 0 && data.length < 5) {
                        ih = yushu;
                    }
                    for (k = 0; k < abq; k++) {
                        if (k == (abq - 1)) {
                            a += "<a  onclick='gotofy(kt=this.text," + yushu + ")'>" + (k + 1) + "</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                            $("#ys").val(yushu);
                        } else {
                            a += "<a onclick='gotofy(kt=this.text,0)'>" + (k + 1) + "</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                        }
                    }
                    a += "<span onclick='gotoDown()' >下一页</span></div>";
                    for (i = iy; i < ih; i++) {
                        var openid = data[i].open_Id;

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
                        if (openid == loginOpenId) {
                            na = "我";
                        }
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
                    return;
                }
            });
    }

    function gotoUp() {
        if (parseInt($("#kt").val()) == 1 || parseInt($("#kt").val()) == "") {
            alert("当前为第一页");
            return
        }
        kt = parseInt($("#kt").val()) - 1;
        ys = 0;
        gotofy(kt, ys);
    }

    function gotoDown() {

        var kt = parseInt($("#kt").val()) + 1;//从kt获取回来的，加一页进入下一页

        var p = parseInt($("#ys").val()); //余数
        var ysl = parseInt($("#ysl").val());//页数
        if (ysl < kt) {
            alert("当前为最后一页");
            return
        }
        if (ysl == kt) {
            ys = p;
        }
        gotofy(kt, ys);
    }

</script>
</body>
</html>
