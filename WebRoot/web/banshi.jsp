<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1,maximum-scale=1.0,user-scalable=no">
	<meta http-equiv="X-UA-Compatible"  content="IE=edge,chrome=1">
	
	<script src="scripts/less.min.js" type="text/javascript"></script>

	<link rel="stylesheet" href="content/reflow.css" />

	<script src="scripts/jquery.min.js" type="text/javascript"></script>
	<script src="scripts/base.js" type="text/javascript"></script>

<script src="scripts/hammer.min.js" type="text/javascript"></script>

<style type="text/css">
.iff{
	
	z-index:10000;
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
.tabs {
    background: #FFF;
    border-bottom: #D9D9D9 solid 1px;
    position: relative;
}

.tabs li {
    width: 50%;
}
.tabs li:first-child a {
    border-radius: 6px 0 0 6px;
}
.tabs .cur a {
    background: #0295c9;
    border-color: #0295c9;
    color: #FFF;
}
.tabs li a {
    background: #EEE;
    border: #D9D9D9 solid 1px;
    border-right: none;
    display: block;
    height: 36px;
    line-height: 36px;
    display: block;
    text-align: center;
    font-size: 14px;
}
ul, li {
    list-style: none;
}
.left {
    float: left;
}
input::-webkit-input-placeholder {
  /* placeholder颜色  */
  color: white;
}
</style>

<title>张槎街道行政服务中心</title>
</head>
<body>

	<div class="header titlebar" style="height: 60px">
	
		<div class="logo" style="float: left;">
			<h1 class="title_app">办事指南查询</h1>
			<a href="web/Index.jsp" class="link_toScreensaver"><img src="images/ic_logo.png" alt="" style="width: 100%"></a>
		</div>

		<div class="searchInput" jslink="web/search.jsp" style="width: 20%;" style="float: left;">
			<a class="fullCover" style="width: 20%;" href="web/search.jsp"></a>
			<span class="ic ic_search"></span>
			<input type="text" placeholder="搜索事项名称…">
		</div>
		<%-- <a href="formviewer_all.html" class="link_allForm">
			<span class="ic ic_menu"></span>
			所有样表
		</a> --%>
	</div>
	
	<div class="contain" >
	
			<div class="tabs" >
				<ul>
					 <li class="left" id ='dep'><a href="ssBaseDicInfo!findAllByBaseDicTypeToWeb">部门事项</a></li>
					<li class=" left" id = 'left'><a href="ssBaseDicInfo!findAllByLeftItemToWeb">人生事项</a></li> 
				</ul>
			</div>
		<div class="content content_grid col6 left" data-bind="foreach:listData" style="overflow:auto;">
		
		<s:iterator value="ssBaseDicsList" >
			<div class="griditem item_classform" style="height: 23%;">
					
					<div class="merge">
						<span class="ic classify_icon"  style="background-image: url('<s:property value="iconPath" />');"></span>
						<p class="classify_name" data-bind="text:name"><s:property value="cname" /></p>
					</div>
					<a class="fullCover" href="web/affairs.jsp?baseDicId=<s:property value="baseDicId" />&baseDicType=<s:property value="baseDicType" />&baseDic=<s:property value="cname" />" data-bind="click:enter"></a>
			</div>
		</s:iterator>
			
		<div class="clear"></div>

		</div>
		<div class=" foot fixPosition width100 bottomPosition iff">
				<div align="center" style="line-height:25px;">
					<span style='font-size:12px;'>张槎街道行政服务中心授权使用&nbsp;&nbsp;南邮信息联合开发</span>
				</div>
		</div>
		
	</div>
	
</body>
</html>
<script src="scripts/knockout-3.4.0.js" type="text/javascript"></script>
<script src="ctrl/service.js" type="text/javascript"></script>
<!--<script src="ctrl/indexModel.js" type="text/javascript"></script>-->