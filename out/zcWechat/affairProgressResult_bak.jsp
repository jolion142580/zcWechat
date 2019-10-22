<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%
String path = request.getContextPath();     
//获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量     
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";   
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,height=device-height, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>办件查询结果</title>
<link href="bootstrap3/css/base.css" rel="stylesheet">
<link href="bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap3/css/bootstrap.pack.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
</head>
<%
String currCode =request.getParameter("currCode");
 %>
<script type="text/javascript">
$(function(){  

	$.post("<%=basePath%>affairProgress!findByAffairCode",{ currCode: "<%=currCode%>"} ,function(result) {
			  //alert(result);
			if(result.Result==1){	
				$("#fieldsetContent").css('display','block'); 			
				$("#accountform").css('display','block'); 			
				$("#currCode").html(isEqualsNull(result.Data.AFFAIRCODE));
				$("#affairName").html(isEqualsNull(result.Data.AFFAIRNAME));
				$("#affairCode").html(isEqualsNull(result.Data.AFFAIRCODE));
				$("#personName").html(isEqualsNull(result.Data.PERSONNAME));
				$("#personCardId").html(isEqualsNull(result.Data.PERSONCARDID));
				$("#personAddress").html(isEqualsNull(result.Data.PERSONADDRESS));
				$("#personLiveAddress").html(isEqualsNull(result.Data.PERSONLIVEADDRESS));
				if(result.Data.ISEND==0)$("#isEnd").html("已完成");
				else $("#isEnd").html("未完成");
				$("#nextFlow").html(isEqualsNull(result.Data.NEXTFLOW));
				
				var steps="";
				 $.each(result.Data.AFFAIRRECORDS, function(k, v) {
       
                	  //alert(v.RECORDTIME);
                	steps="<tr>";
    				steps+="<td>"+v.OPERATORNAME+"</td>";	
    				steps+="<td>"+v.RECORDSTATUS+"</td>";	
    				steps+="<td>"+v.RECORDRESULT+"</td>";	
    				steps+="<td>"+v.RECORDTIME+"</td>";	
                    steps+="</tr>";
              	});
              	
              	
              	$("#step").after(steps);
				
			}
			if(result.Result==0){
				 $("#fieldsetContent").empty();
				 $("#fieldsetContent").css('display','none'); 
				 
    			 var t="<div align=\"center\" style=\" margin:15px; padding:15px 0 200px 0; background:#fff;\">"+result.Msg+"</div>";
				 $("#contant").append(t);
			}
        }, "json");

});

function isEqualsNull(content){
	return content==null?"":content;
}
</script>

<body style="background:#ececec;">
 <div class=" head fixPosition width100 topPosition">
    <div class=" hd rePosition">
      <span class="topLeft"><a href=”#” onClick="javascript:history.back(-1);return false;"><img src="bootstrap3/images/back_ico.png" width="50"  ></a></span>
      <ul>
        <li class="navlist01">查询结果</li>
      </ul>
      <!--<span class="topRight"><img src="bootstrap3/images/back_ico.png" ></span>-->
    </div>
  </div> 

<!-- 预约办事表单 -->
  <div id="contant" class="contant">
    <form id="accountform" action=""  method="post" style="border-left:10px solid #0d9dd2;border-right:1px solid #cecece; border-top:1px solid #cecece; border-bottom:1px solid #cecece; padding-bottom:30px; margin:15px; border-radius:10px; background:#FFF;display:none" class="fonts"> 
    <input type="hidden" name="p_backUrl" value="">
    <input type="hidden" name="smstoken" value="dc8a4cae19ec498eb2b8691d896b7a78">
    <fieldset id="fieldsetContent" style="display:none">
      <table  class="frm width100">
        <tr colspan="2">
          <th>受理编号</th>
          <td id="currCode"></td>
        </tr>
        <tr >
          <th>事项名称</th>
          <td id="affairName"></td>            
        </tr>
        <tr >
          <th>事项编码</th>
          <td id="affairCode"></td>            
        </tr>
        <tr colspan="2">
          <th>申请人</th>
          <td id="personName"></td>            
        </tr>
        <tr colspan="2">
          <th>身份证号码</th>
          <td id="personCardId"></td>            
        </tr>
        
        <tr colspan="2">
          <th>是否完成</th>
          <td id="isEnd"></td>            
        </tr>
        <tr colspan="2">
          <th>下一环节</th>
          <td id="nextFlow"></td>            
        </tr>
        <tr >
          <th colspan="4">审批过程</th>
        </tr>
      </table>
      <table  class="frm1 width100">
        <tr id="step">
          <th>审批人</th>
          <th>审批状态</th>
          <th>审批结果</th>
          <th>审批时间</th>
        </tr>
         <!-- <tr>
          <td>邓劲怡</th>
          <td>审核中</th>
          <td>--</th>
          <td>2016-12-28</th>
        </tr> -->
      </table>
    </fieldset>
     </form>
  </div>
</body>
</html>