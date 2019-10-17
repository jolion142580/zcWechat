/*
function getExplorerInfo() {
    var explorer = window.navigator.userAgent.toLowerCase();
    //ie
    if (explorer.indexOf("msie") >= 0) {
        var ver = explorer.match(/msie ([\d.]+)/)[1];
        return { type: "IE", version: ver };
    }
    //firefox
    else if (explorer.indexOf("firefox") >= 0) {
        var ver = explorer.match(/firefox\/([\d.]+)/)[1];
        return { type: "Firefox", version: ver };
    }
    //Chrome
    else if (explorer.indexOf("chrome") >= 0) {
        var ver = explorer.match(/chrome\/([\d.]+)/)[1];
        return { type: "Chrome", version: ver };
    }
    //Opera
    else if (explorer.indexOf("opera") >= 0) {
        var ver = explorer.match(/opera.([\d.]+)/)[1];
        return { type: "Opera", version: ver };
    }
    //Safari
    else if (explorer.indexOf("Safari") >= 0) {
        var ver = explorer.match(/version\/([\d.]+)/)[1];
        return { type: "Safari", version: ver };
    }
}
alert("浏览器:" + getExplorerInfo().type + "\n 版本:" + getExplorerInfo().version);
*/
//长时间没操作退出系统
function IsPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod");
    var pcLogin = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            pcLogin = false;
            break;
        }
    }
    return pcLogin;
}

var count = 0;
var outTime = 1;//分钟
if (IsPC()) {
    //登录检测
    if (openid != null && openid != "null") {
        $("#inOrOut").empty();
        $("#inOrOut").attr("onclick","logout()");
        $("#inOrOut").append(' <div class="item_form"><p class="form_name">退出登录</p><span\n' +
            '                                        ></span></div>');
        window.setInterval(go, 1000);
    }
}

function go() {
    count++;
    if (count  == outTime * 60) { //60秒没操作后自动注销
        // alert("设置时长N分钟无鼠标键盘操作，自动跳转下一个页面");
        window.location = weChat.WeChatDNSURL + "web!logout";
    }
    // console.log(count);
    //当鼠标在 iframe里操作5秒后 在遮罩住 iframe 移动鼠标重新释放遮罩重新计时
    if (count % 5 == 0) {
        $("#showInformation").css("pointer-events", "none");
    }
    timeOut();
}

var x;
var y;
//监听鼠标
/* 鼠标移动事件 */
/*$(function(){
    $(document).mouseover(function(event){
        var x1 = event.clientX;
        var y1 = event.clientY;
        if (x != x1 || y != y1) {
            count = 0;
        }
        x = x1;
        y = y1;
    });
});*/
document.onmousemove = function (event) {
    $("#showInformation").css("pointer-events", "auto");
    var x1 = event.clientX;
    var y1 = event.clientY;
    if (x != x1 || y != y1) {
        count = 0;
    }
    x = x1;
    y = y1;
};
//监听键盘
/*
document.onkeydown = function () {
    count = 0;
    // $("#showInformation").css("pointer-events", "auto");
};
*/


//倒计时显示
function timeOut() {
    var timeOut = outTime * 60 - count;
    var odiv = document.getElementById("timeOut");
    odiv.innerHTML=timeOut;

}
