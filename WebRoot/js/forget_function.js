$(function() {
	g_user.isNoLogin();
	$('.sg-body-l input').on('input', input_change);
	$('.forget-btn').click(forget_btn_click);
});

function forget_btn_click() {
	var flg = true;
	var input_user = $('.input-user');
	var input_email = $('.input-email');
	var input_password = $('.input-password');
	var tip_user = $('.tip-user');
	var tip_email = $('.tip-email');
	var tip_password = $('.tip-password');

	if (!userNameCheck(input_user.val())) {
		input_user.addClass('input-error');
		tip_user.addClass('tip-error').text('请检查用户名！');
		flg = false;
	}
	if (!emailCheck(input_email.val())) {
		input_email.addClass('input-error');
		tip_email.addClass('tip-error').text('请检查注册邮箱！');
		flg = false;
	}
	if (!passwordCheck(input_password.val())) {
		input_password.addClass('input-error');
		tip_password.addClass('tip-error').text('请检查用户密码！');
		flg = false;
	}

	if (flg) {
		input_user.removeClass('input-error');
		tip_user.removeClass('tip-error').text('输入有效用户名！');
		input_email.removeClass('input-error');
		tip_email.removeClass('tip-error').text('输入有效邮箱！');
		input_password.removeClass('input-error');
		tip_password.removeClass('tip-error').text('输入6-16位密码，不能使用空格！');
		send_forget_res();
	}

}
function input_change(event) {
	var target = event.target;
	var input_user = $('.input-user');
	var input_email = $('.input-email');
	var input_password = $('.input-password');
	var tip_user = $('.tip-user');
	var tip_email = $('.tip-email');
	var tip_password = $('.tip-password');

	if (target == input_user[0]) {
		if (!userNameCheck(input_user.val())) {
			input_user.addClass('input-error');
			tip_user.addClass('tip-error').text('请检查用户名！');
		} else {
			input_user.removeClass('input-error');
			tip_user.removeClass('tip-error').text('输入有效用户名！');
		}
	}
	if (target == input_email[0]) {
		if (!emailCheck(input_email.val())) {
			input_email.addClass('input-error');
			tip_email.addClass('tip-error').text('请检查注册邮箱！');
		} else {
			input_email.removeClass('input-error');
			tip_email.removeClass('tip-error').text('输入有效邮箱！');
		}
	}
	if (target == input_password[0]) {
		if (!passwordCheck(input_password.val())) {
			input_password.addClass('input-error');
			tip_password.addClass('tip-error').text('请检查用户密码！');
		} else {
			input_password.removeClass('input-error');
			tip_password.removeClass('tip-error').text('输入6-16位密码，不能使用空格！');
		}
	}

}
function send_forget_res() {
	var user = $('.input-user').val();
	var email = $('.input-email').val();
	var password = $('.input-password').val();

	$.ajax({
		url : 'action',
		data : {
			method : 'forget_req',
			userName : user,
			email : email,
			password : password
		},
		success : function(data) {
			var obj = Ext.JSON.decode(data);
			if (!obj.success) {// 修改失败
				Ext.Msg.alert('提示', obj.msg);
				return;
			}

			Ext.Msg.alert('提示', obj.msg, function() {// 修改成功
				
				g_user.setUserName(user);
				g_user.login(function(){
					
					location.href = 'chat.html';
				});
				return ;
				location.href = 'chat.html';
			});

		}
	});
}