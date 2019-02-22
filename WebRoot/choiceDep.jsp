<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<!-- Mobile Devices Support @begin -->
<meta content="application/xhtml+xml;charset=GBK" http-equiv="Content-Type">
<meta content="no-cache,must-revalidate" http-equiv="Cache-Control">
<meta content="no-cache" http-equiv="pragma">
<meta content="0" http-equiv="expires">
<meta content="telephone=no, address=no" name="format-detection">
<meta name="apple-mobile-web-app-capable" content="yes" /> <!-- apple devices fullscreen -->
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<!-- Mobile Devices Support @end -->
<title>预约办事</title>
<link rel="stylesheet" href="yms/window/weiXinAppointment/css/common.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="yms/window/weiXinAppointment/js/common.js"></script>
<script type="text/javascript" src="yms/window/onlineAppointment/js/id-num-validator.js"></script>
<script type="text/javascript" src="<%=basePath %>js/layer/layer.js"></script>

</head>

<body >

	<div >
		<!-- <input type="hidden" name="street" id="street" value=""> -->
   		<input type="hidden" name="twoLevelAffairId" id="twoLevelAffairId" value="">
   		<input type="hidden" name="serverTypeId" id="serverTypeId" value="">
   		<input type="hidden" name="serverTypeNo" id="serverTypeNo" value="">
	
		<div class="lobby" id="lobbyM">
			<%-- <h3>请选择预约行政服务中心</h3>

                 	<h3><strong>蓬江</strong>镇、街办事大厅</h3>
                	<div id="dep" class="area-main clear">
                					
                	</div> --%>
                 
                 
                 <h3>请选择办理业务</h3>

                 	<div class="lobby-main dep-main">
               		<s:iterator value="serverTypeList">
	               		<li>
	               			<a href="javascript:selectMoreType('<s:property value="id" />','<s:property value="no" />')"><h4><s:property value="name" /></h4></a>
	               			<div class="business-list">
		                			<ul id="<s:property value="id" />">
			               				<!-- <li>
											<a href=""><h5>计生</h5></a>
			               				</li> -->
		               				</ul>
	                			</div>
	               		</li>
	               		
               		</s:iterator>
               		<div style="font-size: 13px;text-align: center;margin-top:20px;">
							<a href="javascript:showMaterial();"><p style="color:orange">我该到哪个服务中心办理户籍业务</p></a>
							
			         </div>
              </div>	


           
		</div>
		
		<div id="divImg" style="text-align:center;display:none;">
			<img id="imgMaterial" src="" style="width:90%;height:100%;margin-top: 50px;"></img>
		</div>
		
	</div>
</body>
<script type="text/javascript" src="http://pv.sohu.com/cityjson?ie=utf-8" charset="gb2312"></script>
<script type="text/javascript">

function selectMoreType(id,no){
		$("#serverTypeNo").val(no);
		$("#serverTypeId").val(id);
		/* if(id=="455"){
			alert("2017年11月20日至2017年12月15日期间暂停办理出生登记、户口迁移等业务，居住证、身份证业务以及各项入户申请照常受理。");
		} */
		$.ajax({
			url:"<%=basePath%>BusinessWeight!findTwoLevelAffair",
			async: false, 
			type:"post",
			data:{"serverTypeId":id},
			dataType:"json",
			success: function (data) {
			var htmlContext = "";
				$.each(data, function(k, v) {
      				
	              var isnor =true;
				if(v.businessName.indexOf("非通办")!=-1){
					isnor=false;
					//alert(v.businessName);
				}
	                htmlContext += "<li >";
					htmlContext += "<a href=javascript:selectType('"+v.id+"','"+v.businessCode+"','"+isnor+"')><h5>"+v.businessName+"</h5></a>";
					htmlContext += "</li>";
					
            	});
                	
            	
            	$("#"+id).html(htmlContext);
				
	            
	        },
	        error: function (request) {
	            alert("数据加载错误");//请求失败
	        }
		});
	}
	
	function selectType(twoLevelAffairId,businessCode,isnor){

		var serverTypeId = $("#serverTypeId").val();
		var serverTypeNo = $("#serverTypeNo").val();
		if(businessCode!=""){
			serverTypeNo=businessCode;
		}
		//alert(isnor);
		if(isnor==false||isnor=='false'){
			alert("该业务为非通办业务,请选择对的行政行知服务中心进行办理！");
		}
		//alert(twoLevelAffair);
		//alert($("#street").val());
		//alert($("#businessType").val());
		
		var paramVal=serverTypeId+"_"+serverTypeNo+"_"+twoLevelAffairId;
		//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx481172387f6fb7c5&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/YuYues!yuYues&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect
		//location.href="<%=basePath%>YuYues!yuYues?paramVal="+paramVal;
		//alert("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx481172387f6fb7c5&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/YuYues!yuYues?wordBookId="+wordBookId+"&twoLevelAffairId="+twoLevelAffairId+"&businessType="+businessType+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
		location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxeae24884fc77e6ff&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/YuYues!yuYues?paramVal="+paramVal+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		
		
	}
	
	function showMaterial(){
			$("#divImg").removeClass("hidden");
			$("#imgMaterial").attr("src","yms/window/weiXinAppointment/images/policeSite.png");
			//捕获页
			layer.open({
			  type: 1,
			  shade: [0.8,'#333'],
			  shadeClose:true,
			  title: false, //不显示标题
				offset:'5%',
			  area: ['90%', '90%'], //宽高
			  content: $("#divImg"),
			  cancel: function(index){
			    layer.close(index);
			    $("#divImg").addClass("hidden");
			  }
			});
	    }



</script>

</html>
