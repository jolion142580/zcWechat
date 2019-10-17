<%
    String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path +"/";
%>
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script src="https://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
    <script src="../scripts/jquery.js"></script>
    <script src="../scripts/jquery.qrcode.min.js"></script>
    <script src="../js/config.js"></script>
    <script type="text/javascript" src="../js/jweixin-1.0.0.js"></script>
    <script type="text/javascript">
        wx.config({
            debug: true,
            appId: weChat.APPID,// 必填，公众号的唯一标识
            timestamp: '<%=map.get("timestamp") %>', // 必填，生成签名的时间戳
            nonceStr: '<%=map.get("nonceStr") %>', // 必填，生成签名的随机串
            signature: '<%=map.get("signature") %>',// 必填，签名，见附录1
            jsApiList: ['scanQRCode']
            // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
        $(function () {
            loadUUID();
        });

        function reLoad() {
            loadUUID();
        }

    </script>
    <script type="text/javascript">
        var WXurl = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=' + weChat.APPID + '&redirect_uri=' + weChat.weChatDNSURL + 'loginWeb!index&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect';
        var url = "loginWeb!index";

        function loadUUID() {
            $.ajax({
                async: false,
                url: url,
                type: "post",
                dataType: "text",
                success: function (data) {
                    $("#uuid").val(data);
                    loadEWM();
                }
            });
        }

        function loadEWM() {
            $("#pc_qr_code").empty();
            var uuid = $("#uuid").val();
            var content;
            /*  var obj = new WxLogin({
                  id: "pc_qr_code", // 需要显示的容器id
                  appid: weChat.APPID,  // 公众号appid wx*******
                  scope: "snsapi_login",  // 网页默认即可
                  redirect_uri: WXurl, // 授权成功后回调的url
                  state: "123", // 可设置为简单的随机数加session用来校验
                  style: "black", // 提供"black"、"white"可选。二维码的样式
                  href: "" // 外部css文件url，需要https
              });*/
            /*
             二维码
             */
            content = WXurl;//扫描显示的内容
            $('#pc_qr_code').qrcode({
                render: "canvas",
                width: 200,
                height: 200,
                correctLevel: 0,
                text: content,
                background: "#ffffff",
                foreground: "black",
                src: ""
            });

            // setCookie("sid", 123, -1 * 60 * 60 * 1000);
            // keepPool();//自动循环调用
        };

        //检测二维码
        function keepPool() {
            var uuid = $("#uuid").val();
            $.get("<%=basePath%>" + "web/login!detection", {uuid: uuid,}, function (msg) {
                //console.log(msg);
                if (msg.successFlag == '1') {
                    $("#result").html("扫码成功");
                    setCookie(msg.data.cname, msg.data.cvalue, 3 * 60 * 60 * 1000);
                    //alert("将跳转...");
                    window.location.href = "<%=basePath%>" + "/web/logInfo.jsp";
                } else if (msg.successFlag == '0') {
                    $("#result").html("该二维码已经失效,请重新获取");
                } else {
                    keepPool();
                }

            });
        }

        //设置cookie
        function setCookie(cname, cvalue, expireTime) {
            var d = new Date();
            d.setTime(d.getTime() + expireTime);//设置过期时间
            var expires = "expires=" + d.toUTCString();
            var path = "path=/"
            document.cookie = cname + "=" + cvalue + "; " + expires + "; " + path;
        }
    </script>
</head>
<style type="text/css">
    * {
        text-align: center;
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

</style>
<body>
<div>
    <p>
        微信关注公众号【张槎街道行政服务中心】
    </p>
    <p>
     <span>
            <img src="../zcWechatImage/zcWechart.5.jpg" title="扫一扫关注公众号">
        </span>
    </p>
    <p>
        请认证后再扫一扫操作网上办事
    </p>
    <div>
        <input type="hidden" id="uuid"/>
        <div id="pc_qr_code">
            <%--<img src="../zcWechatImage/zcWechart.5.jpg" title="扫一扫关注公众号">--%>
        </div>
        <button type="button" onclick="reLoad()">刷新</button>
        <div id="result">请使用手机扫码</div>
    </div>

</div>
<div class=" foot fixPosition width100 bottomPosition iff">
    <div align="center" style="line-height:25px;">
        <span style='font-size:12px;'>张槎街道行政服务中心授权使用&nbsp;&nbsp;南邮信息联合开发</span>
    </div>
</div>
</body>


</html>
