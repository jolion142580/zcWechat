var model;
var listData;

var refreshList = function() {
	api.theme.list({client:'android'}, //iphone android
		function(data, responseText) {
			model.listData(data.data.data);
			griditem_num=$('.content_grid .griditem').length;
			var colnum;
			if(griditem_num==1){
				colnum=1;
			}else if(griditem_num==2 || griditem_num==4){
				colnum=2;
			}else if(griditem_num==3 || griditem_num==5 || griditem_num==6){
				colnum=3;
				$('.content_grid .griditem').height($('.contain').height());
				$('.content_grid').attr('style','overflow:hidden;')
			}else{
				colnum=4;
			}
			$('.content_grid').removeClass().addClass('content content_grid col'+colnum)
		});
}

var enter=function(obj){
	window.location.href = "formviewer.html?theme="+obj.id+"&name="+obj.name;
}

function indexModel() {
	model = this;
	model.listData = ko.observableArray();
	refreshList();
}

ko.applyBindings(new indexModel());
