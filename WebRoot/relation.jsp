<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="com.gdyiko.zcwx.weixinUtils.OAuth" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.WxJSSignUtil" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.TokenHepl" %>


<%
    String token = TokenHepl.getaccessToken().getAccessToken();
    String jsapi_ticket = TokenHepl.jsapi_ticket;
    String url = WxJSSignUtil.getUrl();
    System.out.println("==url==" + url);
    System.out.println("jsapi_ticket==" + jsapi_ticket);
    Map map = WxJSSignUtil.sign(jsapi_ticket, url);

    String code = request.getParameter("code");
    String type = request.getParameter("type");
    System.out.println("url:::::" + url);
    System.out.println("code:::::" + code + "----type-----" + type);
    String openid = (String) session.getAttribute("openid");

    if (code != null && openid == null) {
        OAuth oauth = new OAuth();
        openid = oauth.getOppenid(code);
    }
    System.out.println("----openid---" + openid);
	/* String openid="1111";
	String code="22222";
	String type="33333";*/
    //System.out.println("----openid---"+openid);
%>
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

    <script type="text/javascript" src="js/config.js"></script>

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
            <h1 class="title main_color">认证绑定</h1>
        </header>

        <div class="content">


            <form id="accountform" action="" method="post">

                <input type="hidden" name="id" value="<%=openid%>">
                <input type="hidden" name="type" id="type" value="<%=type%>">
                <div class="weui-cells__title">请确认你的个人信息</div>
                <div class="weui-cells weui-cells_form">
                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label class="weui-label">办事人姓名</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input id="name" name="name" class="weui-input" type="text" placeholder="请输入办事人姓名"/>
                        </div>
                    </div>
                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label class="weui-label">身份证号码</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input id="idCard" name="idCard" onblur="writeToBrithday()" class="weui-input" type="text" placeholder="请输入身份证号码"/>
                        </div>

                    </div>

                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label for="" class="weui-label">手机号码</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input id="phone" name="phone" class="weui-input" type="text" value=""
                                   placeholder="请输入手机号码"/>
                        </div>
                    </div>

                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label for="" class="weui-label">性别</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input name="sex" type="radio" value="男"/>男
                            <input name="sex" type="radio" value="女"/>女
                        </div>
                    </div>

                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label for="" class="weui-label">出生日期</label></div>
                        <div class="weui-cell__bd">
                            <input class="weui-input" type="date" value="" id="brithday" name="brithday">
                        </div>
                    </div>


                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label for="" class="weui-label">联系地址</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input id="address" name="address" class="weui-input" type="text" value=""
                                   placeholder="请输入联系地址"/>
                        </div>
                    </div>
                    <div class="weui-cell weui-cell_vcode">
                        <div class="weui-cell__hd">
                            <label class="weui-label">验证码</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input id="random_num" name="random_num" class="weui-input" type="number"
                                   placeholder="请输入验证码"/>
                        </div>
                        <div class="weui-cell__ft">
                            <img id="randomming" class="weui-vcode-img"
                                 src="" onclick="reloadCode()" width="100%" height="40"
                                 title="点击更换验证码" alt="点击更换验证码"/>
                            <!--  <a href="javascript:void(0)" onclick="reloadCode()">换一张</a> -->
                        </div>

                    </div>
                    <div class="weui-cell weui-cell_vcode">
                        <div class="weui-cell__hd">
                            <label class="weui-label">短信验证码</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input id="random_sms" name="random_sms" class="weui-input" type="number"
                                   placeholder="请输入验证码"/>

                        </div>


                        <div class="weui-cell__ft">
                            <input type="button" id="btnSendCode" name="btnSendCode" class="weui-btn weui-btn_primary"
                                   width="50%" style="background-color: #911edb" value="获取验证码" onclick="sendMessage()"/>
                            <!--  <a href="javascript:void(0)" onclick="reloadCode()">换一张</a> -->
                        </div>

                    </div>
                </div>


                <div class="weui-cells__title">上传身份信息</div>
                <div class="weui-gallery" id="gallery">
                    <span class="weui-gallery__img" id="galleryImg"></span>
                    <div class="weui-gallery__opr">
                        <a href="javascript:" class="weui-gallery__del">
                            <i class="weui-icon-delete weui-icon_gallery-delete"></i>
                        </a>
                    </div>
                </div>
                <div class="weui-cells weui-cells_form">
                    <div class="weui-cell">
                        <div class="weui-cell__bd">
                            <div class="weui-uploader">
                                <div class="weui-uploader__hd">
                                    <p class="weui-uploader__title">身份证正反面</p>
                                    <div class="weui-uploader__info">0/2</div>
                                </div>
                                <div class="weui-uploader__bd">
                                    <ul class="weui-uploader__files" id="uploaderFiles">
                                        <!-- <li class="weui-uploader__file" style="background-image:url(images/pic_160.png)"></li> -->

                                    </ul>
                                    <div class="weui-uploader__input-box">
                                        <input id="uploaderInput" class="weui-uploader__input">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="weui-btn-area" style="margin-bottom: 30px;">
                    <div style="color:purple;font-size: 12px;text-align: center;">
                        温馨提示：香港居民身份证号码时，不需填添加括号，如M123456（7）则填写为M1234567。
                    </div>


                    <div style="color:purple;font-size: 14px;text-align: center;">

                        <label style="display: inline-block; margin-right: 0px; padding-right: 0px;" for="userProtocol"
                               class="weui-agree">
                            <input id="userProtocol" type="checkbox" class="weui-agree__checkbox">
                            <span class="weui-agree__text">阅读并同意</span>
                        </label>
                        <a href="userProtocol.jsp">《用户协议》</a>
                    </div>


                    <a class="weui-btn weui-btn_primary" href="javascript:" onClick="commit();"
                       id="showTooltips" style="background-color: #911edb">提交并保存</a>
                </div>

                <div align="center"
                     style="line-height:25px;width:100%;position: fixed;bottom: 0;z-index: 600;background-color: #efeff4;">
                    <span style="font-size:12px;">张槎街道行政服务中心授权使用&nbsp;&nbsp;南邮信息联合开发</span>
                </div>

            </form>
        </div>
    </div>
</div>
</body>

<script type="text/javascript" src="http://pv.sohu.com/cityjson?ie=utf-8" charset="gb2312"></script>


<script type="text/javascript">
    var maxCount = 2;
    var count = 0;
    function commit() {

        if ($("#random_sms").val() == "") {
            alert("短信验证码不能为空");
            $("#random_sms").focus();
            return;
        }
        if (!$("#userProtocol").is(":checked")) {
            alert("请勾选用户协议");
            return false;
        }
        var check = yz();
        if (check == true) {
//            alert($("form").serialize());
            $.post('ssUserInfo!save', {
                "random_num": $("#random_num").val(),
                "random_sms": $("#random_sms").val(),
                "id": "<%=openid%>",
                "name": $("#name").val(),
                "idCard": Encrypt($("#idCard").val()),
                "phone": Encrypt($("#phone").val()),
                "sex": $("input[name='sex']:checked").val(),
                "brithday": $("#brithday").val(),
                "address": $("#address").val()
            }, function (data) {

                if (eval("(" + data + ")").flag == 1) {
                    alert("绑定成功！");
                    if ($("#type").val() == "yuyue") {
                        location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChat.APPID+"&redirect_uri="+weChat.WeChatDNSURL+"YuYues!yuYues&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                    }
                    if ($("#type").val() == "tousu") {
//			location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaec78dd064e22ada&redirect_uri=http://zhengqiao.ss.gov.cn/wxfb/tj2/index.htm&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                        location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChat.APPID+"&redirect_uri="+weChat.WeChatDNSURL+"complaint.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                    }
                    if ($("#type").val() == "onlineApply") {
                        location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChat.APPID+"&redirect_uri="+weChat.WeChatDNSURL+"onlineWrite.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                    }
                    if ($("#type").val() == "affairMaterials") {
                        location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChat.APPID+"&redirect_uri="+weChat.WeChatDNSURL+"affairMaterials.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                    }
                    WeixinJSBridge.call('closeWindow');
                }
                if (eval("(" + data + ")").flag == "fail") {
                    alert(eval("(" + data + ")").message);
                }


            });
        } else {
            reloadCode();
        }
    }

    function reloadCode() {
        var image = document.getElementById("randomming");
        if (null != image) {
            image.src = "image.jsp?" + Math.random();
        }

    }

    function loadData() {

        $.post('ssUserInfo!findByOpenId', {id: '<%=openid%>'}, function (data) {

            if (data.length > 0) {
                var d = eval("(" + data + ")")
                $("#name").val(d.name);
                $("#idCard").val(d.idCard);
                $("#phone").val(d.phone);
                $("#address").val(d.address);
                $(":radio[name='sex'][value='" + d.sex + "']").prop("checked", "checked");
                $("#brithday").val(d.brithday);
            }

        });
        $.post('WeChatUpload!findByOpenId', {openid: '<%=openid%>', remark: '身份证正反面'}, function (data) {

            if (data.length > 0) {
                var d = eval("(" + data + ")")
                //alert(JSON.stringify(d));
                $.each(d, function (k, v) {
                    $('#uploaderFiles').append("<li id='" + v.mediaId + "'  class='weui-uploader__file' style='background-image:url(WeChatUpload!IoReadImage?mediaId=" + v.mediaId + ")'></li>");
                    $(".weui-uploader__info").text($('.weui-uploader__file').length + "/" + maxCount);
                    if ($('.weui-uploader__file').length >= maxCount) {
                        $(".weui-uploader__input-box").attr("style", "display:none");
                    }
                })


            }

        });
    }

    wx.ready(function () {

//多图上传
        $('#uploaderInput').on('click', function () {
            checkCount();
            wx.chooseImage({
                count: maxCount -count, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    var localIds = res.localIds;

                    syncUpload(localIds);
                }
            });
        });
        var syncUpload = function (localIds) {
            var localId = localIds.pop();
            wx.uploadImage({
                localId: localId,
                isShowProgressTips: 1,//显示进度提示
                success: function (res) {
                    var serverId = res.serverId; // 返回图片的服务器端ID
                    //将获取到的 mediaId 传入后台 方法savePicture
                    $.post("WeChatUpload!savePicture", {
                        mediaId: serverId,
                        openid: '<%=openid%>',
                        remark: '身份证正反面'
                    }, function (res) {
                         res = eval('(' + res + ')');
                         if(res.res == 'true'){
                        $('#uploaderFiles').append("<li id='" + serverId + "'  class='weui-uploader__file' style='background-image:url(WeChatUpload!IoReadImage?mediaId=" + serverId + ")'><input value=''  type='hidden'  name='img[]' /></li>");
                         }

                        //alert($('.weui-uploader__file').length);
                        $(".weui-uploader__info").text($('.weui-uploader__file').length + "/" + maxCount);
                        if ($('.weui-uploader__file').length >= maxCount) {
                            $(".weui-uploader__input-box").attr("style", "display:none");
                        }
                    })

                    if (localIds.length > 0) {
                        syncUpload(localIds);
                    }

                },
                fail: function (res) {
                    alertModal('上传图片失败，请重试')
                }

            });
        };
    });

    $(function () {
        wx.config({
//            debug: true,
            appId: weChat.APPID,// 必填，公众号的唯一标识
            timestamp: '<%=map.get("timestamp") %>', // 必填，生成签名的时间戳
            nonceStr: '<%=map.get("nonceStr") %>', // 必填，生成签名的随机串
            signature: '<%=map.get("signature") %>',// 必填，签名，见附录1
            jsApiList: ['chooseImage', 'uploadImage']
            // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
        reloadCode();
        loadData();

        var index; //第几张图片
        $("#uploaderFiles").on("click", "li", function () {
            index = $(this).index();
            //alert(JSON.stringify(this.getAttribute("style")))
            $("#galleryImg").attr("style", this.getAttribute("style"));
            $("#gallery").fadeIn(100);
        });
        $("#gallery").on("click", function () {
            $("#gallery").fadeOut(100);
        });
        //删除图片
        $(".weui-gallery__del").click(function () {

            $("#uploaderFiles").find("li").eq(index).remove();
            var imgStyle = $("#galleryImg").attr("style");
            var mediaId = imgStyle.substring(imgStyle.indexOf("=") + 1, imgStyle.length - 1);
            //alert(imgStyle.substring(imgStyle.indexOf("=")+1,imgStyle.length-1));
            $.post("WeChatUpload!delFile", {mediaId: mediaId}, function (res) {
                $(".weui-uploader__info").text($('.weui-uploader__file').length + "/" + maxCount);
                if ($('.weui-uploader__file').length < maxCount) {
                    $(".weui-uploader__input-box").attr("style", "display:block");
                }
            })

        });


    });

    function checkCount() {
        $.ajax({
            type: "post",
            async: false,
            url: "WeChatUpload!count",
            data: {
                "openid": "<%=openid%>",
                "remark": "身份证正反面"
            },
            success: function (data) {
                data = eval("(" + data + ")");
                count = data.size;
            }
        });
    }

    function writeToBrithday(){
        var idCard = $("#idCard").val();
        if(idCard.length<17){
            return ;
        }
        var brith = idCard.substring(6,10)+"-"+idCard.substring(10,12)+"-"+idCard.substring(12,14);
       $("#brithday").val(brith)
    }

</script>

</html>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="com.gdyiko.zcwx.weixinUtils.OAuth" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.WxJSSignUtil" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.TokenHepl" %>


<%
    String token = TokenHepl.getaccessToken().getAccessToken();
    String jsapi_ticket = TokenHepl.jsapi_ticket;
    String url = WxJSSignUtil.getUrl();
    System.out.println("==url==" + url);
    System.out.println("jsapi_ticket==" + jsapi_ticket);
    Map map = WxJSSignUtil.sign(jsapi_ticket, url);

    String code = request.getParameter("code");
    String type = request.getParameter("type");
    System.out.println("url:::::" + url);
    System.out.println("code:::::" + code + "----type-----" + type);
    String openid = (String) session.getAttribute("openid");

    if (code != null && openid == null) {
        OAuth oauth = new OAuth();
        openid = oauth.getOppenid(code);
    }
    System.out.println("----openid---" + openid);

%>
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

    <script type="text/javascript" src="js/config.js"></script>

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
            <h1 class="title main_color">认证绑定</h1>
        </header>

        <div class="content">


            <form id="accountform" action="" method="post">

                <input type="hidden" name="id" value="<%=openid%>">
                <input type="hidden" name="type" id="type" value="<%=type%>">
                <div class="weui-cells__title">请确认你的个人信息</div>
                <div class="weui-cells weui-cells_form">
                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label class="weui-label">办事人姓名</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input id="name" name="name" class="weui-input" type="text" placeholder="请输入办事人姓名"/>
                        </div>
                    </div>
                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label class="weui-label">身份证号码</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input id="idCard" name="idCard" onblur="writeToBrithday()" class="weui-input" type="text" placeholder="请输入身份证号码"/>
                        </div>

                    </div>

                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label for="" class="weui-label">手机号码</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input id="phone" name="phone" class="weui-input" type="text" value=""
                                   placeholder="请输入手机号码"/>
                        </div>
                    </div>

                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label for="" class="weui-label">性别</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input name="sex" type="radio" value="男"/>男
                            <input name="sex" type="radio" value="女"/>女
                        </div>
                    </div>

                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label for="" class="weui-label">出生日期</label></div>
                        <div class="weui-cell__bd">
                            <input class="weui-input" type="date" value="" id="brithday" name="brithday">
                        </div>
                    </div>


                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label for="" class="weui-label">联系地址</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input id="address" name="address" class="weui-input" type="text" value=""
                                   placeholder="请输入联系地址"/>
                        </div>
                    </div>
                    <div class="weui-cell weui-cell_vcode">
                        <div class="weui-cell__hd">
                            <label class="weui-label">验证码</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input id="random_num" name="random_num" class="weui-input" type="number"
                                   placeholder="请输入验证码"/>
                        </div>
                        <div class="weui-cell__ft">
                            <img id="randomming" class="weui-vcode-img"
                                 src="" onclick="reloadCode()" width="100%" height="40"
                                 title="点击更换验证码" alt="点击更换验证码"/>
                            <!--  <a href="javascript:void(0)" onclick="reloadCode()">换一张</a> -->
                        </div>

                    </div>
                    <div class="weui-cell weui-cell_vcode">
                        <div class="weui-cell__hd">
                            <label class="weui-label">短信验证码</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input id="random_sms" name="random_sms" class="weui-input" type="number"
                                   placeholder="请输入验证码"/>

                        </div>


                        <div class="weui-cell__ft">
                            <input type="button" id="btnSendCode" name="btnSendCode" class="weui-btn weui-btn_primary"
                                   width="50%" style="background-color: #911edb" value="获取验证码" onclick="sendMessage()"/>
                            <!--  <a href="javascript:void(0)" onclick="reloadCode()">换一张</a> -->
                        </div>

                    </div>
                </div>


                <div class="weui-cells__title">上传身份信息</div>
                <div class="weui-gallery" id="gallery">
                    <span class="weui-gallery__img" id="galleryImg"></span>
                    <div class="weui-gallery__opr">
                        <a href="javascript:" class="weui-gallery__del">
                            <i class="weui-icon-delete weui-icon_gallery-delete"></i>
                        </a>
                    </div>
                </div>
                <div class="weui-cells weui-cells_form">
                    <div class="weui-cell">
                        <div class="weui-cell__bd">
                            <div class="weui-uploader">
                                <div class="weui-uploader__hd">
                                    <p class="weui-uploader__title">身份证正反面</p>
                                    <div class="weui-uploader__info">0/2</div>
                                </div>
                                <div class="weui-uploader__bd">
                                    <ul class="weui-uploader__files" id="uploaderFiles">
                                        <!-- <li class="weui-uploader__file" style="background-image:url(images/pic_160.png)"></li> -->

                                    </ul>
                                    <div class="weui-uploader__input-box">
                                        <input id="uploaderInput" class="weui-uploader__input">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="weui-btn-area" style="margin-bottom: 30px;">
                    <div style="color:purple;font-size: 12px;text-align: center;">
                        温馨提示：香港居民身份证号码时，不需填添加括号，如M123456（7）则填写为M1234567。
                    </div>


                    <div style="color:purple;font-size: 14px;text-align: center;">

                        <label style="display: inline-block; margin-right: 0px; padding-right: 0px;" for="userProtocol"
                               class="weui-agree">
                            <input id="userProtocol" type="checkbox" class="weui-agree__checkbox">
                            <span class="weui-agree__text">阅读并同意</span>
                        </label>
                        <a href="userProtocol.jsp">《用户协议》</a>
                    </div>


                    <a class="weui-btn weui-btn_primary" href="javascript:" onClick="commit();"
                       id="showTooltips" style="background-color: #911edb">提交并保存</a>
                </div>

                <div align="center"
                     style="line-height:25px;width:100%;position: fixed;bottom: 0;z-index: 600;background-color: #efeff4;">
                    <span style="font-size:12px;">张槎街道行政服务中心授权使用&nbsp;&nbsp;南邮信息联合开发</span>
                </div>

            </form>
        </div>
    </div>
</div>
</body>

<script type="text/javascript" src="http://pv.sohu.com/cityjson?ie=utf-8" charset="gb2312"></script>


<script type="text/javascript">
    var maxCount = 2;
    var count = 0;
    function commit() {

        if ($("#random_sms").val() == "") {
            alert("短信验证码不能为空");
            $("#random_sms").focus();
            return;
        }
        if (!$("#userProtocol").is(":checked")) {
            alert("请勾选用户协议");
            return false;
        }
        var check = yz();
        if (check == true) {
            $.post('ssUserInfo!save', {
                "random_num": $("#random_num").val(),
                "random_sms": $("#random_sms").val(),
                "id": "<%=openid%>",
                "name": $("#name").val(),
                "idCard": Encrypt($("#idCard").val()),
                "phone": Encrypt($("#phone").val()),
                "sex": $("input[name='sex']:checked").val(),
                "brithday": $("#brithday").val(),
                "address": $("#address").val()
            }, function (data) {

                if (eval("(" + data + ")").flag == 1) {
                    alert("绑定成功！");
                    if ($("#type").val() == "yuyue") {
                        location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChat.APPID+"&redirect_uri="+weChat.WeChatDNSURL+"YuYues!yuYues&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                    }
                    if ($("#type").val() == "tousu") {
//			location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaec78dd064e22ada&redirect_uri=http://zhengqiao.ss.gov.cn/wxfb/tj2/index.htm&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                        location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChat.APPID+"&redirect_uri="+weChat.WeChatDNSURL+"complaint.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                    }
                    if ($("#type").val() == "onlineApply") {
                        location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChat.APPID+"&redirect_uri="+weChat.WeChatDNSURL+"onlineWrite.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                    }
                    if ($("#type").val() == "affairMaterials") {
                        location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChat.APPID+"&redirect_uri="+weChat.WeChatDNSURL+"affairMaterials.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                    }
                    WeixinJSBridge.call('closeWindow');
                }
                if (eval("(" + data + ")").flag == "fail") {
                    alert(eval("(" + data + ")").message);
                }


            });
        } else {
            reloadCode();
        }
    }

    function reloadCode() {
        var image = document.getElementById("randomming");
        if (null != image) {
            image.src = "image.jsp?" + Math.random();
        }

    }

    function loadData() {

        $.post('ssUserInfo!findByOpenId', {id: '<%=openid%>'}, function (data) {

            if (data.length > 0) {
                var d = eval("(" + data + ")")
                $("#name").val(d.name);
                $("#idCard").val(d.idCard);
                $("#phone").val(d.phone);
                $("#address").val(d.address);
                $(":radio[name='sex'][value='" + d.sex + "']").prop("checked", "checked");
                $("#brithday").val(d.brithday);
            }

        });
        $.post('WeChatUpload!findByOpenId', {openid: '<%=openid%>', remark: '身份证正反面'}, function (data) {

            if (data.length > 0) {
                var d = eval("(" + data + ")")
                //alert(JSON.stringify(d));
                $.each(d, function (k, v) {
                    $('#uploaderFiles').append("<li id='" + v.mediaId + "'  class='weui-uploader__file' style='background-image:url(WeChatUpload!IoReadImage?mediaId=" + v.mediaId + ")'></li>");
                    $(".weui-uploader__info").text($('.weui-uploader__file').length + "/" + maxCount);
                    if ($('.weui-uploader__file').length >= maxCount) {
                        $(".weui-uploader__input-box").attr("style", "display:none");
                    }
                })


            }

        });
    }

    wx.ready(function () {

//多图上传
        $('#uploaderInput').on('click', function () {
            checkCount();
            wx.chooseImage({
                count: maxCount -count, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    var localIds = res.localIds;

                    syncUpload(localIds);
                }
            });
        });
        var syncUpload = function (localIds) {
            var localId = localIds.pop();
            wx.uploadImage({
                localId: localId,
                isShowProgressTips: 1,//显示进度提示
                success: function (res) {
                    var serverId = res.serverId; // 返回图片的服务器端ID
                    //将获取到的 mediaId 传入后台 方法savePicture
                    $.post("WeChatUpload!savePicture", {
                        mediaId: serverId,
                        openid: '<%=openid%>',
                        remark: '身份证正反面'
                    }, function (res) {
                         res = eval('(' + res + ')');
                         if(res.res == 'true'){
                        $('#uploaderFiles').append("<li id='" + serverId + "'  class='weui-uploader__file' style='background-image:url(WeChatUpload!IoReadImage?mediaId=" + serverId + ")'><input value=''  type='hidden'  name='img[]' /></li>");
                         }

                        //alert($('.weui-uploader__file').length);
                        $(".weui-uploader__info").text($('.weui-uploader__file').length + "/" + maxCount);
                        if ($('.weui-uploader__file').length >= maxCount) {
                            $(".weui-uploader__input-box").attr("style", "display:none");
                        }
                    })

                    if (localIds.length > 0) {
                        syncUpload(localIds);
                    }

                },
                fail: function (res) {
                    alertModal('上传图片失败，请重试')
                }

            });
        };
    });

    $(function () {
        wx.config({
//            debug: true,
            appId: weChat.APPID,// 必填，公众号的唯一标识
            timestamp: '<%=map.get("timestamp") %>', // 必填，生成签名的时间戳
            nonceStr: '<%=map.get("nonceStr") %>', // 必填，生成签名的随机串
            signature: '<%=map.get("signature") %>',// 必填，签名，见附录1
            jsApiList: ['chooseImage', 'uploadImage']
            // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
        reloadCode();
        loadData();

        var index; //第几张图片
        $("#uploaderFiles").on("click", "li", function () {
            index = $(this).index();
            //alert(JSON.stringify(this.getAttribute("style")))
            $("#galleryImg").attr("style", this.getAttribute("style"));
            $("#gallery").fadeIn(100);
        });
        $("#gallery").on("click", function () {
            $("#gallery").fadeOut(100);
        });
        //删除图片
        $(".weui-gallery__del").click(function () {

            $("#uploaderFiles").find("li").eq(index).remove();
            var imgStyle = $("#galleryImg").attr("style");
            var mediaId = imgStyle.substring(imgStyle.indexOf("=") + 1, imgStyle.length - 1);
            //alert(imgStyle.substring(imgStyle.indexOf("=")+1,imgStyle.length-1));
            $.post("WeChatUpload!delFile", {mediaId: mediaId}, function (res) {
                $(".weui-uploader__info").text($('.weui-uploader__file').length + "/" + maxCount);
                if ($('.weui-uploader__file').length < maxCount) {
                    $(".weui-uploader__input-box").attr("style", "display:block");
                }
            })

        });


    });

    function checkCount() {
        $.ajax({
            type: "post",
            async: false,
            url: "WeChatUpload!count",
            data: {
                "openid": "<%=openid%>",
                "remark": "身份证正反面"
            },
            success: function (data) {
                data = eval("(" + data + ")");
                count = data.size;
            }
        });
    }

    function writeToBrithday(){
        var idCard = $("#idCard").val();
        if(idCard.length<17){
            return ;
        }
        var brith = idCard.substring(6,10)+"-"+idCard.substring(10,12)+"-"+idCard.substring(12,14);
       $("#brithday").val(brith)
    }

</script>

</html>
>>>>>>> withoutWechatInterface
