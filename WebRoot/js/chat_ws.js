function start_webSocket() {
	var url = 'ws://' + location.host + '/firstapp/chat/'
			+ g_user.getUserName();
	ws = new WebSocket(url);

	ws.onmessage = function(evt) {
		var obj = Ext.JSON.decode(evt.data);
		if (obj.msgType == 'IM') {
			create_IM_li(obj);
		}
		if (obj.msgType == 'ALERT') {
			create_alert_win(obj);
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

function sendMsg(method, msg, from, to, callback) {
	var obj = {
		method : method,
		msg : msg,
		from : from,
		to : to
	};
	ws.send(Ext.JSON.encode(obj));

	if (callback) {
		callback();
	}
}

function create_IM_li(obj) {
	var li;
	var self = g_user.getUserName();
	var curr_frend = get_select_friend_name();

	if (self == obj.from) {// 自己是消息发起人
		li += '<li>'
		li += '<img class="user-iocn msg-user-img l" src="' + g_user.getImg() + '" />';
		li += '<div class="msg-main-r-content msg-main-r-content-l l">';
		li += '<pre>' + obj.msg + '</pre>';
		li += '<i class="arrow_left"></i>';
		li += '</div>';
		li += '<small class="l">' + obj.time + '</small>';
		li += '</li>';
	}

	else if (self == obj.to) {// 自己是消息接收人
		if (curr_frend == obj.from) {// 当前的对话是和发起聊天的好友
			li += '<li>'
			li += '<img class="user-iocn msg-user-img r" src="'+get_select_friend_img()+'" />';
			li += '<div class="msg-main-r-content msg-main-r-content-r r">';
			li += '<pre>' + obj.msg + '</pre>';
			li += '<i class="arrow_right"></i>';
			li += '</div>';
			li += '<small class="r">' + obj.time + '</small>';
			li += '</li>';
		}
		else{//不是当前好友则弹框
			create_alert_win('来自 '+obj.from+' 的消息',obj.msg,obj.from);
		}
	}
	// 滑动到最新消息
	var ul = $('.msg-main-r ul');
	ul.append($(li));
	scroll_button();
}

// 弹出消息提示框
function create_alert_win(title,msg,friend) {
	var win = Ext.create('Ext.window.Window', {
		title : title,
		height : 20,
		width : 250,
		html : '<p class="r-b-tip">' + msg + '</p>'
	});
	win.showAt($(window).width() - 250, $(window).height() - 50);
	win.animate({
		to : {
			height : 150,
			y : $(window).height() - 140
		}
	});
	//点击后显示与该好友的聊天
	$('.r-b-tip').click(function(){
		
		var h5s=$('.msg-main-m li h5');
		for(var i=0;i<h5s.length;i++){
			if($(h5s[i]).text()==friend){
				$(h5s[i]).parentsUntil('ul').click();
				win.close();
			}
		}
	});
}
// 弹出确认提示框
function create_confirm_win(obj) {
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