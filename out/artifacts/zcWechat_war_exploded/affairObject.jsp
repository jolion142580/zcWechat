<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.Map" %>

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
    <link rel="stylesheet" href="css/sm-extend.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/sm.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="lib/weui.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/guide-style.css" type="text/css"/>

    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="js/jquery-weui.js"></script>
    <script type="text/javascript" src="lib/zepto.js"></script>
    <script type="text/javascript" src="js/config.js"></script>

    <style type="text/css">
        .main_color {
            color: #FFFFFF;
        }

        .weui-cells {
            margin-top: 0;
        }

        .weui-search-bar {
            background-color: #0490E4;
        }

        .weui-search-bar__form {
            background-color: #0490E4;
        }

        .weui-search-bar__cancel-btn {
            color: #FFFFFF;
        }
    </style>
</head>


<body ontouchstart>
<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav"
                style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
            <a class="button button-link button-nav pull-left" href="javascript:;"
               onclick="javascript:WeixinJSBridge.call('closeWindow');"><span class="icon icon-left main_color"></span></a>
            <h1 class="title main_color">网上办事</h1>
        </header>

        <div class="content">

            <div class="weui-cells__title">请选择对象的类别</div>
			<div class="weui-cells weui-cells_checkbox">
		      
		      <s:iterator value="ssAffairObjectList">
		      <input type="hidden" name="iswrite" id="iswrite<s:property value="objindex"/>" value='<s:property value="iswrite"/>'>
		      <input type="hidden" name="iswrite" id="affairid" value='<s:property value="affairid"/>'>
		      
		      <s:if test ='(objname == "无分类" || objname=="无") && iswrite == "true"' >
		      	<script>
		      	//location.href='http://localhost:8083/zcWechat/onlineApply!isrelation?affairid=<s:property value="affairid"/>_onlineApply_'+<s:property value="objindex"/>;
		      	location.href='https://open.weixin.qq.com/connect/oauth2/authorize?appid='+weChat.APPID+'&redirect_uri='+weChat.WeChatDNSURL+'onlineApply!isrelation?affairid=<s:property value="affairid"/>_onlineApply_<s:property value="objindex"/>&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect';
		      	</script>
		      </s:if>
		      <s:if test ='(objname == "无分类" || objname=="无") && iswrite == null' >
		      	<script>
		      	//location.href='http://localhost:8083/zcWechat/onlineApply!isrelation?affairid=<s:property value="affairid"/>_affairMaterials_'+<s:property value="objindex"/>;
		      	location.href='https://open.weixin.qq.com/connect/oauth2/authorize?appid='+weChat.APPID+'&redirect_uri='+weChat.WeChatDNSURL+'onlineApply!isrelation?affairid=<s:property value="affairid"/>_affairMaterials_<s:property value="objindex"/>&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect';
		      	</script>
		      </s:if>
		      
			  <label class="weui-cell weui-check__label" for="<s:property value="objid"/>">
		        <div class="weui-cell__hd">
		          <input type="radio" class="weui-check" name="radio" id="<s:property value="objid"/>"  value="<s:property value="objindex"/>"  >
		          <i class="weui-icon-checked"></i>
		        </div>
		        <div class="weui-cell__bd">
		          <span><s:property value="objname"/></span>
		        </div>
		      </label>
		      </s:iterator>

		    </div>
		    
		    <div class="weui-btn-area">
                <a class="weui-btn weui-btn_primary" href="javascript:" onClick="commit();"
                   id="showTooltips" style="background-color: #911edb">下一步</a>
            </div>

        </div>
    </div>
</div>
</body>
<script type="text/javascript">

function commit(){
	var objindex = $("input[name='radio']:checked").val();
	var objid=$("input[name='radio']:checked").attr('id');
	var iswrite=$("#iswrite"+objindex).val();
	var affairid = $("#affairid").val();
	if(objindex==null){
		$.alert("请选择事项对象！");
		return;
	}
	if(iswrite=='true'){
	    //<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid='+weChat.APPID+'&redirect_uri='+weChat.WeChatDNSURL+'onlineApply!isrelation?affairid=<s:property value="ssAffairGuide.affairid" escape="false" />_onlineApply&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect" class="weui-tabbar__item">
		//location.href='http://localhost:8083/zcWechat/onlineApply!isrelation?affairid='+affairid+'_onlineApply_'+objindex;
		location.href='https://open.weixin.qq.com/connect/oauth2/authorize?appid='+weChat.APPID+'&redirect_uri='+weChat.WeChatDNSURL+'onlineApply!isrelation?affairid=<s:property value="affairid"/>_onlineApply_'+objindex+'&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect';
	}else{
	//<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid='+weChat.APPID+'&redirect_uri='+weChat.WeChatDNSURL+'onlineApply!isrelation?affairid=<s:property value="ssAffairGuide.affairid" escape="false" />_affairMaterials&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect" class="weui-tabbar__item">
		//location.href='http://localhost:8083/zcWechat/onlineApply!isrelation?affairid='+affairid+'_affairMaterials_'+objindex;
		location.href='https://open.weixin.qq.com/connect/oauth2/authorize?appid='+weChat.APPID+'&redirect_uri='+weChat.WeChatDNSURL+'onlineApply!isrelation?affairid=<s:property value="affairid"/>_affairMaterials_'+objindex+'&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect';
	}
}

</script>
</html>
