//�ύʱ��FORM<form enctype="multipart/form-data" id="iegovBusinessForm" action="${globalContextPath}/saveBusinessAction.struts" method="post">
//enctype="multipart/form-data"��method="post"�Ǳ����

//��formתΪAJAX�ύ
function ajaxFormSubmit(frm, fn){
	var dataPara = getFormJson(frm);
	$.ajax({
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		//contentType:'multipart/form-data',
		async:false,
		url: frm.action,
		type: frm.method,
		data: dataPara,
		success: fn
	});
}
//��form�е�ֵת��Ϊ��ֵ�ԡ�
function getFormJson(frm){
	var o = {};
	var a = $(frm).serializeArray();
	$.each(a, function () {
//				if (o[this.name] !== undefined) {
					//if (!o[this.name].push) {
					//	o[this.name] = [o[this.name]];
					//}
//					o[this.name].push(this.value || '');
//				} else {
			o[this.name] = this.value || '';
//				}
			});
	return o;
}

//��formתΪAJAX�ύ
function ajaxFormSubmitAsync(frm, fn){
	var dataPara = getFormJson(frm);
	$.ajax({
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		//contentType:'multipart/form-data',
		async:true,
		url: frm.action,
		type: frm.method,
		data: dataPara,
		success: fn
	});
}