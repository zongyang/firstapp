$(function() {
	$('.head-userName').text(g_user.getUserName());
	$('.head-userInfo').click(function() {
		location.href = 'info.html';
	});
	$('.head-userOut').click(function() {
		Ext.Msg.confirm('确认注销？', '是否注销当前用户?', function(e) {
			if (e == 'yes') {
				g_user.loginOut();
			}
		});

	});

	
	$('.head .user-iocn').attr('src', g_user.getImg()).mouseover(function() {
		$('.head-ul').removeClass('hide');
		//$('.head .user-iocn').addClass('hover');
	});
	$('.head-ul').mouseleave(function() {
		$('.head-ul').addClass('hide');
		//$('.head .user-iocn').removeClass('hover');
	});
});