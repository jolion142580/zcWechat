$(document).ready(function(){
	
	$('.dep-main li').click(function(){
		$(this).siblings().removeClass('active');
		$(this).toggleClass('active');
	});
	
	$('.book-time li').click(function(){
		$(this).siblings().removeClass('active');
		$(this).toggleClass('active');
	});
	
	/* �������¼� */
	$('.openpop').click(function(){
		var $targetname = $(this).attr('href').substring(1);
		if ($targetname!='') {
			$('.popwin').hide();
			$('.popwin-'+$targetname).show().animate({opacity:"show",right:""},"fast");
		} else {
			$('.popwin-default').show().animate({opacity:"hide",top:""},"fast");
		}
		$('#pophide').show();
		return false;
	});

	$('.close').click(function(){
		$(this).parents('.popwin').animate({right:"0",opacity:"hide"},"fast");
		$('#pophide').hide();
	});
	
	$('.protocol-box span').click(function(){
		$(this).toggleClass('checked');
	});
	
});