var model;
var listData;

var refreshList = function() {
	var theme=$.getURLParam('theme');
	model.themeName(decodeURIComponent($.getURLParam('name')));
	api.matter.list({id:theme}, function(data, responseText) {
		model.matterData(data.data.data);
		$('dd')[0].click();
	});
}

var enterAngency=function(obj){
	$('.content_formlist .scroll_parent').scrollTop(0);
	api.angency.list({id:obj.id}, function(data, responseText) {
		model.angencyData(data.data.data);
		$('.angency')[0].click();
	});
}

var angencyDetail=function(obj){
	$('.content_review').scrollTop(0);
	var pics=obj.url.split('-'),pics1=[];
	for(var i=0;i<pics.length;i++){
		pics1.push({index:i,src:"/fuwuapp/upload/images"+pics[i]});
	}
	model.picData(pics1);
	
	
}

function formviewerModel() {
	model = this;
	model.matterData = ko.observableArray();
	model.angencyData=ko.observableArray();
	model.picData=ko.observableArray();
	model.themeName=ko.observable('');
	//$('.scroll_parent').scrollTop(0);
	refreshList();
}

ko.applyBindings(new formviewerModel());