<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,height=device-height, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>事项列表</title>
<link href="bootstrap3/css/base.css" rel="stylesheet">
<link href="bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap3/css/bootstrap.pack.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
</head>
<body>

<script type="text/javascript">
function affairsSerach(){

	$.post("ssAffairsGuideInfo!findByAffairName",{ affairname: $("#affairname").val()} ,function(data) {
			  //alert(data);
			  $("#affairsItem").empty();
			 
			  var content="";
			  if(data.length<=0)content="<li class=\"list-group-item\">没有找到相关的事项！</li>";
        	   $.each(data, function(k, v) {
                  //$.each(v, function(kk, vv) {
                	  //alert(v.affairname);
                	  
                	  var subAffairName="";
                	  //if(v.affairname.length>18){
                	  //	subAffairName=v.affairname.substring(0,18)+"...";
                	  //}else{
                	  	subAffairName=v.affairname;
                	  //}
                	  content+="<a href=ssAffairsGuideInfo!findByAffairId?affairid="+v.affairid+"  class=\"list-group-item\">"+subAffairName+"</a>";
                      
                  //});
              });
              $("#affairsItem").append(content);
        }, "json");

}
</script>

<body style="background:#ececec;">
    <div class=" head fixPosition width100 topPosition">
    <div class=" hd rePosition">
      <span class="topLeft"><a href=”#” onClick="javascript:history.back(-1);return false;"><img src="bootstrap3/images/back_ico.png" width="50"  ></a></span>
      <ul>
        <li class="navlist01">事项列表</li>
      </ul>
      <!--<span class="topRight"><img src="bootstrap3/images/back_ico.png" ></span>-->
    </div>
  </div> 
  <div class="contant">
    <div class="bconetnt">
      <form name="form1" method="post" action="">
        <div >
          <div style="margin:0 15px 15px 15px;">
            <label for="textfield"></label>
            <input type="text" name="affairname" class="form-control input-md fontslittle" id="affairname" value="" onkeyup="affairsSerach();"  oninput="affairsSerach();"  placeholder="请输入事项名称查询">
          </div>
          <div style=" margin:15px; padding-bottom:15px;  background:#FFF;" id="affairsItem" >
            <!-- <div class="blist" align="center">
              <ul id="affairsItem"> -->
              
              <s:iterator value="ssAffairsList">
				<a href="ssAffairsGuideInfo!findByAffairId?affairid=<s:property value="affairid" /> " 
				 class="list-group-item" title="<s:property value="affairname"/>">
					<s:property value="affairname"/>				
				</a>
			  </s:iterator> 
                
             <!--  </ul>
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