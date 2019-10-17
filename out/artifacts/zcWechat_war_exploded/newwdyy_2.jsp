<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@page import="java.util.*" %>
<%@page import="com.gdyiko.zcwx.weixinUtils.OAuth" %>


<%
    String code = request.getParameter("code");
    OAuth oauth = new OAuth();
    String openid = oauth.getOppenid(code);
    //String openid="ovh5dxGh-9EXBe-fFYD5IU1fSW4k";
    //System.out.println("2222---"+openid);

%>
<!DOCTYPE html>
<html>
<head>
    <meta name="generator"
          content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39"/>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>张槎行政服务中心</title>
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
        .main_color2 {
            color: #ffffff;
        }

        tr {
            height: 32px;
        }

        tr td {
            text-align: center;
        }

        .weui-navbar a {
            text-decoration: none;
            color: #333;
        }

        .weui_tab_bd_item {
            display: none;
            /*height: 100%;*/
            overflow: auto;
        }

        .weui_tab_bd_item.weui_tab_bd_item_active {
            display: block;
        }
    </style>
</head>
<body>
<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav"
                style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
            <a class="button button-link button-nav pull-left" href="#"
               onclick="javascript:WeixinJSBridge.call('closeWindow');">
                <span class="icon icon-left main_color2"></span></a>
            <h1 class="title main_color2">我的预约记录</h1>
        </header>
        <div class="content" style="height: 100%;width: 100%">
            <div class="weui-tab">
                <div class="weui-navbar">
                    <a class="weui-navbar__item weui-bar__item_on" href="#tab1">
                        我的预约
                    </a>
                    <a class="weui-navbar__item" href="#tab2">
                        往期预约
                    </a>

                </div>
                <div class="weui-tab__panel">
                    <div id="tab1" class="weui_tab_bd_item weui_tab_bd_item_active">
                        <div id="content1">

                        </div>
                        <div class="">
                            <footer id="footer1">
                            </footer>
                        </div>
                    </div>
                    <div id="tab2" class="weui_tab_bd_item">
                        <div id="content2">

                        </div>
                        <div class="">
                            <footer id="footer2">

                            </footer>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<%--<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>--%>
<script src="lib/jquery-2.1.4.js"></script>
<script src="js/jquery-weui.js"></script>
<script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
    var object;
    $(function () {
        getList();
        skipTab();
    });

    function skipTab() {
        $('.weui-navbar__item').on('click', function () {
            $(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
            //内容切换
            $(".weui-tab__panel .weui_tab_bd_item_active").removeClass('weui_tab_bd_item_active');
            var data_toggle = jQuery(this).attr("href");
            $(data_toggle).addClass("weui_tab_bd_item_active");
        });
    }

    //地址
    function getList() {
        $.ajax({
            url: '${path}YuYues!userYuYuesJson',
            type: 'POST',
            dataType: "JSON",
            data: {
                //code:code
                openid: '<%=openid%>'
            },
            success: function (r) {
                // console.log(r.old);
                // console.log(r.future);
                loadFuture(r.future);
                loadOld(r.old);
            },
            error: function (e) {

            }
        });
    }

    function loadFuture(r) {
        if (r.length != 0) {
            var html = "<table width='100%'> <tr><th  width='50%'>流水号</th><th width='50%'>预约时间</th></tr>";
            for (var i in r) {
                if (r[i].state == 2) {
                    html += "<tr style='color: blue;' onclick='showDetail(\"" + r[i].no + "\",\"" + r[i].idcard + "\")'><td>" + r[i].no + "</td><td>" + r[i].ydate + "</td></tr>";
                } else {
                    html += "<tr onclick='showDetail(\"" + r[i].no + "\",\"" + r[i].idcard + "\")'><td>" + r[i].no + "</td><td>" + r[i].ydate + "</td></tr>";
                }
            }
            html += "</table>";
            $("#content1").append(html);
            $("#footer1").append("<h3>当前共有 " + r.length + " 条预约内容</h3>")
        } else {
            $('#content1').append(
                "<div '><div ><div  style='text-align:center;'><div >没有相关预约信息" +
                "</div></div></div></div>");
        }
    }

    function loadOld(r) {
        if (r.length != 0) {
            var html = "<table width='100%'> <tr ><th >流水号</th><th >预约日期</" +
                "th><th>状态</th></tr>";

            for (var i in r) {
                var state = getState(r[i].state);
                if (r[i].state == 4 || r[i].state == 0) {
                    html += "<tr style='color: red;' onclick='showDetail(\"" + r[i].no + "\",\"" + r[i].idcard + "\")'><td>" + r[i].no + "</td><td>" + r[i].ydate + "</td><td>" + state + "</td></tr>";
                } else if (r[i].state == 2) {
                    html += "<tr style='color: blue;' onclick='showDetail(\"" + r[i].no + "\",\"" + r[i].idcard + "\")'><td>" + r[i].no + "</td><td>" + r[i].ydate + "</td><td>" + state + "</td></tr>";
                } else {
                    html += "<tr onclick='showDetail(\"" + r[i].no + "\",\"" + r[i].idcard + "\")'><td>" + r[i].no + "</td><td>" + r[i].ydate + "</td><td>" + state + "</td></tr>";
                }

            }
            html += "</table>";
            $("#content2").append(html);
            $("#footer2").append("<h3>当前共有 " + r.length + " 条预约内容</h3>")
        } else {
            $('#content2').append(
                "<div '><div ><div  style='text-align:center;'><div >没有相关预约信息" +
                "</div></div></div></div>");
        }
    }

    function showDetail(no, idCard) {
        /*if (checkCell()) {
            alert("请在工作时间查询！")
            return;
        }
*/
        $.ajax({
            type: "post",
            data: {"no": no, "idcard": idCard},
            url: "YuYues!singleYuYueJson",
            success: function (data) {
                var r = eval("(" + data + ")");
                var yuyue = r.yuyue;
                if (yuyue == undefined || yuyue == null) {
                    alert(r.msg);
                    return;
                }
                /*
                  var code = r.code;
                  if (code == 0) {
                     alert(r.msg);
                     return;
                 }*/

                var buttons = [
                    {
                        text: "取消预约",
                        onClick: function () {
                            cancelYuYue(yuyue.no, yuyue.id_card);
                        }
                    }
                    ,
                    {
                        text: "关闭", className:
                            "default"
                    }
                ];
                var state = getState(yuyue.state);
                var result = dateCompare(yuyue.y_date);
                if (result == 0 || yuyue.state != 0) {
                    buttons.shift(); //把"取消预约"按钮去除
                }
                var title = yuyue.no;
                var text = "<p>姓名：" + yuyue.name + "</p><p>身份证：" + yuyue.id_card + "</p><p>手机号：" + yuyue.phone + "</p><p>预约日期：" + yuyue.y_date +
                    "</p><p>预约时间：" + yuyue.s_time + "-" + yuyue.e_time + "</p><p>办理事项：" + yuyue.businessName + "</p><p style='color: green'>状态：" + state;


                $.modal({
                    title: title,
                    text: text,
                    buttons: buttons
                });


            }
        });
    }

    function checkCell() {
        var nowTime = (new Date()).getHours();
        if (nowTime >= 8 && nowTime < 17) {
            return false;
        }
        return true;
    }

    function getState(state) {
        if (state == 0) {
            return "未签到";
        }
        if (state == 1) {
            return "已签到";
        }
        if (state == 2) {
            return "已取消";
        }
        // 👇👇👇👇
        if (state == 3) {
            return "已补签";
        }
        if (state == 4) {
            return "失约";
        }
        return state;
    }

    function dateCompare(date1) {
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDay();
        if (month < 10) {
            month = "0" + month;
        }
        if (day < 10) {
            day = "0" + day;
        }
        var date2 = year + "-" + month + "-" + day;
        date1 = date1.replace(/\-/gi, "/");
        date2 = date2.replace(/\-/gi, "/");
        var time1 = new Date(date1).getTime();
        var time2 = new Date(date2).getTime();
        if (time1 >= time2) {
            return 1;
        } else {
            return 0;
        }
    }

    function cancelYuYue(no, idCard) {
        // alert(no + "::::::::::" + idCard);
        $.ajax({
            url: "YuYues!cancelYuYue",
            type: "get",
            data: {"no": no, "idcard": idCard},
            success: function (data) {
                var r = eval("(" + data + ")");
                if (r.code == 1) {
                    alert(r.msg);
                } else if (r.code == 0) {
                    alert(r.msg);
                } else {
                    alert("错误！");
                }
                window.location.reload();
            }
        });
    }

    function diff(obj1, obj2) {
        var o1 = obj1 instanceof Object;
        var o2 = obj2 instanceof Object;
        if (!o1 || !o2) {/*  判断不是对象  */
            return obj1 === obj2;
        }

        if (Object.keys(obj1).length !== Object.keys(obj2).length) {
            return false;
        }

        for (var attr in obj1) {
            var t1 = obj1[attr] instanceof Object;
            var t2 = obj2[attr] instanceof Object;
            if (t1 && t2) {
                return diff(obj1[attr], obj2[attr]);
            } else if (obj1[attr] !== obj2[attr]) {
                return false;
            }
        }
        return true;
    }
</script>
</body>
</html>
