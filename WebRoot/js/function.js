//获得元素在数组中的序号
function getComIndex(doms, dom) {
	return doms.index(dom);
}
// 检测字符串是否为空
function checkNull(str) {
	if (str == undefined) {
		return true;
	}
	if (str == null) {
		return true;
	}
	if (str == '') {
		return true;
	}
	if (str.trim && str.trim() == '') {
		return true;
	}
	return false;
}
// 输入框的检测及样式的修改
function inputCheck(el, regex, error, correct) {
	var reg = new RegExp(regex);
	var val = el.val();
	if (reg.test(val)) {
		el.removeClass('input-error');
		el.siblings().removeClass('tip-error').text(correct);
	} else {
		el.addClass('input-error');
		el.siblings().addClass('tip-error').text(error);
	}
}

// 用户信息存储
function UserInfo(id, email, nickName) {
	this.id = (checkNull(this.id)) ? sessionStorage.getItem('id') : id;
	this.email = (checkNull(this.email)) ? sessionStorage.getItem('email')
			: email;
	this.nickName = (checkNull(this.nickName)) ? sessionStorage
			.getItem('nickName') : nickName;
}
UserInfo.prototype.check = function() {
	if (checkNull(this.id) && checkNull(this.email)) {
		return true;
	}
	return false;
}
UserInfo.prototype.setId(val){
	this.id=val;
	sessionStorage.setItem('id',val);
}
UserInfo.prototype.setEmail(val){
	this.email=val;
	sessionStorage.setItem('email',val);
}
UserInfo.prototype.setNickName(val){
	this.nickName=val;
	sessionStorage.setItem('nickName',val);
}
UserInfo.prototype.loginOut = function() {// 注销操作
	if (this.check()) {
		return;
	}
	var user = {
		id : this.id,
		email : this.email
	};

	$.ajax({
		url : 'action',
		data : {
			user : JSON.stringify(user),
			method : 'loginOut'
		},
		success : function(obj) {
			obj = JSON.parse(obj);
			if (obj.success) {
				sessionStorage.clear();
				alert(obj.msg);
				location.href = 'login.html';
			}
		}
	});

}