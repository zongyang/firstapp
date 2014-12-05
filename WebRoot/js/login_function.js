
$(function() {
	// header
	$('.sg-header span').click(sg_header_click);
	// body
	
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
//注册动作
function reg_click(){
	var input_userName=$('.reg-username');
	var input_email=$('.reg-email');
	var input_password=$('.reg-password');
	var input_password_again=$('.reg-password-again');
	
	var tip_userName=$('.reg-tip-username');
	var tip_email=$('.reg-tip-email');
	var tip_password=$('.reg-tip-password');
	var tip_password_again=$('.reg-tip-password-again');
	
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