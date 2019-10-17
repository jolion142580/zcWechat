var InterValObj; //timer变量，控制时间  
var count = 120; //间隔函数，1秒执行  
var curCount;//当前剩余秒数  
var code = ""; //验证码  
var codeLength = 6;//验证码长度  
var phonecount=5; //手机限制次数
var ipcount=10;  //IP限制次数
  
function sendMessage() {  
    curCount = count;  
    var Phone = $("#phone").val(); 
    var check = yz();
   if(check==true){
	   
    if (Phone != "") {
      /*	code="";
            // 产生验证码  
            for ( var i = 0; i < codeLength; i++) {  
                code += parseInt(Math.random() * 9).toString();  
            }
            // 设置button效果，开始计时  
            
            alert(code);*/
            $.post('smsinfo!saveSmsinfo',
     			   {smsmobile:Phone,
     		   		ip: returnCitySN["cip"],
     		   		code:'',
     		   		phonecount:phonecount,
     		   		ipcount:ipcount,
     		   		random_num: $("#random_num").val()
     		   		},
     			   function(data){
     				   var da = eval(data); 
     				   if(da.flag=='0'){
     					$("#random_sms").val("");
     					  reloadCode();
     					  alert(da.errorMsg);
     					  return;
     				   }else if(da.flag=='1'){
     					   //保存后发送
     					  //alert(da.flag);
     					 $("#btnSendCode").attr("disabled", "true"); 
     		            $("#btnSendCode").val( + curCount + "s 后获取");  
     					  InterValObj = window.setInterval(SetRemainTime, 1000); // 启动计时器，1秒执行一次
     					  $.post('sms!sendSmsmessage',{code:'',phone:Phone,txt:''},
     							  function(data){
     						  var da = eval(data);
     						  alert(da.Msg);
     						  },'json');
     				   }
     			   },'json');

    }
   }
}  
  
//timer处理函数  
function SetRemainTime() {  
    if (curCount == 0) {                  
        window.clearInterval(InterValObj);// 停止计时器  
        $("#btnSendCode").removeAttr("disabled");// 启用按钮  
        $("#btnSendCode").val("重新发送");  
        code = ""; // 清除验证码。如果不清除，过时间后，输入收到的验证码依然有效  
    }else {  
    	 code = ""; // 清除验证码。如果不清除，过时间后，输入收到的验证码依然有效  
        curCount--;  
        $("#btnSendCode").val(+ curCount + "s 后获取");  
    }  
}  
