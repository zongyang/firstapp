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
function UserInfo(userName, img, mark, regTime, sex, area) {
	this.userName = (checkNull(this.userName)) ? localStorage
			.getItem('userName') : this.userName;
	this.img = (checkNull(this.img)) ? localStorage.getItem('img') : this.img;
	this.mark = (checkNull(this.mark)) ? localStorage.getItem('mark')
			: this.mark;
	this.regTime = (checkNull(this.regTime)) ? localStorage.getItem('regTime')
			: this.regTime;
	this.sex = (checkNull(this.sex)) ? localStorage.getItem('sex') : this.sex;
	this.area = (checkNull(this.area)) ? localStorage.getItem('area')
			: this.area;
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
	
	if($('.user-iocn').length>0){
		$('.user-iocn').attr('src',val);
	}
}
UserInfo.prototype.setMark = function(val) {
	this.mark = val;
	localStorage.setItem('mark', val);
}
UserInfo.prototype.setRegTime = function(val) {
	this.regTime = val;
	localStorage.setItem('regTime', val);
}
UserInfo.prototype.setSex = function(val) {
	this.sex = val;
	localStorage.setItem('sex', val);
}
UserInfo.prototype.setArea = function(val) {
	this.area = val;
	localStorage.setItem('area', val);
}

UserInfo.prototype.getUserName = function() {
	return this.userName;
}
UserInfo.prototype.getImg = function() {
	return this.img;
}
UserInfo.prototype.getMark = function() {
	return this.mark;
}
UserInfo.prototype.getRegTime = function() {
	return this.regTime;
}
UserInfo.prototype.getSex = function() {
	return this.sex;
}
UserInfo.prototype.getArea = function() {
	return this.area;
}
UserInfo.prototype.loginOut = function(callback) {// 注销操作
	if (this.check()) {
		return;
	}
	this.setUserName();
	this.setImg();
	this.setArea();
	this.setMark();
	this.setRegTime();
	this.setSex();
	localStorage.clear();
	
	if (callback) {
		callback();
	}
	location.href='login.html';
	return;
}
UserInfo.prototype.login = function(callback) {
	var that = this;
	$.ajax({
		url : 'action',
		data : {
			method : 'get_userInfo_req',
			userName : that.getUserName()
		},
		success : function(data) {
			var obj = Ext.JSON.decode(data);
			that.setUserName(obj.userName);
			that.setImg(obj.img);
			that.setMark(obj.mark);
			that.setSex(obj.sex);
			that.setRegTime(obj.regTime);
			that.setArea(obj.area);

			if (callback) {
				callback();
			}
		}
	});
}
UserInfo.prototype.isLogin = function(win) {
	if (!this.check()&&win) {
		Ext.Msg.alert('提示', '用户  <font class="red-color">' + this.userName
				+ '</font> 已经登录，点击确定后进入聊天页面。', function() {
			location.href = 'chat.html';
			return;
		});
	}
	else if(this.check()&&!win){
		Ext.Msg.alert('提示', '请先登录！', function() {
			location.href = 'login.html';
			return;
		});
	}
	
}
UserInfo.prototype.isNoLogin=function(){
	if (!this.check()) {
		Ext.Msg.alert('提示', '用户  <font class="red-color">' + this.userName
				+ '</font> 已经登录，请先注销。', function() {
			history.back();
			return;
		});
	}
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
