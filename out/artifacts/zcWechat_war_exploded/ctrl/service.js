_uri_api = function(url){
//	return 'http://120.25.97.123:8080/kplatform/api/disaster/' + url; 
	return '/fuwuapp-api/api/yb/' + url; 
}

function post(url, data, callback) {
	$.ajax({
//        beforeSend: function(reqObj, settings) {
//            reqObj.setRequestHeader('Token', '73d42c6777d97d425ea3cca00087f2be76utMnRR');
//        },
	    type: "POST",
		data: data,
		url: url,
		dataType: "json",
		error: function(resObj, textStatus, errorThrown) {
			data.code = -1;
			callback(data, resObj.responseText);
        },
        success: function(data, textStatus, resObj) {
//        	if (data.code != 200) {
//        		//alert(data.msg);
//        		return;
//        	}
        	callback(data, resObj.responseText);
        }
	});
}

var api = {
		 matter: {//事项
			list:function(data, func) {
				 post(_uri_api('matter'), data, func);
			 }
		 },
		 notice: {//公告
			 list:function(data, func) {
				 post(_uri_api('notice'), data, func);
			 },
			 detail:function(data, func) {
				 post(_uri_api('noticedetail'), data, func);
			 }
		 },
		 angency: {//办事样表
			 list:function(data, func) {
				 post(_uri_api('angency'), data, func);
			 },
			 detail:function(data, func) {
				 post(_uri_api('angencydetail'), data, func);
			 },
			 allangency:function(data, func) {
				 post(_uri_api('allangency'), data, func);
			 },
			 search:function(data, func) {
				 post(_uri_api('searchangency'), data, func);
			 }
		 }, 
		 theme:{//主题
			 list:function(data,func){
				 post(_uri_api('theme'), data, func);
			 }
		 }
	};

//var clone = function(e) {
//    if ("object" != typeof e)
//        return e;
//    if (null == e)
//        return e;
//    var t = new Object;
//    for (var n in e)
//        t[n] = clone(e[n]);
//    return delete t.$$hashKey, t
//}
//
//var toParam = function(e, t, n) {
//    var o = clone(t);
//    for (var a in o)
//        n&&-1 != n.indexOf(a) || (o[e + "." + a] = o[a], delete o[a]);
//    return o
//}

function getQueryString(name) { 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) 
		return unescape(r[2]); 
	return null; 
} 

Date.prototype.pattern = function(fmt) {           
    var o = {           
    "M+" : this.getMonth()+1, //月份           
    "d+" : this.getDate(), //日           
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时           
    "H+" : this.getHours(), //小时           
    "m+" : this.getMinutes(), //分           
    "s+" : this.getSeconds(), //秒           
    "q+" : Math.floor((this.getMonth()+3)/3), //季度           
    "S" : this.getMilliseconds() //毫秒           
    };           
    var week = {           
    "0" : "\u65e5",           
    "1" : "\u4e00",           
    "2" : "\u4e8c",           
    "3" : "\u4e09",           
    "4" : "\u56db",           
    "5" : "\u4e94",           
    "6" : "\u516d"          
    };           
    if(/(y+)/.test(fmt)){           
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));           
    }           
    if(/(E+)/.test(fmt)){           
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);           
    }           
    for(var k in o){           
        if(new RegExp("("+ k +")").test(fmt)){           
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));           
        }           
    }           
    return fmt;           
}

