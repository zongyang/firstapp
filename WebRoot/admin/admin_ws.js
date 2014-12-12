function start_webSocket() {
	var url = 'ws://' + location.host + '/firstapp/chat/'
			+ g_user.getUserName();
	ws = new WebSocket(url);

	ws.onmessage = function(evt) {
		var obj = Ext.JSON.decode(evt.data);
		if (obj.msgType) {
			Ext.Msg.alert('提示','消息已发出！');
		}
		else{
			Ext.Msg.alert('提示','消息发送失败！');
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

