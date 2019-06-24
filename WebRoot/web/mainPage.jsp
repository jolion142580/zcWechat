<%@ page import="com.gdyiko.zcwx.po.resp.Token" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%
    String path = request.getContextPath();
//   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path +"/" ;
%>
<%
    String uuid = (String) session.getAttribute("uuid");
    String openid = null;
    if (uuid != null) {
//        openid = (String) application.getAttribute(uuid);
        openid = (String) ActionContext.getContext().getApplication().get(uuid);
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <title>首页 - 张槎街道行政服务中心</title>
    <link rel="icon" href="<%=basePath%>zcWechatImage/title.ico">
    <link rel="stylesheet" href="content/reflow.css">
    <link rel="stylesheet" href="content/interaction_pc.css">
    <%--<link rel="stylesheet" href="bootstrap3/css/bootstrap.min.css">--%>

    <script src="scripts/jquery.min.js" type="text/javascript"></script>
    <script src="scripts/hammer.min.js" type="text/javascript"></script>
    <script src="scripts/base.js" type="text/javascript"></script>
    <script src="js/config.js" type="text/javascript"></script>

    <script src="js/layer/layer.js" type="text/javascript"></script>
    <script src="bootstrap3/js/bootstrap.min.js" type="text/javascript"></script>
    <style type="text/css">
        .center{
            text-align : center;
            font-size : 18px;
        }
        #timeOut {
            color: orange;
            z-index: 999;
            width: 50px;
            float: right;
            height: 50px;
            font-size: 48px;
            color: red;
            margin-right: 36px;
            /*text-align:center;*/
            line-height: 50px;
            /*margin:0px auto;*/
        }

        .circle1 {
            border-radius: 3em;
            background-color: orange;
            color: white;
            text-shadow: 1px 1px 1px #000, -1px -1px 1px #fff;
        }

        body {
            /*   position: absolute;
               z-index: 999;*/
            width: 100%;
            height: 100%;
        }

        .line {
            border-bottom: 1px solid #000;
            cursor: pointer;
        }

        .bg_Orange {
            background-color: orange;
            color: white;
        }

        .showInformation {
            /*pointer-events:none;*/
            width: 100%;
            height: 100%;
            overflow: hidden;
            border-width: 0px;
            border-left-width: 1px;
        }

        .iff {

            z-index: 10000;
        }

        .fixPosition {
            position: fixed;
        }

        .width100 {
            width: 100%;
        }

        .foot {
            border-top: #ddd 2px solid;
            background: #f8f8f8;
        }

        .bottomPosition {
            bottom: 0;
        }

        tr td {
            line-height: 64px;
            font-size: 16px;
            text-align: center;
        }

        tr td input {
            text-align: left;
        }

        input[type="text"] {
            border-style: none;
            border-bottom-style: solid;
            border-bottom-width: thin;
            border-bottom-color: #0490E4;
            text-align: center;
            width: 90%;
        }
    </style>
    <!--覆盖样式 -->
    <style type="text/css">

        .content_review .body_review {
            float: left;
            width: 100%;
            height: 99%;
            overflow: hidden;
            margin: 0 auto;
            /*width: 92%;*/
            border: 0px hidden #fff;
            background: #fff;
            -webkit-transition: none;
            /*-o-transition: none;*/
            transition: none;
        }

        .header {
            z-index: 1;
            display: block;
            width: 100%;
            height: 48px;
            line-height: 48px;
            background: #0490E4;
            color: #fff;
            overflow: hidden !important;
        }

        #loginName {
            /*margin-left: 16px;*/
        }

        .title_formNeed {
            background-color: rgb(4, 144, 228);
            color: white;
            width: 100%;
            height: 56px;
            line-height: 28px;
            /*text-indent: 16px*/
            font-size: 14px;
        }

        .content_formlist {
            position: absolute;
            left: 0;
            top: 0;
            width: 15%;
        }

        .content_imgcontent {
            position: absolute;
            right: 0;
            top: 0;
            display: block;
            width: 85%;
            overflow: hidden
        }
    </style>

    <%--时间显示&登录人--%>
    <script type="text/javascript">
        // var showIdCard;
        var openid = '<%=openid%>';
        var t = null;
        t = setTimeout(time, 1000);//开始执行
        function time() {
            clearTimeout(t);//清除定时器
            dt = new Date();
            var y = dt.getYear() + 1900;
            var mm = dt.getMonth() + 1;
            var d = dt.getDate();
            // var weekday=["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
            var day = dt.getDay();
            var h = dt.getHours();
            var m = dt.getMinutes();
            var s = dt.getSeconds();
            if (h < 10) {
                h = "0" + h;
            }
            if (m < 10) {
                m = "0" + m;
            }
            if (s < 10) {
                s = "0" + s;
            }
            // document.getElementById("time").innerHTML =  "现在的时间为："+y+"年"+mm+"月"+d+"日"+weekday[day]+" "+h+":"+m+":"+s+"";
            document.getElementById("time").innerHTML = y + "年" + mm + "月" + d + "日&emsp;" + h + ":" + m + ":" + s;
            t = setTimeout(time, 1000); //设定定时器，循环执行
        }

        $(function () {
            if (openid != null && openid != "null") {
                loadUserInfo(openid);
            }
        });

        function loadUserInfo(openid) {
            $.ajax({
                type: "post",
                url: "ssUserInfo!findByOpenId",
                data: {"id": openid},
                async: false,
                success: function (data) {
                    var userInfo = eval('(' + data + ')');
                    // var text = "<div><label title='点击查看信息' onclick='showUserInfo(\"" + openid + "\")'>" + userInfo.name + "</label>，你好。<button disabled class='bg_Orange' >✔已认证</button></div>"
                    var text = "<div><label title='点击查看信息' class='line' onclick='showUserInfo()'>" + userInfo.name + "</label>，你好。" +
                        "<span class='circle1'>证</span> &emsp;" +
                        "</div>"
                    $("#loginName").append(text);
                    $("#name").val(userInfo.name);
                    $("#sex").val(userInfo.sex);
                    $("#brithday").val(userInfo.brithday);
                    var idCard = userInfo.idCard;
                    // showIdCard = idCard;
                    var length = idCard.length;
                    var prefix = idCard.substring(0, 4);
                    var suffix = idCard.substring(length - 4, length);
                    var hid = "";
                    for (var i = 0; i < length - 8; i++) {
                        hid += "*";
                    }
                    idCard = prefix + hid + suffix;
                    $("#idCard").val(idCard);
                    $("#phone").val(userInfo.phone);
                    $("#address").val(userInfo.address);
                }
            });
        }

        function showUserInfo() {
            $("#userInfo").attr("style", "display:block;");//显示div
            layer.open({
                area: ['800px', '520px'],
                title: "用户信息",
                type: 1,
                content: $("#userInfo"),
                end: function () {
                    $("#userInfo").attr("style", "display:none;");//隐藏div
                }
            });
        }

        function logout() {
            //需要确认？
            window.location = "<%=basePath%>web!logout";
        }
    </script>
    <%--菜单按钮操作--%>
    <script type="text/javascript">
        function menuBtn(url) {
            if (url == "web!newHistory" || url == "web!affairProgress") {
                //检测登录情况
                vailToken();
                if (!flag) {
                    openQRCode();
                    return;
                }
            }
            $("#showInformation").attr("src", url);
        }
    </script>
    <%--初始化检测--%>
    <script type="text/javascript">
        var flag = true;

        /* 子页面检测 */
        function vailFlag() {
            vailToken();
            if (!flag) {
                openQRCode();
            }
            return flag;
        }

        function vailToken() {
            $.ajaxSettings.async = false;
            $.get("web!vailToken", function (data) {
                data = eval("(" + data + ")");
                if (data.flag == 'false') {
                    flag = false;
                }
            })
            $.ajaxSettings.async = true;
        };
    </script>
    <%--登录扫码--%>
    <script type="text/javascript">
        /*登录测试*/
        function demo() {
            //添加随机数避免缓存
            var random_number = Math.floor(Math.random() * ( 1000 + 1));
            $.ajaxSettings.async = false;
            $.post('QRCode!createUUID?'+random_number );
            $.get('web!demoLogin?'+random_number);
            $.ajaxSettings.async = true;
            window.location = "<%=basePath%>";
        }
        var tt = null; //扫码登录id
        function openQRCode() {
            if (tt != null && tt != undefined) {
                clearTimeout(tt);
            }
            $.post('QRCode!createUUID', function (data) {
                //刷新二维码的uuid,
                $("#imgId").attr("src", "QRCode!createQRCode?" + Math.random());
                scanLayer = layer.open({
                    type: 1,
                    title: "登录二维码",
                    closeBtn: 0,
                    area: '258px',
                    // skin: 'demo-class',
                    shade:0,
                    shadeClose: true,
                    content: $('#QRCodeImg'),
                    success: function (layero, index) {
                        countdown = 60; //监听60秒没有扫码登录就关闭;
                        settime();
                    }
                });

            });

        }

        function settime() {
            if (countdown == 5) {
                layer.close(scanLayer);
                clearTimeout(tt);
            } else {
                scanFun();
                countdown--;
                tt = setTimeout(function () {
                    settime();
                }, 1000)
            }
        }

        function scanFun() {
            /*  $.get('QRCode!scanQRCode', function (data) {
                  data = eval("("+data+")");
                  if (data.state == "success") {
                      clearTimeout(tt);
                      window.location.reload();
                  } else {
                      // listen();
                  }
              });*/
            $.post('QRCode!listenUUID', function (data) {
                if (data == "true") {
                    clearTimeout(tt);
                    // window.location.reload();
                    window.location = "<%=basePath%>";
                }

            });
        }
    </script>
</head>
<body>
<div class="header titlebar" style="position: fixed ;top:0;left: 0;height: 124px;">
    <div style="text-align: center;">
        <img src="images/logo.png">
    </div>
</div>
<div style="height: 100%;width: 100%; position: fixed ;top:124px;">
    <div class="contain show_subcontent show_review">
        <div class="content content_subcontent transition"
             style="touch-action: pan-y; user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);width:100%">
            <div class="content content_formlist" id="content_formlist">
                <div class="title_formNeed">
                    <p id="time"></p>
                    <p id="loginName"></p>
                </div>
                <div class="scroll_parent" style="height: 100%;">
                    <div class="scroll scroll_content auto_plus">
                        <dt class="title_list"></dt>
                        <div>
                            <dd class="angency selection"
                                onclick="menuBtn('http://zcxzfwzx.chancheng.gov.cn/wxfb/zxjj/index.htm')">
                                <div class="item_form"><p class="form_name">中心简介</p><span
                                        class="ic ic_arrowright"></span></div>
                            </dd>
                            <dd class="angency" onclick="menuBtn('web!map')">
                                <div class="item_form"><p class="form_name">联系方式</p><span
                                        class="ic ic_arrowright"></span></div>
                            </dd>
                            <dd class="angency" onclick="menuBtn('web!complaint')">
                                <div class="item_form"><p class="form_name">市民留言</p><span
                                        class="ic ic_arrowright"></span></div>
                            </dd>
                            <dd class="angency " onclick="menuBtn('ssBaseDicInfo!findAllByBaseDicTypeToWeb')">
                                <div class="item_form"><p class="form_name">网上办事</p><span
                                        class="ic ic_arrowright"></span></div>
                            </dd>

                            <dd class="angency" onclick="menuBtn('web!newHistory')">
                                <div class="item_form"><p class="form_name">办事记录</p><span
                                        class="ic ic_arrowright"></span></div>
                            </dd>
                            <dd class="angency" onclick="menuBtn('web!affairProgress')">
                                <div class="item_form"><p class="form_name">办件跟踪</p><span
                                        class="ic ic_arrowright"></span></div>
                            </dd>
                            <dd class="angency" id="inOrOut" onclick="openQRCode()">
                                <div class="item_form"><p class="form_name">我要登录</p><span
                                ></span></div>
                            </dd>
                            <dd class="angency" id="demo" onclick="demo()">
                                <div class="item_form"><p class="form_name">测试用户</p><span
                                ></span></div>
                            </dd>
                        </div>
                    </div>
                </div>
            </div>
            <div class="content content_imgcontent">
                <div id="scrollDiv" class="content_review">
                    <div class="scroll body_review">
                        <iframe class="showInformation" id="showInformation"
                                src="http://zcxzfwzx.chancheng.gov.cn/wxfb/zxjj/index.htm"></iframe>
                    </div>
                </div>
            </div>

        </div>

    </div>
</div>
<div class=" foot fixPosition width100 bottomPosition iff">
    <div align="center" style="line-height:25px;">
        <span style='font-size:12px;'>张槎街道行政服务中心授权使用&nbsp;&nbsp;南邮信息联合开发</span>
    </div>
</div>
<%--用户信息--%>
<div id="userInfo" style="display:none;">
    <table width="100%">
        <tr>
            <td>
                <label>姓名:</label>
            </td>
            <td>
                <input id="name" type="text" readonly value="">
            </td>
        </tr>
        <tr>
            <td>
                <label>性别:</label>
            </td>
            <td>
                <input id="sex" type="text" readonly value="">
            </td>
        </tr>
        <tr>
            <td>
                <label>出生日期:</label>
            </td>
            <td>
                <input id="brithday" type="text" readonly value="">
            </td>
        </tr>
        <tr>
            <td>
                <label>身份证号:</label>
            </td>
            <td>
                <input id="idCard" type="text" readonly value="">
            </td>
        </tr>
        <tr>
            <td>
                <label>联系电话:</label>
            </td>
            <td>
                <input id="phone" type="text" readonly value="">
            </td>
        </tr>
        <tr>
            <td>
                <label>住址:</label>
            </td>
            <td>
                <input id="address" type="text" readonly value="">
            </td>
        </tr>
    </table>
    <p style="text-align: center;color: red;">
        本页只做查看
    </p>
</div>
<%--登录扫码--%>
<div id="QRCodeImg" style="display: none;">
    <div>
        <img id="imgId" src="" width="258px" height="258px">
        <div style="text-align:center;bottom:10px">使用手机微信扫一扫</div>
    </div>
</div>
<%--倒计时--%>
<div id="timeOut">

</div>

</body>
<script src="scripts/window.js" type="text/javascript"></script>
</html>
