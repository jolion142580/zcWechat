var model;
var listData;
var search=function(obj){
	//alert(obj);
	var urlAddress=window.location.href;
	var sureUrlAdd=urlAddress.substring(0,urlAddress.lastIndexOf("/"));
	
	if(obj.trim()==""){
		$(".content_subcontent").css('left','100%'); 
		$('.search_content').css('width','100%');
		$('.content_result').addClass('status_noresult').removeClass('show_review');
		$("#searchResult").empty();
		$('#content').css("display","none");
		$('.item_result div').text('输入关键字搜索办事指南');
	}else{
		$.post("ssAffairsInfo!findByAffairName",{ affairname: obj} ,function(result) {
			//alert(result);
			 if(result==""){
				 $('.content_result').addClass('status_noresult').removeClass('show_review');
				 $('#content').css("display","none");
				 $('.item_result div').text('没有找到相关结果');
			 }else{
				 var resultContent="";
				
					$.each(result, function(k, v) {
						$("#searchResult").empty();
			          	 
						resultContent+="<dd data-bind=\"click:searchDetail\" onclick=\"changeResult("+v.affairid+")\"><div class=\"item_form\"><p class=\"form_name\" data-bind=\"text:angencyName\">"+v.affairname+"</p><span class=\"ic ic_arrowright\"></span></div></dd>";

			        });
					
					$('.content_result').removeClass('status_noresult show_review');
					$('#content').css("display","block");
					$('.scroll_parent').scrollTop(0);
					$("#searchResult").append(resultContent);
				 
			 }
			
			
			
			
	   }, "json"); 
		
	}
	
	 
}
var clean=function(){
	model.keyword("");
}
var searchDetail=function(obj){
	var pics=obj.url.split('-'),pics1=[];
	for(var i=0;i<pics.length;i++){
		pics1.push({index:i,src:"/fuwuapp/upload/images"+pics[i]});
	}
	
	model.picData(pics1);
	$('.content_imgcontent').show();
	$('.content_review').scrollTop(0);
}

function searchModel() {
	model = this;
	model.searchData = ko.observableArray();
	model.picData=ko.observableArray();
	model.keyword=ko.observable("");
	//refreshList();
	model.keyword.subscribe(function(newValue){
		search(newValue);
	});
}

ko.applyBindings(new searchModel());