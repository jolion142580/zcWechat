
ww=$(window).width();
wh=$(window).height();
ie8old=0;
var countdown;
function countdownbackhome(e){
	time=e;
	// console.log('剩余'+time+'秒');
	countdown=setInterval(setTime, 1000);
	function setTime(){
		time--;
		// console.log('剩余'+time+'秒');
		if (time==0) {
			window.location.href='Index.jsp';
			clearInterval(countdown);
			time=e;
		};
	}
}
// 返回首页
function gohomepage(){
	$('.main>*:not(.home),.notdel').fadeOut(function(){
		boardn=$('.boardcontent').size();
		$('.boardcontent>.boardcontent').each(function(){
			$(this).unwrap();
		})
		$('.main>.view').remove();
		$('.main .home').css('zIndex','1').animate({'opacity':1})
	});
	$('.main').removeClass('inboard');
}

function scrollbar_set(){
	$(".scroll_parent").mCustomScrollbar({
		scrollButtons:{enable:true,scrollSpeed:20,scrollAmount:40},
		theme:"rounded-dark",
		scrollbarPosition:"outside",
		documentTouchScroll:true,
		contentTouchScroll: 25
	});	
}

function containMinH(){
	wh=$(window).height();
	$('.contain').css('height',wh-48);
}

// 隐藏快速导航标签提示
function clhide(){
	$('.currentLetter').delay(800).fadeOut(200);
	$('.nav_letter .backbg').removeClass('active');
}
// 侧边栏滚动跳转
function scroll_jump(jumpToWord){
	jump_distance=$('.title_bigword[navword='+jumpToWord+']').position().top;
	$('.nav_letter+.scroll_parent').scrollTop(jump_distance);
}
// 操作状态
function backbg_active(){
	$('.nav_letter .backbg').addClass('active');
}

// 全屏
function fullscreen(){
	$('.contain').toggleClass('fullScreenMode');
	$('.content_review').attr('zoomx',1).children('.body_review').removeAttr('style');
	$('.zoomX').text('100%')
	$('.prevent').show();

	if($('.contain').hasClass('fullScreenMode')){
		$('.tips_toFullScreen .clicktips').text('点击右下角按钮退出全屏');
		
		$('.status_fullscreen').text('退出全屏');
		imgctrl('body');
	}else{
		$('.tips_toFullScreen .clicktips').text('点击全屏按钮全屏查看');
		$('.status_fullscreen').text('全屏');
		$('.control_form').removeAttr('style')
		// imgctrl();
	}

	zoom_parent=$('.content_review')
	zoom_body=$('.content_review .body_review')
	origin_height=zoom_body.outerHeight();
	origin_width=zoom_body.outerWidth();
	origin_scrollTop=zoom_parent.scrollTop();
	setTimeout(function(){
		scroll_zoom=origin_scrollTop/origin_height*zoom_body.outerHeight();
		zoom_parent.scrollTop(scroll_zoom);
		$('.prevent').hide();
	},200)
}
function imgctrl(){
	// imgctrlw=$(window).width()*.496-80;
	if($('.contain').hasClass('fullScreenMode')){
		imgctrlw=$(window).width()-80;
		$('.control_form').css({
			'width':imgctrlw,
			'margin-left':-imgctrlw/2
		})
	}
}

// 放大缩小
function formZoom(){
	zoom_parent=$('.content_review')
	zoom_body=$('.content_review .body_review')
	percent_base=92;
	this_=$(this);
	origin_height=zoom_body.outerHeight();
	origin_width=zoom_body.outerWidth();
	origin_scrollTop=zoom_parent.scrollTop();
	zoomx=parseInt(zoom_parent.attr('zoomx'));
	var zoomToX;
	if(this_.hasClass('btn_zoom_in')){
		if(zoomx+1<=4){
			zoomx=zoomx+1;
		}else{
			return false;
		}
	}

	if(this_.hasClass('btn_zoom_out')){
		if(zoomx-1>=-1){
			zoomx=zoomx-1;
		}else{
			return false;
		}
	}

	switch(zoomx){
		case -1:
		zoomToX=.5;
		break;

		case 0:
		zoomToX=.75;
		break;

		case 1:
		zoomToX=1;
		break;

		case 2:
		zoomToX=1.5;
		break;

		case 3:
		zoomToX=2;
		break;

		case 4:
		zoomToX=3;
		break;
	}

	zoom_body.width(percent_base*zoomToX+'%');
	scroll_zoom=origin_scrollTop/origin_height*zoom_body.outerHeight();
	zoom_parent.scrollTop(scroll_zoom).attr('zoomx',zoomx);
	$('.zoomX').text(zoomToX*100+'%')
}

//列表高度自动
function scroll_parentH_auto(){
	$('.title_formNeed').each(function(){
		scroll_parentH=$(window).height()-96;
		$(this).next('.scroll_parent').height(scroll_parentH);
	})
}


// 连接跳转到本地服务器
function replaceHref(){
	if(location.hostname==""){
		pathname=location.pathname;
		jumpToLink=pathname.replace('/D:/Apache24/htdocs/','http://localhost/');
		window.location.href=jumpToLink
	}
}
// 屏蔽鼠标右键和文字选中
function shieldselect(){
	document.oncontextmenu=function(){event.returnValue=false}
	document.onselectstart=function(){event.returnValue=false}
}
//浏览器UA标识
function whatBrowser() {  
	Name=navigator.appName;  
	Version=navigator.appVersion;  
	Code=navigator.appCodeName;  
	Agent=navigator.userAgent;  
	if(Version.indexOf('Mobile')!=-1 || Version.indexOf('Android')!=-1){
		$('head').append('<style>*{cursor: default!important;}.header .ic_back:hover,.content_grid .griditem:hover,.content_formlist dd:hover,.tips_toFullScreen .btn_iknow:hover,.control_form .btn:hover,.control_form .btn_screenContorl:hover,.content_allForm dd:hover{background-color: transparent;}.header .ic_back:active{background-color:#0280b0;}.content_grid .griditem:active{background-color: #f0f0f0;}.content_matter dd:active{background-color: #f5f5f5;}.content_formlist dd:active{background-color: #f5f5f5;}.tips_toFullScreen .btn_iknow:active{background-color: #999;}.control_form .btn:active{background-color-color: #666;}.control_form .btn_screenContorl:active{background-color: #666;}.content_allForm dd:active{background-color: #f5f5f5;}</style>')
	}
	if(Name=='Microsoft Internet Explorer'){
		$('head').append('<style>.searchInput.focus .ic_clearinput{display:none;}</style>')
		ieVersion=Version.substr(Version.indexOf('MSIE')+5,1)
		if(ieVersion<9){
			ie8old=1;
			$('head').append('<style>.control_form .btn img{display: block;position: absolute!important;;left: 0!important;;top: 0!important;width: 16px!important;height: 16px!important;}.control_form .btn div{display: none!important;}</style>')
		}
	}
	// alert(Name+'/version:n/'+Version+'/Code:'+Code+'/Agent:'+Agent)
}  


// 判断PC设备
function IsPC() {
	var userAgentInfo = navigator.userAgent;
	var Agents = ["Android", "iPhone","SymbianOS", "Windows Phone", "iPod", "iPad"];
	var flag = true;
	for (var v = 0; v < Agents.length; v++) {
		if (userAgentInfo.indexOf(Agents[v]) > 0) {
			flag = false;
			break;
		}
	}
	console.log(flag)
	// alert(flag)
	if(flag){
		$('head').append('<link rel="stylesheet" href="content/interaction_pc.css" />')
	}else{
		$('head').append('<link rel="stylesheet" href="content/interaction_mobile.css" />')
	}
	// return flag;
}


function searchInputW(){
	if($('.searchInput a.fullCover').length>0){
		$('.searchInput').width($(window).width()-312)
	}else{
		$('.searchInput').width($(window).width()-96)
	}
	$('.searchInput input[type=text]').width($('.searchInput').width()-80)
}

// 屏保自动高度
function autoH_screensaver(){
	wh=$(window).height();
	ww=$(window).width();
	bh=$('body').height();
	if(ww>wh){
		if(wh<768){
			rate_wh=618/572
			$('.centerlogo_big').addClass('vc').css({
				width:(bh-140)*rate_wh,
				height:bh-140,
				marginLeft:(bh-140)*rate_wh/2*-1
			})
		}else{
			$('.centerlogo_big').removeClass('vc').removeAttr('style')
		}
	}else{
		$('.centerlogo_big').removeClass('vc').removeAttr('style')
	}
}

$(document).ready(function(){
	//replaceHref();//正式版删除该行！！！！！！！！！！！！！
	shieldselect();//屏蔽右键
	whatBrowser();
	containMinH();
	searchInputW();
	// imgctrl();
	scroll_parentH_auto();
	autoH_screensaver();
	// scrollbar_set();
	IsPC();

	$('.insert_cleardiv').append('<div class="clear"></div>')
	griditem_num=$('.content_grid .griditem').length;
	var colnum;
	if(griditem_num==1){
		colnum=1;
	}else if(griditem_num==2 || griditem_num==4){
		colnum=2;
	}else if(griditem_num==3 || griditem_num==5 || griditem_num==6){
		colnum=3;
	}else{
		colnum=4;
	}
	//$('.content_grid').removeClass().addClass('content content_grid col'+colnum)
	/*$('body').on('click','*',function(){
		alert($(this)[0].tagName)
	})*/

	/*——————————多级状态阻止href跳转——————————*/
	$('body').on('click','.header .btn_back.link_checkJump',function(){
		/*if($('.show_subcontent').length>0 && $('.show_subcontent').hasClass('content_allForm')==false){
			$('.show_subcontent').removeClass('show_subcontent');
			$('.content_subcontent').css('left','100%')
			return false;
		}*/
		if($('.searchInput').length>0){
			if($('.searchInput input').attr('value').replace(/\s/g,"")!=''){
				$(".content_subcontent").css('left','100%'); 
				$('.search_content').css('width','100%');
				$('.content_review').scrollTop(0);
				$('.content_imgcontent').hide();
				clear_searchinput();
				status_searchinput();
				return false;
			}
		}
		if(ie8old==1){
			jumplink=$(this).attr('jslink');
			window.location.href=jumplink;
		}
	})
	/*——————————js跳转——————————*/
	$('body').on('click','*[jslink]:not(.link_checkJump)',function(){
		if(ie8old==1){
			jumplink=$(this).attr('jslink');
			window.location.href=jumplink;
		}
	})

	/*——————————点击高亮——————————*/
	$('body').on('click','.content_matter dd,.content_formlist dd',function(){

		if($(this).parents('.content').hasClass('content_subcontent')==false){
			$(this).parents('.contain').addClass('show_subcontent show_review');
			console.log(false)
		}
		if($(this).parents('.content').hasClass('content_matter')){
			$('.content_subcontent').css('left','20%');
		}
		if($(this).parents('.content').hasClass('search_content')){
			$('.content_subcontent').css('left','50%');
			$('.search_content').css('width','50%');
		}
		if($(this).hasClass('selection')){
			return false;
		}else{
			$(this).parents('.scroll_parent').find('dd').removeClass('selection');
			$(this).addClass('selection').siblings().removeClass('selection');
		}
	});

	/*——————————字母导航行高——————————*/
	wordsize=$('.group_word span').size();
	navH=$('.group_word').height();
	$('.group_word').css('line-height',navH/wordsize+'px');
	$('.group_word *').height(navH/wordsize);


	/*——————————我知道了——————————*/
	/*setTimeout(function(){
		$('.tips_toFullScreen').addClass('hide')
	},8000);*/
	$('.btn_iknow').click(function(){
		$('.tips_toFullScreen').addClass('hide');
	})

	/*——————————切换全屏——————————*/
	$('.btn_screenContorl').click(fullscreen)
	$('.body_review').dblclick(fullscreen)
	/*——————————放大缩小——————————*/
	$('.btn_zoom').click(formZoom)


	/*——————————侧边栏快速导航点击——————————*/
	$('.group_word p').click(function(){
		console.log("navclick");
		this_=$(this);
		index=this_.index();
		letter=this_.text();
		cl=$('.currentLetter')

		cl.attr('navindex',index).html(letter)
		scroll_jump(letter);
		cl.stop(true).show();
		clhide();
	})

	/*——————————随机标签列表数————————*/
//	$('.title_bigword').each(function(){
//		random=Math.round(Math.random()*10)+1;
//		for(i=0;i<random;i++){
//			$(this).after('<dd><div class="item_form"><p class="form_name">《一人（法人独资）公司章程参考范本》</p><span class="ic ic_arrowright"></span></div></dd>')
//		}
//	})
//	$('.auto_plus').each(function(){
//		random=Math.round(Math.random()*200)+1;
//		for(i=0;i<random;i++){
//			$(this).after('<dd><div class="item_form"><p class="form_name">《一人（法人独资）公司章程参考范本》</p><span class="ic ic_arrowright"></span></div></dd>')
//		}
//	})


/*——————————搜索框焦点状态——————————*/
$('.searchInput input').focus(function(){
	this_=$(this);
	this_.parent('.searchInput').addClass('focus');
}).blur(function(){
	this_=$(this);
	this_.parent('.searchInput').removeClass('focus');
}).change(function(){

})
/*——————————清空搜索框——————————*/
$('.ic_clearinput').click(function(){
	$('.content_review').scrollTop(0);
	$('.content_imgcontent').hide();
	$(".content_subcontent").css('left','100%'); 
	$('.search_content').css('width','100%');
	clear_searchinput();
	status_searchinput();
});
function clear_searchinput(){
	$('.searchInput input[type=text]').attr('value','').focus();
}

	/*————————————搜索框输入时动态搜索
	（该js为失去焦点时发生，自动完成功能的处理操作跟这个一样）————————————*/
	$('.searchInput input').change(status_searchinput)
	function status_searchinput(){
		$('.content_review').scrollTop(0);
		if($('.searchInput input').attr('value').replace(/\s/g,"")==''){
			$(".content_subcontent").css('left','100%'); 
			$('.search_content').css('width','100%');
			$('.content_result').addClass('status_noresult').removeClass('show_review');
			$('.item_result p').text('输入关键字搜索相关样表');
			$('.content_imgcontent').hide();
		}
		/*else if(没有找到相关结果){
			$('.content_result').addClass('status_noresult').removeClass('show_review');
			$('.item_result p').text('没有找到相关结果')
		}else{
			$('.content_result').removeClass('status_noresult show_review');
		}*/
	}

// 倒计时返回（这个一定要放在所有js的最后面）
/*countdownbackhome(300);
$('body').on('click','*',function(){
	clearInterval(countdown);
	countdownbackhome(300);
});*/

});

$(window).resize(function(){
	containMinH();
	searchInputW();
	imgctrl();
	scroll_parentH_auto();
	autoH_screensaver();
})