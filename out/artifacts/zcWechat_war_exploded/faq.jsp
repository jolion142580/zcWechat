<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%@page import="com.gdyiko.zcwx.action.FAQAction" %>
<%
//FAQAction faq = new FAQAction();
String openid =  FAQAction.openid;

 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,height=device-height, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>疑问&办法</title>
<link href="bootstrap3/css/base.css" rel="stylesheet">
<link href="bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap3/css/bootstrap.pack.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/jweixin-1.0.0.js"></script>
</head>
<body>

<script type="text/javascript">
function faqSerach(){

	$.post("FAQ!getAnswer",{ problem: $("#problems").val()} ,function(data) {

			  $("#problemsItem").empty();
			 
			  var content='';
			  if(data.length<=0)content="<li>没有找到相关的事项！</li>";
        	   $.each(data, function(k, v) {
                	  var problems="";
                	  if(v.problem.length>20){
                	  	problems=v.problem.substring(0,20)+"...";
                	  }else{
                	  	problems=v.problem;
                	  }
                	  
	content+="<li><a href=javascript:void(0) onclick=\"sendmsg('" + problems + "','" + v.answer + "')\" >"+problems+"</a></li>";
			});
             $("#problemsItem").append(content);
        }, "json");

}

function sendmsg(pro,msg){
	$.ajax({
			url :'sendMessage!send',
			data:{Title:pro,
			Msg:msg,
			openid:'<%=openid %>' },
			type : "post",
			async : false,
			dataType : "json",
			error : function(XMLHttpRequest,textStatus,errorThrown) {
			},
			success : function(json, state) {
			
			if(json.errcode==0)
				WeixinJSBridge.call('closeWindow');
			}	
	});
}

</script>

<body style="background:#ececec;">
    <div class=" head fixPosition width100 topPosition">
    <div class=" hd rePosition">
      <span class="topLeft"><a href=”#” onClick="javascript:history.back(-1);"><img src="bootstrap3/images/back_ico.png" width="50"  ></a></span>
      <ul>
        <li class="navlist01">疑问&办法</li>
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
            <input type="text" name="problems" class="form-control input-md fontslittle" id="problems" value="" onkeyup="faqSerach();"  placeholder="请输入事项名称查询">
          </div>
          <div style=" margin:15px; padding-bottom:15px;  background:#FFF;" >
            <div class="blist" align="center">
              <ul id="problemsItem">
              <s:iterator value="problemsItem">
				<li>
				<a href="javascript:void(0)" onclick="sendmsg('<s:property value="problem"/>','<s:property value="answer"/>');" >
					  <s:if test="problem.length()>20">
                      <s:property value="problem.substring(0,20)"/>...
                      </s:if>
                      <s:else> 
                          <s:property value="problem"/>
                       </s:else> 
                 </a>
				</li>
			  </s:iterator> 
                
              </ul>
            </div>
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