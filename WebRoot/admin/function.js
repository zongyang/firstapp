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
			// name : name.getValue(),
			// password : password.getValue()

			name : 'admin',
			password : '123456'

		},
		success : function(response) {
			var obj = Ext.JSON.decode(response.responseText);
			if (obj.success === false) {
				Ext.Msg.alert('提示', obj.msg);
			}
			Ext.getCmp('login_win').close();
			Ext.create('MyApp.view.main_win').show();
			Ext.StoreManager.get('user').load();
			Ext.StoreManager.get('admin').load();
			return;
			if (obj.success === true) {
				Ext.Msg.alert('提示', obj.msg, function() {
					Ext.getCmp('login_win').close();
					Ext.create('MyApp.view.main_win').show();
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
			if(callback){
				callback(response.responseText);
			}
		}
	});
}