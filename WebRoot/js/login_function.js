$(function() {
	// header
	$('.sg-header span').click(sg_header_click);
	// body
	$('.log-btn').click(log_click);
	$('.reg-btn').click(reg_click);
	$('.sg-body-r input').on('input', reg_input_change);
	$('.sg-body-l input').on('input', log_input_change);
	// footer
});

function sg_header_click(event) {
	var self = $(this);
	self.addClass('sg-header-span-click');
	self.siblings().removeClass('sg-header-span-click');

	var body_index = getComIndex($('.sg-header span'), this)
	var sg_body = $('.sg-body:nth(' + body_index + ')').removeClass('hide');
	sg_body.siblings().addClass('hide');
}
// 登录动作
function log_click() {
	var flg = true;
	var input_userName = $('.log-username');
	var input_password = $('.log-password');

	var tip_userName = $('.log-tip-username');
	var tip_password = $('.log-tip-password');

	if (!userNameCheck(input_userName.val())) {
		input_userName.addClass('input-error');
		tip_userName.addClass('tip-error').text('请检查用户名！');
		flg = false;
	}
	if (!passwordCheck(input_password.val())) {
		input_password.addClass('input-error');
		tip_password.addClass('tip-error').text('请检查用户密码！');
		flg = false;
	}

	if (flg) {
		input_userName.removeClass('input-error');
		tip_userName.removeClass('tip-error').text('输入有效用户名！');
		input_password.removeClass('input-error');
		tip_password.removeClass('tip-error').text('输入6-16位密码，不能使用空格！');
		send_log_res();
	}
}

// 注册动作
function reg_click() {
	var flg = true;
	var input_userName = $('.reg-username');
	var input_email = $('.reg-email');
	var input_password = $('.reg-password');
	var input_password_again = $('.reg-password-again');

	var tip_userName = $('.reg-tip-username');
	var tip_email = $('.reg-tip-email');
	var tip_password = $('.reg-tip-password');
	var tip_password_again = $('.reg-tip-password-again');

	if (!userNameCheck(input_userName.val())) {
		input_userName.addClass('input-error');
		tip_userName.addClass('tip-error').text('请检查用户名！');
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
	if (input_password_again.val() != input_password.val()) {
		input_password_again.addClass('input-error');
		tip_password_again.addClass('tip-error').text('密码不一致！');
		flg = false;
	}

	if (flg) {
		input_userName.removeClass('input-error');
		tip_userName.removeClass('tip-error').text('输入有效用户名！');
		input_email.removeClass('input-error');
		tip_email.removeClass('tip-error').text('输入有效邮箱！');
		input_password.removeClass('input-error');
		tip_password.removeClass('tip-error').text('输入6-16位密码，不能使用空格！');
		input_password_again.removeClass('input-error');
		tip_password_again.removeClass('tip-error').text('输入再次输入密码，不能使用空格！');
		send_forget_res();
	}
}

// log input输入动作
function log_input_change(event) {
	var target = event.target;
	var input_userName = $('.log-username');
	var input_password = $('.log-password');

	var tip_userName = $('.log-tip-username');
	var tip_password = $('.log-tip-password');

	if (target == input_userName[0]) {
		if (!userNameCheck(input_userName.val())) {
			input_userName.addClass('input-error');
			tip_userName.addClass('tip-error').text('请检查用户名！');
		} else {
			input_userName.removeClass('input-error');
			tip_userName.removeClass('tip-error').text('输入有效用户名！');
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
// reg input输入动作
function reg_input_change(event) {
	var target = event.target;
	var input_userName = $('.reg-username');
	var input_email = $('.reg-email');
	var input_password = $('.reg-password');
	var input_password_again = $('.reg-password-again');

	var tip_userName = $('.reg-tip-username');
	var tip_email = $('.reg-tip-email');
	var tip_password = $('.reg-tip-password');
	var tip_password_again = $('.reg-tip-password-again');

	if (target == input_userName[0]) {
		if (!userNameCheck(input_userName.val())) {
			input_userName.addClass('input-error');
			tip_userName.addClass('tip-error').text('请检查用户名！');
		} else {
			input_userName.removeClass('input-error');
			tip_userName.removeClass('tip-error').text('输入有效用户名！');
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
		if (input_password_again.val() != input_password.val()) {
			input_password_again.addClass('input-error');
			tip_password_again.addClass('tip-error').text('密码不一致！');
		} else {
			input_password_again.removeClass('input-error');
			tip_password_again.removeClass('tip-error')
					.text('输入再次输入密码，不能使用空格！');
		}
	}
	if (target == input_password_again[0]) {
		if (input_password_again.val() != input_password.val()) {
			input_password_again.addClass('input-error');
			tip_password_again.addClass('tip-error').text('密码不一致！');
		} else {
			input_password_again.removeClass('input-error');
			tip_password_again.removeClass('tip-error')
					.text('输入再次输入密码，不能使用空格！');
		}
	}
}

// 注册提交
function send_forget_res() {
	var input_userName = $('.reg-username');
	var input_email = $('.reg-email');
	var input_password = $('.reg-password');

	$.ajax({
		url : 'action',
		data : {
			method : 'reg_req',
			userName : input_userName.val(),
			email : input_email.val(),
			password : input_password.val()
		},
		success : function(data) {
			var obj = Ext.JSON.decode(data);
			if (!obj.success) {
				Ext.Msg.alert('提示', obj.msg);
				return;
			}

			Ext.Msg.alert('提示', obj.msg, function() {// 注册成功
				
				g_user.setUserName(input_userName.val());
				g_user.login(function(){
					location.href = 'chat.html';
				});
				
				return ;
				
				g_user.setUserName(input_userName.val());
				location.href = 'chat.html';
			});

		}
	});
}
// 登录提交
function send_log_res() {
	var input_userName = $('.log-username');
	var input_password = $('.log-password');

	
	$.ajax({
		url : 'action',
		data : {
			method : 'log_req',
			userName : input_userName.val(),
			password : input_password.val()
		},
		success : function(data) {
			var obj = Ext.JSON.decode(data);
			if (!obj.success) {
				Ext.Msg.alert('提示', obj.msg);
				return;
			}

			Ext.Msg.alert('提示', obj.msg, function() {// 登录成功
				g_user.setUserName(input_userName.val());
				
				g_user.login(function(){
					location.href = 'chat.html';
				});
				return;
				
				location.href = 'chat.html';
			});

		}
	});
}