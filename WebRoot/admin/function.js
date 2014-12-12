function login_click() {
	var name = Ext.getCmp('lb_name');
	var password = Ext.getCmp('lb_password');

	// if (!name.isValid() || !password.isValid()) {
	// return;
	// }

	Ext.Ajax.request({
		url : '../admin',
		params : {
			method : 'admin_login',
			name : name.getValue(),
			password : password.getValue()

		},
		success : function(response) {
			var obj = Ext.JSON.decode(response.responseText);
			if (obj.success === false) {
				Ext.Msg.alert('提示', obj.msg);
			}

			if (obj.success === true) {
				Ext.Msg.alert('提示', obj.msg, function() {
					Ext.getCmp('login_win').close();
					Ext.create('MyApp.view.main_win').show();
					Ext.StoreManager.get('user').load();
					Ext.StoreManager.get('admin').load();
					//开启websocket
					start_webSocket();
				});
			}
		}
	});
}

function add_admin_click() {
	var name = Ext.getCmp('lb_add_name');
	var password = Ext.getCmp('lb_add_password');

	if (!name.isValid() || !password.isValid()) {
		return;
	}

	Ext.Ajax.request({
		url : '../admin',
		params : {
			method : 'add_admin',
			strModel : Ext.JSON.encode({
				name : name.getValue(),
				password : password.getValue()
			})
		},
		success : function(response) {
			var obj = Ext.JSON.decode(response.responseText);
			if (obj.success === false) {
				Ext.Msg.alert('提示', obj.msg);
			}

			if (obj.success === true) {
				Ext.Msg.alert('提示', obj.msg, function() {
					Ext.getCmp('add_admin_win').close();
					Ext.StoreManager.get('admin').load();
				});
			}
		}
	});
}

function get_admin_name(callback) {
	Ext.Ajax.request({
		url : '../admin',
		params : {
			method : 'get_admin_name'
		},
		success : function(response) {
			if (callback) {
				callback(response.responseText);
			}
		}
	});
}

function send_to_all() {
	var tabpanel = Ext.getCmp('tab_panel');
	var textarea = tabpanel.getActiveTab().down('textarea');
	if (!textarea.isValid()) {
		return;
	}

	send_msg("send_system_info", textarea.getValue(), '系统消息', '');
}

function start_webSocket() {
	get_admin_name(function(admin) {
		var url = 'ws://' + location.host + '/firstapp/chat/'
				+ admin;
		ws = new WebSocket(url);

		ws.onmessage = function(evt) {
			var obj = Ext.JSON.decode(evt.data);
			if (obj.msgType) {
				Ext.Msg.alert('提示', '消息已发出！');
			} else {
				Ext.Msg.alert('提示', '消息发送失败！');
			}

		};

		ws.onclose = function(evt) {
			// alert("close");
		};

		ws.onopen = function(evt) {
			// alert("open");
		};
	});

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
