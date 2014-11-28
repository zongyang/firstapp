var LOGIN_TIPS = [ '邮箱地址不对！', '输入有效的邮箱！', '密码不对！', '输入6-16位密码,不能使用空格!',
		'昵称不对!', '输入昵称，2-18位中英文、数字或下划线！', '请仔细检查输入项!', '登录成功！', '注册成功！' ];

$(function() {
	//检测是否已登录过
	if (!g_user.check()) {
		//location.href = 'chat.html';
	}

	// header
	$('.sg-header span').click(headerClick);
	// body
	$('.bg-mail').on('input', emailCheck);
	$('.bg-psw').on('input', pswCheck);
	$('.bg-nick').on('input', nickCheck);
	$('.sg-body-l button').click(loginClick);
	$('.sg-body-r button').click(regClick);
	// footer
});

function headerClick(event) {
	var self = $(this);
	self.addClass('sg-header-span-click');
	self.siblings().removeClass('sg-header-span-click');

	var body_index = getComIndex($('.sg-header span'), this)
	var sg_body = $('.sg-body:nth(' + body_index + ')').removeClass('hide');
	sg_body.siblings().addClass('hide');
}
// 登录动作
function loginClick(event) {
	var self = $(event.target);
	var inputs = self.parent().parent().find('input');
	var errors = inputs.hasClass('input-error');
	var data = {
		email : inputs[0].value,
		psw : inputs[1].value
	};
	if (errors > 0 || checkObjNull(data)) {
		self.siblings().addClass('tip-error').text(LOGIN_TIPS[6]);
		return;
	}

	data.method = 'login';
	$.ajax({
		url : 'action',
		data : data,
		success : function(obj) {
			obj = JSON.parse(obj);
			// 登录成功
			if (obj.success) {
				// 本地存储
				g_user.setEmail(data.email);
				g_user.setId(obj.msg.split(',')[0]);
				g_user.setNickName(obj.msg.split(',')[1]);
				// 样式修改
				$('.login-tip').removeClass('tip-error').text(LOGIN_TIPS[7]);
				// 跳转
				location.href = 'chat.html';
			} else {
				$('.login-tip').addClass('tip-error').text(obj.msg);
			}
		}
	});
}
// 注册动作
function regClick(event) {
	var self = $(event.target);
	var inputs = self.parent().parent().find('input');
	var errors = inputs.hasClass('input-error');
	var data = {
		email : inputs[0].value,
		psw : inputs[1].value,
		nickName : inputs[2].value
	};
	if (errors > 0 || checkObjNull(data)) {
		self.siblings().addClass('tip-error').text(LOGIN_TIPS[6]);
		return;
	}

	$.ajax({
		url : 'action',
		data : {
			user : JSON.stringify(data),
			method : 'register'
		},
		success : function(obj) {
			obj = JSON.parse(obj);
			// 注册成功
			if (obj.success) {
				// 本地存储
				g_user.setEmail(data.email);
				g_user.setNickName(data.nickName);
				g_user.setId(obj.msg);
				// 样式修改
				$('.reg-tip').removeClass('tip-error').text(LOGIN_TIPS[8]);
				// 跳转
				location.href = 'chat.html';
			} else {
				$('.reg-tip').addClass('tip-error').text(obj.msg);
			}
		}
	});
}
function emailCheck(event) {
	var self = $(event.target);
	var reg = /^\w+@\w+\.\w+$/;
	var error = LOGIN_TIPS[0];
	var correct = LOGIN_TIPS[1];
	inputCheck(self, reg, error, correct);
}
function pswCheck(event) {
	var self = $(event.target);
	var reg = /^\S{6,16}$/;
	var error = LOGIN_TIPS[2];
	var correct = LOGIN_TIPS[3];
	inputCheck(self, reg, error, correct);
}
function nickCheck(event) {
	var self = $(event.target);
	var reg = /^\S{2,18}$/;
	var error = LOGIN_TIPS[4];
	var correct = LOGIN_TIPS[5];
	inputCheck(self, reg, error, correct);
}