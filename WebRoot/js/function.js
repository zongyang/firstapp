var g_user = new UserInfo();
// 获得元素在数组中的序号
function getComIndex(doms, dom) {
	return doms.index(dom);
}

/*
 * 检测字符串是否为空
 */
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
function checkObjNull(obj) {
	if (checkNull(obj)) {
		return true;
	}

	for ( var i in obj) {
		if (checkNull(obj[i])) {
			return true;
		}
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

/*
 * 用户信息存储
 */
function UserInfo(userName, img) {
	this.userName = (checkNull(this.userName)) ? localStorage
			.getItem('userName') : this.userName;
	this.img = (checkNull(this.img)) ? localStorage.getItem('img') : this.img;// 保存图像，监听变化，以便相关图像的改变
}
UserInfo.prototype.check = function() {
	if (checkNull(this.userName)) {
		return true;
	}
	return false;
}
UserInfo.prototype.setUserName = function(val) {
	this.userName = val;
	localStorage.setItem('userName', val);
}
UserInfo.prototype.setImg = function(val) {
	this.img = val;
	localStorage.setItem('img', val);
}
UserInfo.prototype.getUserName = function() {
	return this.userName;
}
UserInfo.prototype.getImg = function() {
	return this.img;
}
UserInfo.prototype.loginOut = function() {// 注销操作
	if (this.check()) {
		return;
	}
	this.setUserName();
	this.setImg();
	localStorage.clear();
	return;

	// 删除websocket会话
}

/*
 * 字符串检测
 */
function regCheck(reg_exp, str) {
	var reg = new RegExp(reg_exp);
	return reg.test(str);
}
function userNameCheck(str) {
	return regCheck(/^\S{2,18}$/, str);
}
function emailCheck(str) {
	return regCheck(/^\w+@\w+\.\w+$/, str);
}
function passwordCheck(str) {
	return regCheck(/^\S{6,16}$/, str);
}
