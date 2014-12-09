function startWebSocket() {
	var url = 'ws://' + location.host + '/firstapp/chat/' + g_user.getUserName();
	ws = new WebSocket(url);

	ws.onmessage = function(evt) {
		var obj = Ext.JSON.decode(evt.data);
		if (obj.msgType == 'IM') {
			createIMLi(obj);
		}
		if (obj.msgType == 'ALERT') {
			createAlertWin(obj);
		}
		if (obj.msgType == 'CONFIRM') {
			createConfirmWin(obj);
		}
	};

	ws.onclose = function(evt) {
		// alert("close");
	};

	ws.onopen = function(evt) {
		// alert("open");
	};
}

function sendMsg(method, msg, friend, callback) {
	var obj = {
		method : method,
		msg : msg,
		friend : friend
	};
	ws.send(Ext.JSON.encode(obj));

	if (callback) {
		callback();
	}
}

function createIMLi(obj) {
	var li;
	var self = g_user.email;

	// 如果不是自己发起的消息，并且对话也不再当前对话框则不做操作，在聊天记录里面查找
	if (getFriendId() != obj.friend && obj.friend != self) {
		return;
	}

	if (obj.friend == self) {
		li += '<li>'
		li += '<img class="msg-user-img l" src="img/meinv.jpg" />';
		li += '<div class="msg-main-r-content msg-main-r-content-l l">';
		li += '<pre>' + obj.msg + '</pre>';
		li += '<i class="arrow_left"></i>';
		li += '</div>';
		li += '<small class="l">' + obj.time + '</small>';
		li += '</li>';
	} else {
		li += '<li>'
		li += '<img class="msg-user-img r" src="img/meinv.jpg" />';
		li += '<div class="msg-main-r-content msg-main-r-content-r r">';
		li += '<pre>' + obj.msg + '</pre>';
		li += '<i class="arrow_right"></i>';
		li += '</div>';
		li += '<small class="r">' + obj.time + '</small>';
		li += '</li>';
	}

	// 滑动到最新消息
	var ul = $('.msg-main-r ul');
	ul.append($(li));
	scrollButton();
}

// 弹出消息提示框
function createAlertWin(obj) {
	var win = Ext.create('Ext.window.Window', {
		title : '消息提示',
		height : 20,
		width : 250,
		html : '<p id="rb-tip">' + obj.msg + '</p>'
	});
	win.showAt($(window).width() - 250, $(window).height() - 50);
	win.animate({
		to : {
			height : 150,
			y : $(window).height() - 140
		}
	});
}
// 弹出确认提示框
function createConfirmWin(obj) {
	// var obj={from:'',fromName:'',to:'',toName:''} to是自己
	// sendMsg('receptFriendRequest', obj,'fromName');
	var win = Ext.create('Ext.window.Window', {
		title : '消息提示',
		height : 150,
		width : 250,
		layout : {
			type : 'border'
		},
		items : [ {
			xtype : 'panel',
			region : 'center',
			html : '<p id="rb-tip">' + obj.msg + '</p>',
			border : false,
			dockedItems : [ {
				xtype : 'toolbar',
				dock : 'bottom',
				items : [
						{
							xtype : 'button',
							margin : '0 50 0 50',
							width : 50,
							text : '接 受',
							listeners : {
								click : function() {
									sendMsg('receptFriendRequest',
											obj.friendId, obj.friend);
									win.close();
								}
							}
						}, {
							xtype : 'button',
							width : 50,
							text : '拒 绝',
							listeners : {
								click : function() {
									win.close();
								}
							}
						} ]
			} ]
		} ]
	});
	win.showAt($(window).width() - 250, $(window).height() - 50);
	win.animate({
		to : {
			height : 150,
			y : $(window).height() - 140
		}
	});
}