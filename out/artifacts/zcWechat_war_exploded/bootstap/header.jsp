<%@ page contentType="text/html; charset=UTF-8"%>
	<div id="mws-header" class="clearfix">
    
    	<!-- Logo Wrapper -->

                	<!-- Logo Wrapper -->
    	<div id="mws-logo-container">
        	<div id="mws-logo-wrap" style="width:500px">
            	<img src="images/logo.png"  alt="狮山行政服务中心" /> 
			</div>
        </div>	
 <!-- User Area Wrapper -->
        <div id="mws-user-tools" class="clearfix">
        
            
            <!-- User Functions -->
            <div id="mws-user-info" class="mws-inset">

                <div id="mws-user-functions">
					
                    <div id="mws-username" style="width:550px;font-size:16px">
						<SPAN >你好，今天是</SPAN>
						<SPAN id="time"></SPAN>
                        <SPAN id="tq"></SPAN>
                    </div>
                </div>
            </div>
            <!-- End User Functions -->
            
        </div>
        
    </div>
	<SCRIPT type="text/javascript">
	var check_second=60;
function startTime() {
    var week = new Array('星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六');
    var today = new Date();
	var h = today.getHours();
	var m = today.getMinutes();
	var s = today.getSeconds();
	var ms = today.getUTCMilliseconds();
    // add a zero in front of numbers<10
    m = checkTime(m);
	s = checkTime(s);
	document.getElementById('time').innerHTML = today.getFullYear() + '年' + (today.getMonth() + 1) + '月' + today.getDate() + '日' + h + ":" + m + ":" + s;
    t = setTimeout('startTime()', 1000);
}
function checkTime(i) {
    if (i < 10) {
        i = "0" + i
    }
    return i
}

	function loadTq(){
	$.ajax({
		  url: "tq.jsp?v="+Math.random(),
		  cache: false,
		  success: function(json){
			 ////json
			 eval("var weatherJson = "+json);
			 ////alert(json);
			 var temp1 = weatherJson.retData.h_tmp;
			 var temp2 = weatherJson.retData.l_tmp;
			 var city = weatherJson.retData.city;
			 var weather = weatherJson.retData.weather;
			 var tempStr = "&nbsp;&nbsp;"+city+" "+weather+" "+temp2+"至"+temp1+"摄氏度";
			 $("#tq").html(tempStr);
		  }
	});

	}

	function  checkIsInit(){
		check_second = check_second -1;
		var url =window.location.href;
		var filename = url.substring(url.lastIndexOf("/")+1,url.length);
		if(check_second==5){
			if(filename!='dayTotal.jsp'){
				///alert(filename);
				$.jGrowl("如不操作，系统5秒后返回首页！", {header: "温馨提示：", position: "bottom-right"});
			}
		}
		if(check_second<0){
				if(filename!='dayTotal.jsp'){
					window.location.href="dayTotal.jsp";
				}
			///
		}
		
	}
	setInterval("checkIsInit();",1000);
	loadTq();
	startTime();
	$(function() {
		$("*").bind("click",function(){
			///alert("all Click");
			check_second=60;
		});
		
	});
	</SCRIPT>