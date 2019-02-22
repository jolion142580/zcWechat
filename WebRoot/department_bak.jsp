<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,height=device-height, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>部门列表</title>
<link href="bootstrap3/css/base.css" rel="stylesheet">
<link href="bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap3/css/bootstrap.pack.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>

</head>


<body style="background:#ececec;">
    <div class=" head fixPosition width100 topPosition">
    <div class=" hd rePosition">
      <span class="topLeft"><a href="#" onClick="WeixinJSBridge.call('closeWindow');"><img src="bootstrap3/images/back_ico.png" width="50"  ></a></span>
      <ul>
        <li class="navlist01">部门列表</li>
      </ul>
      <!--<span class="topRight"><img src="bootstrap3/images/back_ico.png" ></span>-->
    </div>
  </div> 
  <div class="contant">
    <div class="bconetnt">
      <form name="searchForm" method="post" action="">
        <div >
          <div style="margin:0 15px 15px 15px;">
            <label for="textfield"></label>
            <!-- <input type="text" name="affairname" class="form-control input-md fontslittle" id="affairname"  placeholder="请输入事项名称查询" >-->
          </div>
          <div style=" margin:15px; padding-bottom:15px;  background:#FFF;" >
            <!-- <div class="blist" align="center">
              <ul> -->
             
              <s:iterator value="ssBaseDicsList">
				<a href="ssAffairsGuideInfo!findByAffairCodeId?baseDicId=<s:property value="cname" />" class="list-group-item"><s:property value="cname" /></a>
			  </s:iterator>
               
              <!-- </ul>
            </div> -->
          </div>
        </div>
      </form>
    </div>
  </div>

<div class=" foot fixPosition width100 bottomPosition">
    <div align="center" style="line-height:25px;"><span style='font-size:12px;'>版权所有：三水区行政服务中心&nbsp;&nbsp;技术支持：南邮英科</span></div>    
  </div>


</body>


</html>