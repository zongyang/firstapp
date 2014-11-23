
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
function checkObjNull(obj){
	for(var i in obj){
		if(checkNull(obj[i])){
			return true
		}
	}
	return false;
}
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