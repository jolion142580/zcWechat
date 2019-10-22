var model;
var listData;
var search=function(obj){
	api.angency.search({key:obj}, function(data, responseText) { 
		model.searchData([]);
		$('.content_review').scrollTop(0); 
		$('.content_imgcontent').hide();
		if(obj.trim()==""){
			$(".content_subcontent").css('left','100%'); 
			$('.search_content').css('width','100%');
			$('.content_result').addClass('status_noresult').removeClass('show_review');
			$('.item_result p').text('输入关键字搜索相关样表');
		}else if(data.data.data.length==0){
			$('.content_result').addClass('status_noresult').removeClass('show_review');
			$('.item_result p').text('没有找到相关结果');
		}else if(data.data.data.length>0){
			model.searchData(data.data.data);
			$('.content_result').removeClass('status_noresult show_review');
			$('.scroll_parent').scrollTop(0);
		}
	});
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