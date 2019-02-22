<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="generator"
	content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39" />
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>蓬江邑门式</title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1" />
<link rel="shortcut icon" href="/favicon.ico" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link rel="stylesheet" href="css/sm-extend.min.css" type="text/css" />
<link rel="stylesheet" href="css/sm.min.css" type="text/css" />
<link rel="stylesheet" href="css/jquery-weui.min.css" type="text/css" />

<link rel="stylesheet" href="lib/weui.min.css" type="text/css" />
<link rel="stylesheet" href="css/guide-style.css" type="text/css" />

<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/jquery-weui.js"></script>

<style type="text/css">
.main_color {
	color: #35af98;
}

.weui-cells {
	margin-top: 0;
}

.weui-search-bar {
	background-color: #3ec4ab;
}

.weui-search-bar__form {
	background-color: #3ec4ab;
}

.weui-search-bar__cancel-btn {
	color: #FFF;
}
</style>
</head>


<script type="text/javascript">
function affairsSerach(){

	$.post("ssAffairsInfo!findByAffairName",{ affairname: $("#affairname").val()} ,function(data) {
			  //alert(data);
			  $("#affairsItem").empty();
			 
			  var content="";
			  if(data.length<=0)content="<a href=\"javascript:;\"  class=\"weui-cell weui-cell_access\"> <div class=\"weui-cell__hd\" ><img src=\"icon/arrow_green.png\" alt=\"\" style=\"width:10px;margin-right:5px;display:block\"></div> <div class=\"weui-cell__bd\"> 没有找到相关的事项！ </div> <div class=\"weui-cell__ft\"></div></a>";
        	   $.each(data, function(k, v) {
                  //$.each(v, function(kk, vv) {
                	  //alert(v.affairname);
                	  
                	  var subAffairName="";
                	  //if(v.affairname.length>18){
                	  //	subAffairName=v.affairname.substring(0,18)+"...";
                	  //}else{
                	  	subAffairName=v.affairname;
                	  //}
                	  content+="<a href=ssAffairsGuideInfo!findByAffairId?affairid="+v.affairid+"  class=\"weui-cell weui-cell_access\"> <div class=\"weui-cell__hd\" ><img src=\"icon/arrow_green.png\" alt=\"\" style=\"width:10px;margin-right:5px;display:block\"></div> <div class=\"weui-cell__bd\">"+subAffairName+"</div> <div class=\"weui-cell__ft\"></div></a>";
                      
                  //});
              });
              $("#affairsItem").append(content);
        }, "json");

}
</script>
<body ontouchstart>
	<div class="page-group">
		<div class="page page-current">
			<header class="bar bar-nav"
        style="background-size: contain;background-repeat: repeat-x;background-color:#FFF">
        <!-- <img src="images/bar_nav_left_green.png" style="position: absolute;left: -0.5rem;height: 2.2rem;" />  -->
        <a class="button button-link button-nav pull-left" href="javascript:;" onclick="WeixinJSBridge.call('closeWindow');"> 
        <span class="icon icon-left main_color"></span>
        </a> 
        <h1 class="title main_color">办事指南</h1></header>
			<div class="header-bar" style="background-color: #35af98">WORK GUIDE</div>
			<div class="content">
			
				<div class="weui-search-bar" id="searchBar">
					<form class="weui-search-bar__form" name="form1" method="post" action="">
						<div class="weui-search-bar__box">
							<i class="weui-icon-search"></i> 
							
							<input type="search"  name="affairname" class="weui-search-bar__input" id="affairname" value="" onkeyup="affairsSerach();"  oninput="affairsSerach();"  placeholder="搜索"> 
							<a href="javascript:"
								class="weui-icon-clear" id="searchClear"></a>
						</div>
						<label class="weui-search-bar__label" id="searchText"
							style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1);">
							<i class="weui-icon-search"></i> <span>搜索</span> </label>
							</form>
							<a href="javascript:" class="weui-search-bar__cancel-btn"
						id="searchCancel">取消</a>
					</div>
					  
				
				<div class="weui-cells" id="affairsItem">
				<s:iterator value="ssAffairsList">
				<a href="ssAffairsGuideInfo!findByAffairId?affairid=<s:property value="affairid" /> " 
				class="weui-cell weui-cell_access" title="<s:property value="affairname"/>">
				
				<span class="weui-cell__hd">
							<img src="icon/arrow_green.png" alt=""
								style="width:10px;margin-right:5px;display:block">
						</span>
						<span class="weui-cell__bd">
								<s:property value="affairname"/>
						</span>
						<span class="weui-cell__ft"></span> 
				
				
				</a>
				  </s:iterator> 

				</div>
				<div class=" foot fixPosition width100 bottomPosition iff">
					<div align="center" style="line-height:25px;">
						<span style='font-size:12px;'>版权所有：蓬江邑门式&nbsp;&nbsp;技术支持：南邮英科</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="lib/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="lib/fastclick.js"></script>
	<script>
		$(function() {
			FastClick.attach(document.body);
		});
	</script>

</body>
</html>
