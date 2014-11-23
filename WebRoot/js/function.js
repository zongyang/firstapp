var LOGIN_ERRORS = [ '邮箱地址不对！', '密码不对！', '用户名或者密码错误', '昵称不对!' ];

function getComIndex(doms, dom) {
	return doms.index(dom);
}
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
	if (str.trim() == '') {
		return true;
	}
	return false;
}
function checkEmail(str) {
	if (checkNull(str)) {
		return true;
	}
	str = str.trim();
	var reg = new RegExp('^\w+@\w+\.\w+$');
	return reg.test(str);
}
function checkPsw() {
	if (checkNull(str)) {
		return true;
	}
	var len = str.length;
	if (6 <= len <= 18) {
		return false;
	}
	return true;
}
function checkNick() {
	if (checkNull(str)) {
		return true;
	}
	var len = str.length;
	if (6 <= len <= 18) {
		return false;
	}
	return true;
}