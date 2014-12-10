$(function() {
	// left
	$('.msg-main-l li').click(li_click);
	$('.msg-main-l-friend').click(msg_main_l_friend_click);
	$('.msg-main-l-recent').click(msg_main_l_recent_click);
	// 发送消息
	$('#btn-send-msg').click(send_msg_click);
	$('#chart-input').keydown(function(event) {
		if (event.keyCode == 13) {
			send_msg_click();
		}
	});
	start_webSocket();
	return;

	$('.msg-main-l-recent').click(get_chat_latest_record);
	$('.msg-main-l-add').click(getAddPanel);
	// middle
	$('.msg-main-m li').click(li_click);
	// right

});

function create_IM_left(obj) {
	var li;
	li += '<li>'
	li += '<img class="user-iocn msg-user-img l" src="' + g_user.getImg()
			+ '" />';
	li += '<div class="msg-main-r-content msg-main-r-content-l l">';
	li += '<pre>' + obj.msg + '</pre>';
	li += '<i class="arrow_left"></i>';
	li += '</div>';
	li += '<small class="l">' + obj.time + '</small>';
	li += '</li>';

	// 滑动到最新消息
	var ul = $('.msg-main-r ul');
	ul.append($(li));
	scroll_button();
}
function create_IM_right(obj) {
	var li;
	li += '<li>'
	li += '<img class="user-iocn msg-user-img r" src="'
			+ get_select_friend_img() + '" />';
	li += '<div class="msg-main-r-content msg-main-r-content-r r">';
	li += '<pre>' + obj.msg + '</pre>';
	li += '<i class="arrow_right"></i>';
	li += '</div>';
	li += '<small class="r">' + obj.time + '</small>';
	li += '</li>';
	// 滑动到最新消息
	var ul = $('.msg-main-r ul');
	ul.append($(li));
	scroll_button();
}
function create_friend_lis(arr, type) {// type:0(消息),1(签名)
	var lis = '';
	var el = $('.msg-main-m ul');
	el.empty();
	for (var i = 0; i < arr.length; i++) {
		lis += '<li title="右键查看 ' + arr[i].userName + ' 的详细信息">';
		lis += '<img class="user-iocn l" src="' + arr[i].img + '" />';
		lis += '<div class="l">';
		lis += '<h5>' + arr[i].userName + '</h5>';
		if (type == 0) {
			lis += '<p  class="blue-color"><span class="red-color">'
					+ arr[i].fromName + ':</span> ' + arr[i].content + '</p>';
		}
		if (type == 1) {
			lis += '<p  class="blue-color">' + arr[i].mark + '</p>';
		}
		lis += '</div>';
		lis += '</li>';
	}
	el.append(lis);
	el.find('li').on('contextmenu', firend_mousedown).click(li_click).click(msg_main_l_li_click);
}

function get_select_friend_name() {
	return $('.msg-main-m li.active h5').text();
}
function get_select_friend_img() {
	return $('.msg-main-m li.active img').attr('src');
}
// 发送按钮
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

	var friend = sendMsg('sendToFriend', msg.trim(), g_user.getUserName(),
			get_select_friend_name(), function() {
				// 发送后清空内容
				$('#chart-input').val('');
			});
}

//

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

// 右键查看好友信息
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
// 创建主聊天窗口
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
			debugger;
			for (var i = 0; i < obj.length; i++) {
				create_IM_li(obj[i]);
			}
		}
	});

}
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
// 滑到消息最底部
function scroll_button() {
	var ul = $('.msg-main-r ul');
	var li = ul.find('li:last');
	if (li.length > 0) {
		ul.scrollTop(0).scrollTop(li.offset().top);
	}

}
// 左侧和中间所有li的样式改变
function li_click() {
	var self = $(this);
	self.addClass('active');
	self.siblings().removeClass('active');
}

// 获得最近消息记录

// 获得好友列表
function getFriendByUser(event) {
	$.ajax({
		url : 'action',
		data : {
			id : g_user.id,
			method : 'getFriendByUser'
		},
		success : function(data) {
			var obj = JSON.parse(data);

			if (!obj.success) {
				alert(obj.msg);
				return;
			}

			createFriendList(JSON.parse(obj.msg));
			$('.msg-main-m li').click(li_click);

		}
	});
}
// 根据好友id获得聊天记录
function getChatByFriend(event) {
	var friend = $(event.currentTarget).find('h5').attr('user');
	$.ajax({
		url : 'action',
		data : {
			user : g_user.id,
			friend : friend,
			method : 'getChatByFriend'
		},
		success : function(data) {
			var obj = JSON.parse(data);

			if (!obj.success) {
				alert(obj.msg);
				return;
			}

			createMainChat(JSON.parse(obj.msg));
			scroll_button();
		}
	});
}
// 根据用户id获得聊天记录
function getChatByUser() {
	$.ajax({
		url : 'action',
		data : {
			id : g_user.id,
			method : 'getChatByUser'
		},
		success : function(data) {
			var obj = JSON.parse(data);

			if (obj.success==false) {
				alert(obj.msg);
				return;
			}

			createMidChat(JSON.parse(obj.msg));
			$('.msg-main-m li').click(li_click);
			scroll_button();
		}
	});
}
// 创建主聊天的快
function createMainChat(arr) {
	var lis = '';
	var el = $('.msg-main-r ul');
	el.empty();

	for (var i = 0; i < arr.length; i++) {
		lis += '<li>';
		if (g_user.id == arr[i].from) {
			lis += '<img class="msg-user-img l" src="img/meinv.jpg" />';
			lis += '<div class="msg-main-r-content msg-main-r-content-l l">';
			lis += '<pre>' + arr[i].content + '</pre>';
			lis += '<i class="arrow_left"></i>';
			lis += '</div>';
			lis += '<small class="l">' + arr[i].time + '</small>';
		} else {
			lis += '<img class="msg-user-img r" src="img/meinv.jpg" />';
			lis += '<div class="msg-main-r-content msg-main-r-content-r r">';
			lis += '<pre>' + arr[i].content + '！</pre>';
			lis += '<i class="arrow_right"></i>';
			lis += '</div>';
			lis += '<small class="r">' + arr[i].time + '</small>';
		}
		lis += '</li>';
	}
	el.append(lis);

}
// 创建好友列表的块
function createFriendList(arr) {
	var lis = '';
	var el = $('.msg-main-m ul');
	el.empty();
	for (var i = 0; i < arr.length; i++) {
		lis += '<li>';
		lis += '<img class="msg-user-img l" src="img/meinv.jpg" />';
		lis += '<div class="l">';
		lis += '<h5 user="' + arr[i].id + '">' + arr[i].nickName + '</h5>';
		lis += '<p></p>';
		lis += '</div>';
		lis += '</li>';
	}
	el.append(lis);
	el.find('li').click(getChatByFriend);
}
// 创建用户中间聊天的记录块
function createMidChat(arr) {
	var lis = '';
	var el = $('.msg-main-m ul');
	el.empty();
	for (var i = 0; i < arr.length; i++) {
		lis += '<li>';
		lis += '<img class="msg-user-img l" src="img/meinv.jpg" />';
		lis += '<div class="l">';
		lis += '<h5 user="' + arr[i].friend + '">' + arr[i].friendName
				+ '</h5>';
		lis += '<p>' + arr[i].content + '</p>';
		lis += '</div>';
		lis += '</li>';
	}
	el.append(lis);
	el.find('li').click(getChatByFriend);
}

// 添加好友框
function getAddPanel() {
	var el = $('.msg-main-m ul');
	el.empty();
	Ext.define('User', {
		extend : 'Ext.data.Model',
		fields : [ {
			name : 'email'
		}, {
			name : 'id'
		} ]
	});

	var store = Ext.create('Ext.data.Store', {
		model : 'User',
		proxy : {
			type : 'ajax',
			url : 'action',
			extraParams : {
				email : g_user.email,
				method : 'getAllUser'
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
			displayField : 'email',
			valueField : 'id'
		}, {
			xtype : 'button',
			margin : '30 0',
			text : '添 加',
			listeners : {
				click : function(button, e, eOpts) {
					addFriend();
				}
			}
		} ]
	});
	return panel;
}

function addFriend() {
	var combox = Ext.getCmp('btn-friend');
	var friend = {
		from : g_user.id,
		to : combox.getValue()
	};
	// 推送加好友请求
	sendMsg('friendRequest', '', combox.getRawValue());
	/*
	 * $.ajax({ url : 'action', data : { method:'addFriend', friend
	 * :Ext.JSON.encode(friend) }, success : function(data) { var obj =
	 * Ext.JSON.decode(data); } });
	 */
}