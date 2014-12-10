


// 发送消息按钮点击事件
function send_msg_click() {
	var msg = $('#chart-input').val();
	if (get_select_friend_name() == "") {
		Ext.Msg.alert('警告', '请选择好友、消息不能为空！');
		return;
	}
	if (msg == "") {
		Ext.Msg.alert('警告', '请选择好友、消息不能为空！');
		$('#chart-input').val('');
		return;
	}

	var friend = send_msg('send_msg_to_friend', msg.trim(), g_user.getUserName(),
			get_select_friend_name(), function() {
				// 发送后清空内容
				$('#chart-input').val('');
			});
}

//右侧菜单的点击事件
function msg_main_l_friend_click() {
	$.ajax({
		url : 'action',
		data : {
			userName : g_user.getUserName(),
			method : 'get_friend_lsit_req'
		},
		success : function(data) {
			var obj = Ext.JSON.decode(data);
			if (obj.success==false) {
				Ext.Msg.alert('提示',obj.msg);
				return;
			}
			create_friend_lis(obj.msg, 1);
			
		}
	});
}

//右侧菜单的点击事件
function msg_main_l_recent_click() {
	$.ajax({
		url : 'action',
		data : {
			method : 'get_chat_latest_record',
			userName : g_user.getUserName()
		},
		success : function(data) {
			var obj = Ext.JSON.decode(data);

			if (obj.success == false) {
				alert(obj.msg);
				return;
			}
			create_friend_lis(obj, 0);

		}
	});
}

function msg_main_l_add_click() {
	var el = $('.msg-main-m ul');
	el.empty();
	Ext.define('User', {
		extend : 'Ext.data.Model',
		fields : [ {
			name : 'userName'
		} ]
	});

	var store = Ext.create('Ext.data.Store', {
		model : 'User',
		proxy : {
			type : 'ajax',
			url : 'action',
			extraParams : {
				method : 'get_all_userName_req'
			},
			reader : {
				type : 'json'
			}
		}
	});

	var panel = Ext.create('Ext.panel.Panel', {
		width : 320,
		border : false,
		renderTo : el[0],

		layout : {
			align : 'stretch',
			type : 'hbox'
		},
		bodyStyle : {
			background : 'transparent'
		},
		titleAlign : 'center',
		items : [ {
			xtype : 'combobox',
			width : 200,
			margin : '30 20',
			id : 'btn-friend',
			typeAhead : true,
			minChars : 1,
			fieldLabel : '',
			store : store,
			displayField : 'userName',
			valueField : 'userName'
		}, {
			xtype : 'button',
			margin : '30 0',
			text : '添 加',
			listeners : {
				click : function(button, e, eOpts) {
					add_friend();
				}
			}
		} ]
	});
	return panel;
}


// 中间好友的点击事件
function msg_main_l_li_click(event) {

	$.ajax({
		url : 'action',
		data : {
			self:g_user.getUserName(),
			friend : get_select_friend_name(),
			method : 'get_chat_record'
		},
		success : function(data) {
			var obj = Ext.JSON.decode(data);
			if (obj.success==false) {
				Ext.Msg.alert('提示',obj.msg);
				return;
			}
			//初始化聊天记录
			clear_msg_main_l_li();
			for (var i = 0; i < obj.length; i++) {
				create_IM_li(obj[i]);
			}
		}
	});

}
//中间好友的鼠标右键事件
function firend_mousedown(event) {

	if (event.which != 3) {
		return;
	}

	var userName = $(event.currentTarget).find('h5').text();
	var contextmenu = new Ext.menu.Menu(
			{

				items : [ {
					text : '查看 <font class="blue-clor">' + userName
							+ '</font> 详情',
					handler : function() {

						$
								.ajax({
									url : 'action',
									data : {
										method : 'get_userInfo_req',
										userName : userName
									},
									success : function(data) {
										var obj = Ext.JSON.decode(data);

										var html = '<table class="friend-table">';
										html += '<tr><td class="text-right"><img class="user-iocn" src="'
												+ obj.img + '"/></td></tr>';
										html += '<tr><td class="text-right">用户名：</td><td>'
												+ obj.userName
												+ '</td ></td></tr>';
										html += '<tr><td class="text-right">性     别：</td><td>'
												+ obj.sex + '</td></tr>';
										html += '<tr><td class="text-right">注册时间：</td><td>'
												+ obj.regTime + '</td></tr>';
										html += '<tr><td class="text-right">地     址：</td><td>'
												+ obj.area + '</td></tr>';
										html += '<tr><td class="text-right">个性签名：</td><td>'
												+ obj.mark + '</td></tr>';
										html += '</table>';

										Ext
												.create(
														'Ext.window.Window',
														{
															width : 400,
															bodyStyle : {
																background : 'rgb(231, 226, 223)'
															},
															title : obj.userName
																	+ ' 的详细信息',
															html : html
														}).show();

									}
								});
					}
				} ]
			});

	contextmenu.showAt(event.clientX, event.clientY);
	event.preventDefault();
}

function add_friend() {
	var combox = Ext.getCmp('btn-friend');
	// 推送加好友请求
	send_msg('send_add_req_to_friend', '', g_user.getUserName(), combox.getRawValue());
}



