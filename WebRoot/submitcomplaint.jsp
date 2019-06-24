<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.OAuth" %>
<%
    String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path +"/";
    String openid =(String) session.getAttribute("openid");
    System.out.println(openid);
%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
    <link rel="stylesheet" href="lib/weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="<%=basePath %>css/jquery-weui.css"/>
    <link rel="stylesheet" href="<%=basePath %>css/style.css"/>
    <script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/config.js"></script>

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
               onclick="javascript:WeixinJSBridge.call('closeWindow');">
                <span class="icon icon-left main_color" style="margin-top: 11px;"></span></a>
            <h1 class="title main_color">留言</h1>
        </header>
        <div class="content">
            <div class="weui-cells__title">我的留言</div>
            <!-- <input  type="button" value="测试" onclick="toBackground()"> -->
            <div class="weui-cells weui-cells_form">
                <%-- <form id="guestbookForm" action="${base}/guestbook.jspx" --%>
                <form id="save" method="post">


                    <div class="weui-cell">
                        <div class="weui-cell__bd">       <!-- onfocus="jiancha1()" -->
                            <textarea class="weui-textarea" name="title" id="title"
                                      placeholder="请填写标题或内容摘要......"></textarea>

                        </div>
                    </div>
                    <div class="weui-cell">
                        <div class="weui-cell__bd">             <!-- onfocus="jiancha2()" -->
                            <textarea class="weui-textarea" name="content" id="content"
                                      placeholder="请输入留言……" rows="5"></textarea>

                        </div>
                    </div>
                    <div class="weui-cell">
                        <%--<div class="weui-cell__bd">
                            <textarea class="weui-textarea" name="complaint_Name" id="complaint_Name"
                                placeholder="请填写你的姓名(不填写即为匿名用户)......" ></textarea>

                        </div>--%>
                    </div>
                   <%-- <div class="weui-cell weui-cell_vcode">
                        <div class="weui-cell__hd">
                            <label class="weui-label">电话号码</label>
                        </div>
                       <div class="weui-cell__bd phon">
                            <input class="weui-input" id="pho" name="phone"
                                   type="text" placeholder="请输入电话号码"/>
                        </div>
                    </div>--%>

                    <div class="weui-cell weui-cell_vcode">
                        <div class="weui-cell__bd">
                            <input id="code" name="code" class="weui-input" type="number" placeholder="请输入验证码"/>
                        </div>
                        <div class="weui-cell__ft">
                            <img id="randomming" class="weui-vcode-img"
                                 src="" onclick="reloadCode()" height="40"
                                 title="点击更换验证码" alt="点击更换验证码"/>
                            <input type="hidden" id="codeResult"></input>
                        </div>
                    </div>
                    <input name="ctgId" value="2" type="hidden"/>
                </form>
            </div>
            <div class="weui-btn-area">
                <%--<span style="margin-bottom:15px; font-size: 13px;color:#6C6C6C">（仅受理三水区行政服务中心各网点的投诉）</span>--%>
                <a class="weui-btn weui-btn_primary" onclick="sub()" id="submit"
                   style="background-color: #0890ee">提交</a><br/>


            </div>

        </div>


        <div align="center" style="line-height:25px;">
                <div align="center" style="line-height:25px;width:100%;position: fixed;bottom: 0;z-index: 600;background-color: #efeff4;"><span style="font-size:12px;">张槎街道行政服务中心授权使用&nbsp;&nbsp;南邮信息联合开发</span></div>
        </div>

    </div>
</div>

<script type="text/javascript">
    var myDate = new Date();
    //获取当前年
    var year = myDate.getFullYear();
    //获取当前月
    var month = myDate.getMonth() + 1;
    //获取当前日
    var date = myDate.getDate();
    var ho = myDate.getHours();       //获取当前小时数(0-23)
    var m = myDate.getMinutes();     //获取当前分钟数(0-59)
    var s = myDate.getSeconds();

    //alert();
    var num = "SZTS" + month + date + m + s;
    var h = "收到新的投诉，请及时了解情况，24小时内给投诉人进行电话回访，予以答复！";
    $(function () {
        reloadCode();
        /* $.ajax({
             url:'http://zhengqiao.ss.gov.cn/ssWechat/phoneResult.jsp',
             type: 'post',
             dataType: 'text',
             async: true,
             success:  function (data) {
                       if(data==""||data==null||data==undefined||data=="\r\n")
                                  {

                                    $.ajax({
                                     url:"http://zhengqiao.ss.gov.cn/wxfb/GetPhoneServlet",
                                    type: 'post',
                                dataType: 'text',
                               // async: true,
                                     success:  function (data) {
                                       document.getElementById('pho').value=data;
                                      }
                                     });
                                     }
                                  else
                 document.getElementById('pho').value=data;
              }
             });*/

    });


    function sub() {
        if (/* $("#selectwd").val()==""||$("#selectwd").val()==null||
					$("#selectlb").val()==""||$("#selectlb").val()==null|| */
        $("#title").val() == "" || $("#title").val() == null ||
        $("#content").val() == "" || $("#content").val() == null) {
            alert("填写内容不完整，请填写...");
            return
        }

        var vc = checkYanzhengma();
        if (!vc) {
            return;
        }
        //alert(!vc);

        //把数据提交到投诉管理后台

        toBackground();


        //return false;

    }

    function toBackground() {
        var myDate = new Date();
        //获取当前年
        var year = myDate.getFullYear();
        //获取当前月
        var month = myDate.getMonth() + 1;
        //获取当前日
        var date = myDate.getDate();
        var ho = myDate.getHours();       //获取当前小时数(0-23)
        var m = myDate.getMinutes();     //获取当前分钟数(0-59)
        var s = myDate.getSeconds();
        var type = $("#selectlb").val();
        //alert(type)
        var show = "是";
        var status = "已提交办理中";
        //var unit = $("#selectwd").val();
        var content = $("#content").val();
        var pho = $("#pho").val();
        var title = $("#title").val();
        var time = year + "-" + month + "-" + date + "    " + ho + ":" + m + ":" + s;
        //var time = year+"年"+month+"月"+date+"日"+ho+"时"+m+"分"+s+"秒";
        //alert(time);
        //var remark = "投诉";
        var name = $("#complaint_Name").val();
        if (name == "" || name == null) {
            name = "匿名用户";
        }

        $.ajax(
            {
                type: "post",
                url: "<%=basePath %>complaint!save",
                data: {
                    complaint_Content: content,
                    complaintTime: time, //时间后台生成，这个取消
                    complaint_Num: num,
                    complaint_Title: title,
                    complaint_Status: status,
                    complaint_Show: show
                },
                /* $.ajax(phone); */
                dataType: "text",
                success: function (data) {
                    alert("留言成功");
                    // location.reload();
                    location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChat.APPID+"&redirect_uri="+weChat.WeChatDNSURL+"complaint.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                    return;
                },
                error: function () {
                    alert("error");
                    /*   alert(data); */
                    return;
                }
            });
    }


    //验证码
    function reloadCode() {
        var image = document.getElementById("randomming");
        if (null != image) {
            image.src = "<%=basePath%>image.jsp?" + Math.random();
        }
    }


    //验证码校验
    function checkYanzhengma() {
        callServer();
        //alert(document.getElementById('codeResult').value);
        if (document.getElementById("codeResult").value.indexOf("false") != -1) {//ietester在此处不能取得验证码
            alert("验证码错误！");
            //document.getElementById("image").src="img.jsp";
            reloadCode();
            return false;
        } else {

            //alert(document.getElementById("loginForm"));
            ///document.getElementById("loginForm").onsubmit();
            return true;
        }
    }

    function callServer() {
        gHttpObj = getXMLHTTPObj();
        try {
            var code = document.getElementById("code").value;
            //alert("code--->"+code);
            if (gHttpObj) {
                //构造查询连接字符串
                var url = "<%=basePath %>chkImg.jsp?code=" + code;
                //打开连接
                gHttpObj.open("GET", url, false);
                //设置回调函数
                gHttpObj.onreadystatechange = retualServerValue;
                //发送请求
                gHttpObj.send(null);
            } else {
                alert("system error2");
            }
        } catch (e) {
            alert("system error1");
        }
    }


    function getXMLHTTPObj() {
        var XMLHTTPObj = null;
        try {
            XMLHTTPObj = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                XMLHTTPObj = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (sc) {
                XMLHTTPObj = null;
            }
        }

        if (!XMLHTTPObj && typeof XMLHttpRequest != "undefined") {
            XMLHTTPObj = new XMLHttpRequest();
        }
        return XMLHTTPObj;
    }

    //回调处理函数
    function retualServerValue() {
        try {
            if (gHttpObj.readyState == 1) {

            }
            if (gHttpObj.readyState == 2) {

            }
            if (gHttpObj.readyState == 3) {

            }
            if (gHttpObj.readyState == 4) {
                var response = gHttpObj.responseText;
                document.getElementById("codeResult").value = response;
            }
        } catch (e) {

        }
    }

    /* var phone = {
                        url:"http://zhengqiao.ss.gov.cn/sspjxt/Phone!findallnum.action?g_id=2",
                        type:'post',
                        dataType: 'text',
                        //async: true,
                        success: function(data){
                                             var datas = data.split(",");
                                            //alert(h,data);
                    for(var i=0;i<datas.length;i++){
                                              $.ajax({
                                              url:"http://zhengqiao.ss.gov.cn/ssWechat/sms!sendSmsmessage.action",
                                              type:'post',
                                              dataType: 'text',
                                              async:true,
                                               data : {
                                               phone:datas[i],
                                               txt:h
                                                },
                                              success: function(data){

                                              }
                                              });
                                             }

                        }
                        }; */
    /*
        function jiancha1(){

            if($("#selectwd").val()==""||$("#selectwd").val()==null&&$("#selectlb").val()==""||$("#selectlb").val()==null){
                alert("请选择投诉网点和类别");
            }else if($("#selectlb").val()==""||$("#selectlb").val()==null){
                alert("请选择投诉类别");
            }else if($("#selectwd").val()==""||$("#selectwd").val()==null){
                alert("请选择投诉网点");
            }

        }
        function jiancha2(){
            if($("#selectwd").val()==""||$("#selectwd").val()==null&&$("#selectlb").val()==""||$("#selectlb").val()==null){
                alert("请选择投诉网点和类别");
            }else if($("#selectlb").val()==""||$("#selectlb").val()==null){
                alert("请选择投诉类别");
            }else if($("#selectwd").val()==""||$("#selectwd").val()==null){
                alert("请选择投诉网点");
            }else if($("#title").val()==""||$("#title").val()==null){
                alert("请填写标题或内容摘要");
            }

        } */
</script>
</body>
</html>
