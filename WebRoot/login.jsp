<%--
  Created by IntelliJ IDEA.
  User: Bink
  Date: 2019-07-25
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>张槎街道行政服务中心</title>
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport" />
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <link href="css/newlogin.css" rel="stylesheet" type="text/css" />
</head>
<body>

<section class="aui-flexView">
    <header class="aui-navBar aui-navBar-fixed">
        <a href="javascript:;" class="aui-navBar-item">
            <i class="icon icon-return"></i>
        </a>
        <div class="aui-center">
            <span class="aui-center-title"></span>
        </div>
        <a href="javascript:;" class="aui-navBar-item" >
            <i class="icon icon-sys"></i>
        </a>
    </header>
    <section class="aui-scrollView">
        <div class="aui-sign-head">
            <img src="images\weixin\wangshangyuyue.gif" alt="">
        </div>
        <div class="aui-sign-form">
            <form action="wxlogin">
                <div class="aui-flex">
                    <i class="icon icon-phone"></i>
                    <div class="aui-flex-box">
                        <input type="text" id="phone1" name="phone" autocomplete="off" placeholder="输入手机号码">
                    </div>
                </div>
                <div class="aui-flex">
                    <i class="icon icon-code"></i>
                    <div class="aui-flex-box">
                        <input type="text"  id="code1" name="SmsCode" autocomplete="off" placeholder="输入验证码">
                    </div>
                    <div class="aui-code">
                        <input id="btnSendCode1"  type="button" class="btn btn-default" value="获取验证码" onClick="sendMessage1()" />
                    </div>
                </div>
                <input type="submit" class="aui-sign-login"  value="立即登录" />

                <div class="aui-flex aui-flex-clear">
                    <div class="aui-flex-box">
                        <a href="userInfo.jsp">登记信息</a>
                    </div>
                </div>
            </form>

        </div>

    </section>
</section>
</body>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="http://pv.sohu.com/cityjson?ie=utf-8" charset="gb2312"></script>


<%--<script type="text/javascript" src="js/store.min.js"></script>--%>
<script type="text/javascript">
    var phoneReg = /(^1[3|4|5|7|8]\d{9}$)|(^09\d{8}$)/;
    var count = 60;
    var InterValObj1;
    var curCount1;

    var phonecount=5; //手机限制次数
    var ipcount=10;  //IP限制次数

    $(function () {
       /* var s = getCookie("user");
        var ja = JSON.parse(s);
        alert(ja.phone);
        console.log(ja.phone);*/
    });

    function sendMessage1() {
        curCount1 = count;
        var phone = $.trim($('#phone1').val());
        if (!phoneReg.test(phone)) {
            alert(" 请输入有效的手机号码");
            return false;
        }


        $.post('smsinfo!saveSmsinfo', {smsmobile:phone,
            ip: returnCitySN["cip"],
            code:'',
            phonecount:phonecount,
            ipcount:ipcount,
            random_num: $("#random_num").val()
            },function(data){

            $("#btnSendCode1").attr("disabled", "true");
            $("#btnSendCode1").val( + curCount1 + "秒再获取");
            InterValObj1 = window.setInterval(SetRemainTime1, 1000);
        });
    }
    function SetRemainTime1() {
        if (curCount1 == 0) {
            window.clearInterval(InterValObj1);
            $("#btnSendCode1").removeAttr("disabled");
            $("#btnSendCode1").val("重新发送");
        }
        else {
            curCount1--;
            $("#btnSendCode1").val( + curCount1 + "秒再获取");
        }
    }

    function getCookie(name)
    {
        var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");

        if(arr=document.cookie.match(reg))

            return unescape(arr[2]);
        else
            return null;
    }

</script>
</html>
