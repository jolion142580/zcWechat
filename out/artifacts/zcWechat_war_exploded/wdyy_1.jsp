<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String booking_no = request.getParameter("no");
	//System.out.println("-----------"+booking_no);
	String id_card = request.getParameter("idcard");
	String types = request.getParameter("type");
	//System.out.println("---type----"+types);
%>
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

<script type="text/javascript" src="js/config.js"></script>
<style type="text/css">
         .main_color{
         color:#FFFFFF;}
.weui-navbar + .weui-tab__bd  {
    padding-top: 6rem;
}
p {
    margin: 0;
}
.content{
background-color:#FFF;
}
.re_num{
font-size:2rem;
color:#8fc320;
}
.weui-bar__item--on .re_num {
    color: #f39801;
}
.weui-navbar__item.weui-bar__item--on {
background-image: url(icon/selected.png);
    background-repeat: no-repeat;
    background-size: 100%;
    background-position-y: 100%;
	background-color:#FFF;
}
.weui-navbar__item{
background-color:#FFF;
}
.weui-navbar__item:after{
border-right:0px;
}
.placeholder{
color:#888;
}

.rtext{
font-size:14px;
color:#555;
}
.weui-cells{
margin-top:0px;
}
/*我的预约*/
.frm{border-left:1px solid #cecece;border-collapse:collapse;}
.frm tr th{color:#000;font-weight:normal;text-align:left;background:#fff;padding:5px 5px 5px 10px;border-left:1px solid #cecece;border-bottom:1px solid #cecece;line-height:30px; width:15%;}
.frm tr td{padding:5px;border-left:1px solid #cecece;border-bottom:1px solid #cecece; text-align:left; line-height:30px; width:27%;}

/*宽度*/
.width100{
	width:100%;
}
</style>
</head>
<body>
	<div class="page-group">
		<div class="page page-current">
			<header class="bar bar-nav"
	      style="background-size: contain;background-repeat: repeat-x;background-color:#0490E4">
	      <!-- <img src="images/bar_nav_left_purple.png" style="position: absolute;left: -0.5rem;height: 2.2rem;" />  -->
	      <a class="button button-link button-nav pull-left" href="javascript:;" onclick="WeixinJSBridge.call('closeWindow');">
	        <span class="icon icon-left main_color"></span>
	      </a>
	      <h1 class="title" style="color:#FFFFFF">预约信息</h1></header>

			<!-- <div class="header-bar" style="background-color: #911edb">
				<div>PERSONAL INFORMATION REGISTRATION</div>
			</div> -->
			
			<div class="content" >
   				 <form id="accountform" action="" method="post" 
					 style="border-right:2px solid #cecece; border-top:1px solid #cecece; border-bottom:1px solid #cecece; padding-bottom:30px; margin:15px; border-radius:10px; background:#FFF;"
					class="fonts">
    				<input type="hidden" id="ystime" value="" />
    				
				      <table  class="frm width100">
				        <tr>
				          <th>业务编码</th>
				          <td id="stype">--</td>
				        </tr>
				        <tr >
				          <th>事项名称</th>
				          <td id="sname">--</td>            
				        </tr>
				        <tr>
				          <th>用户姓名</th>
				          <td id="name">--</td>            
				        </tr>
				        <tr>
				          <th>联系方式</th>
				          <td id="phone">--</td>            
				        </tr>
				        <tr>
				          <th>预约流水号</th>
				          <td id="no">--</td>            
				        </tr>
				        <tr>
				          <th>身份证号码</th>
				          <td id="idcard">--</td>            
				        </tr>
				        <tr>
				          <th>预约日期</th>
				          <td id="ydate">--</td>            
				        </tr>
				        <tr>
				          <th>预约时段</th>
				          <td id="time">--</td>            
				        </tr>
				        <!-- <tr >
				          <th colspan="4">处理过程</th>
				        </tr> -->
				      </table>
				   
    			 </form>
    			 <div id="cancel" align="center" style=" margin:0px;padding-bottom:45px;display:none">
					<input onclick="cancelYuYue();" type="button" name="button" style="border-radius:8px; border:none; background:#06F; width:120px; height:35px; line-height:35px; color:#fff;" id="button" value="取消预约">
				</div>
  			</div>
		</div>
	</div>

</body>

<script type="text/javascript">
$(function(){
	singleYuYueJson();
});

var no="<%=booking_no%>";
var idcard="<%=id_card%>";
var type="<%=types%>";
	function singleYuYueJson() {

		if (no == null || idcard == null || no == '' || idcard == '') {
			return;
		}
		$
				.ajax({
					url : 'YuYues!singleYuYueJson',
					data : {
						no : no,
						idcard : idcard
					},
					dataType : 'JSON',
					type : 'POST',
					success : function(r) {
						//查询不成,跳出方法
						if (r.r_code != 200) {
							alert(r.msg);
							return;
						}

						$("#stype").html(r.data[0].appointment_lowertype);
						$("#sname").html(r.data[0].appointment_typename);
						$("#name").html(r.data[0].people_name);
						$("#phone").html(r.data[0].people_mobile);
						$("#no").html(r.data[0].v_appointment_id);
						$("#idcard").html(r.data[0].people_cardid);
						$("#ydate").html(r.data[0].d_appointment_date);
						/*var time = r.yuyue.s_time + "-" + r.yuyue.e_time;*/
						var time =r.data[0].v_time.substring(11);

						$("#time").html(time);
//						$("#ystime").val(r.yuyue.s_time);

						//alert(r.yuyue.y_date);
						//alert(getNowFormatDate());
//						var nowTime = getNowFormatDate();
						//alert(r.yuyue.y_date==nowTime);
//						var s_time = r.yuyue.y_date + " " + r.yuyue.s_time;
						var s_time =r.data[0].d_appointment_date+ " "+time.substring(0,5);
                        $("#ystime").val(time.substring(0,5));
						s_time = new Date(Date.parse(s_time.replace(/-/g, "/")));

						var date1 = s_time.getTime();
						//alert(date1);

						var timestamp = Date.parse(new Date());
						//alert(timestamp);
						//&& date1>timestamp
						if (type == "remove" && r.data[0].c_flag == 0
								&& date1 > timestamp) {
							$("#cancel").css("display", "block");
						}
						//r.yuyue.id_card;

					},
					errot : function(e) {

					}
				});
	}

	function cancelYuYue() {
		if (no == null || idcard == null || no == '' || idcard == '') {
			return;
		}
		var ydate = $('#ydate').text();
		var ystime = $("#ystime").val();

		var r = confirm("确定要取消该预约吗？");
		if (r) {
			$
					.ajax({
						url : 'YuYues!cancelYuYue',
						data : {
							no : no,
							/*idcard : idcard,*/
							ydate : ydate,
							ystime : ystime
						},
						type : "POST",
						dataType : "JSON",
						success : function(r) {
							alert(r.r_msg);
//							window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx481172387f6fb7c5&redirect_uri=http://ymswx.pjq.gov.cn/pjWechat/wdyy_2.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
							window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChat.APPID+"&redirect_uri="+weChat.WeChatDNSURL+"newwdyy_2.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect"
						},
						error : function(e) {

						}
					});
		} else {

		}

	}

	function getNowFormatDate() {
		var day = new Date();
		var Year = 0;
		var Month = 0;
		var Day = 0;
		var CurrentDate = "";
		//初始化时间 
		//Year       = day.getYear();//有火狐下2008年显示108的bug 
		Year = day.getFullYear();//ie火狐下都可以 
		Month = day.getMonth() + 1;
		Day = day.getDate();
		CurrentDate += Year + "-";
		if (Month >= 10) {
			CurrentDate += Month + "-";
		} else {
			CurrentDate += "0" + Month + "-";
		}
		if (Day >= 10) {
			CurrentDate += Day;
		} else {
			CurrentDate += "0" + Day;
		}

		return CurrentDate;
	}
</script>
</html>