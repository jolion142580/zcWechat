$(document).ready(function(){
	
	$('.appointment-date td').hover(function(){
        $(this).parent().find('td').addClass('td-hover');
    },function(){
        $(this).parent().find('td').removeClass('td-hover');
    });
    
    $('.appointment-date .col-2').hover(function(){
        $(this).parent().siblings().find('.col-2').addClass('td-hover');
    },function(){
        $(this).parent().siblings().find('.col-2').removeClass('td-hover');
    });
    $('.appointment-date .col-3').hover(function(){
        $(this).parent().siblings().find('.col-3').addClass('td-hover');
    },function(){
        $(this).parent().siblings().find('.col-3').removeClass('td-hover');
    });
    $('.appointment-date .col-4').hover(function(){
        $(this).parent().siblings().find('.col-4').addClass('td-hover');
    },function(){
        $(this).parent().siblings().find('.col-4').removeClass('td-hover');
    });
    $('.appointment-date .col-5').hover(function(){
        $(this).parent().siblings().find('.col-5').addClass('td-hover');
    },function(){
        $(this).parent().siblings().find('.col-5').removeClass('td-hover');
    });
    $('.appointment-date .col-6').hover(function(){
        $(this).parent().siblings().find('.col-6').addClass('td-hover');
    },function(){
        $(this).parent().siblings().find('.col-6').removeClass('td-hover');
    });
    
    /* 寮瑰嚭灞備簨浠? */
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
    
});