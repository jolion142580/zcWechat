<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%@ page import="com.gdyiko.zcwx.weixinUtils.WxJSSignUtil" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.gdyiko.zcwx.weixinUtils.TokenHepl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 

"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,height=device-height, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>办件查询</title>
<link href="bootstrap3/css/base.css" rel="stylesheet">
<link href="bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap3/css/bootstrap.pack.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
</head>

<%
	String token = TokenHepl.getaccessToken().getAccessToken();
	String jsapi_ticket = TokenHepl.jsapi_ticket;
	String url = WxJSSignUtil.getUrl();
	System.out.println("==url=="+url);
	System.out.println("jsapi_ticket=="+jsapi_ticket);
	Map map = WxJSSignUtil.sign(jsapi_ticket, url);
	/* String timestamp = map.get("timestamp").toString();
	String nonceStr=map.get("nonceStr").toString();
	String signature=map.get("signature").toString(); */
	
 	/* System.out.println("==timestamp=="+map.get("timestamp"));
	System.out.println("==nonceStr=="+map.get("nonceStr"));
	System.out.println("==signature=="+map.get("signature"));
	System.out.println("==jsapi_ticket=="+map.get("jsapi_ticket"));
	System.out.println("==url=="+map.get("url")); */ 
	
 %>
<script type="text/javascript">

	function commit(){
		//$("#accountform").submit();
		//$("#currCode").val();
		location.href="affairProgressResult.jsp?currCode="+$("#currCode").val();
	}
	
	$(function() {
          
            wx.config({
                debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId : 'wxaec78dd064e22ada', // 必填，公众号的唯一标识
                timestamp : '<%=map.get("timestamp") %>', // 必填，生成签名的时间戳
                nonceStr : '<%=map.get("nonceStr") %>', // 必填，生成签名的随机串
                signature : '<%=map.get("signature") %>',// 必填，签名，见附录1
                jsApiList : [ 'scanQRCode' ]
            // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
            });
        });
    function wxcloseWindow(){
    	wx.scanQRCode({
	    needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
	    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
	    success: function (res) {
		    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
		   // alert(result);
		  var rs = result.split(",");
		  //alert(rs[1]);
		   location.href="affairProgressResult.jsp?currCode="+rs[1];
			}
		});
    }
        
</script>

<body style="background:#ececec;">
		
  <div class=" head fixPosition width100 topPosition">
    <div class=" hd rePosition">
      <span class="topLeft"><a href="javascript:wx.closeWindow();"><img src="bootstrap3/images/back_ico.png" width="50"  ></a></span>
      <ul>
        <li class="navlist01">办件查询</li>
      </ul>
      <!--<span class="topRight"><img src="bootstrap3/images/back_ico.png" ></span>-->
    </div>
  </div> 
		
<!-- 个人办事信息表单 -->
  <div class="contant">
    <form id="accountform" action="" 
       method="POST" style="border:1px solid #cecece; padding:15px 15px 30px 15px; margin:15px; 

border-radius:10px; background:#FFF;" 
     class="fonts">
    <fieldset>
    <div class="form-group">
      <label class="col-md-3 control-label" style="padding-top:20px;" for="fullName">受理编号：

</label>  
      <div class="col-md-11">
      <input name="currCode" class="form-control input-md fontslittle" id="currCode" type="text" 

placeholder="">
      <span class="help-block"></span>  
      </div>
      </div>
      
      <div class="form-group">
        <span class="col-md-11" style="padding-bottom:20px;">提示：请使用受理回执中的查询码进行查询，

或者点击下方的扫码查询功能扫描二维码</span>
      </div>
      
    <div class="form-group">
      <label class="col-md-4 control-label" for="button1id"></label>
      <div class="col-md-8" >
    
        <button name="button1id" class="btn btn-success fonts" type="button" id="button1id" 

onClick="commit();return false;">查询</button>
        
        &nbsp;&nbsp;&nbsp;
        <button  class="btn btn-danger fonts"  id="button2id" type="button" onClick="wxcloseWindow();return false;">扫码查询</button>

      </div>
    
    </div>
    </fieldset>
    </form>
    <%-- AccessToken：<%=request.getAttribute("accessToken") %><br/>
  		JSApi_Ticket：<%=request.getAttribute("jsapi_ticket") %><br/>
  		timestamp：<%=request.getAttribute("timestamp") %><br/>
  		nonceStr：<%=request.getAttribute("nonceStr") %><br/>
  		signature：<%=request.getAttribute("signature") %><br/>  --%>
  </div>
  <div class=" foot fixPosition width100 bottomPosition">
    <div align="center" style="line-height:25px;"><span style='font-size:12px;'>版权所有：三水区行政

服务中心&nbsp;&nbsp;技术支持：南邮英科</span></div>    
  </div>
</body>
</html>