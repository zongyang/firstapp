var ws;
function start_webSocket() {
	var url = 'ws://' + location.host + '/firstapp/chat/'
			+ g_user.getUserName();
	ws = new WebSocket(url);
	console.info(url);
	ws.onmessage = function(evt) {
		var obj = Ext.JSON.decode(evt.data);
		if (obj.msgType == 'IM') {
			create_IM_li(obj);
		}
		if (obj.msgType == 'ALERT') {
			create_alert_win("消息提示", obj.content, '');
		}
		if (obj.msgType == 'CONFIRM') {
			create_confirm_win(obj);
		}
	};

	ws.onclose = function(evt) {
		// alert("close");
	};

	ws.onopen = function(evt) {
		// alert("open");
	};
}

function send_msg(method, content, fromName, toName, callback) {
	var obj = {
		method : method,
		content : content,
		fromName : fromName,
		toName : toName
	};
	ws.send(Ext.JSON.encode(obj));

	if (callback) {
		callback();
	}
}
