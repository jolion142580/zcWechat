<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
//    System.out.println("----openid---" + openid);
%>
<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script src='https://res.wx.qq.com/open/js/jweixin-1.0.0.js'></script>
    <script src="../js/config.js"></script>
    <script type="text/javascript">

        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: weChat.APPID, // 必填，企业号的唯一标识，此处填写企业号corpid
            timestamp: '<%=map.get("timestamp") %>',  // 必填，生成签名的时间戳
            nonceStr: '<%=map.get("nonceStr") %>', // 必填，生成签名的随机串
            signature: '<%=map.get("signature") %>',// 必填，签名，见附录1
            jsApiList: ['checkJsApi', 'scanQRCode']// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
        wx.ready(function () {
            // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
            wx.scanQRCode({
                needResult: 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
                scanType: ["qrCode"], // 可以指定扫二维码还是一维码，默认二者都有
                success: function (res) {
                    // var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
                    // console.log(res);
                    // alert(result);
                    // var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
                    // sessionStorage.setItem('saomiao_result', result);
                }
            });
        });

    </script>
</head>
<body>
</body>
</html>
