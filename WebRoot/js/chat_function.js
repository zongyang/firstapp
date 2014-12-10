//创建左边聊天条
function create_IM_left(obj) {
	var li;
	li += '<li>'
	li += '<img class="user-iocn msg-user-img l" src="' + g_user.getImg()
			+ '" />';
	li += '<div class="msg-main-r-content msg-main-r-content-l l">';
	li += '<pre>' + obj.content + '</pre>';
	li += '<i class="arrow_left"></i>';
	li += '</div>';
	li += '<small class="l">' + obj.time + '</small>';
	li += '</li>';

	// 滑动到最新消息
	var ul = $('.msg-main-r ul');
	ul.append($(li));
	scroll_button();
}
// 创建右边聊天条
function create_IM_right(obj) {
	var li;
	li += '<li>'
	li += '<img class="user-iocn msg-user-img r" src="'
			+ get_select_friend_img() + '" />';
	li += '<div class="msg-main-r-content msg-main-r-content-r r">';
	li += '<pre>' + obj.content + '</pre>';
	li += '<i class="arrow_right"></i>';
	li += '</div>';
	li += '<small class="r">' + obj.time + '</small>';
	li += '</li>';
	// 滑动到最新消息
	var ul = $('.msg-main-r ul');
	ul.append($(li));
	scroll_button();
}
// 创建好友列表
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
	el.find('li').on('contextmenu', firend_mousedown).click(li_click).click(
			msg_main_l_li_click);
}

// 清除消息条
function clear_msg_main_l_li() {
	$('.msg-main-r ul').empty();
};

// 获得选中好友的名字
function get_select_friend_name() {
	return $('.msg-main-m li.active h5').text();
}
// 获得选中好友的头像
function get_select_friend_img() {
	return $('.msg-main-m li.active img').attr('src');
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

// 创建聊天消息条
function create_IM_li(obj) {
	var li;
	var self = g_user.getUserName();
	var curr_frend = get_select_friend_name();

	if (self == obj.fromName) {// 自己是消息发起人
		create_IM_left(obj);
	}

	else if (self == obj.toName) {// 自己是消息接收人
		if (curr_frend == obj.fromName) {// 当前的对话是和发起聊天的好友
			create_IM_right(obj);
		} else {// 不是当前好友则弹框
			create_alert_win('来自 ' + obj.fromName + ' 的消息', obj.content,
					obj.fromName);
		}
	}

}

// 弹出消息提示框
function create_alert_win(title, content, friend) {
	var win = Ext.create('Ext.window.Window', {
		title : title,
		height : 20,
		width : 250,
		html : '<p class="r-b-tip blue-color">' + content + '</p>'
	});
	win.showAt($(window).width() - 250, $(window).height() - 50);
	win.animate({
		to : {
			height : 150,
			y : $(window).height() - 140
		}
	});
	// 点击后显示与该好友的聊天
	$('.r-b-tip').click(function() {

		var h5s = $('.msg-main-m li h5');
		for (var i = 0; i < h5s.length; i++) {
			if ($(h5s[i]).text() == friend) {
				$(h5s[i]).parentsUntil('ul').click();
				win.close();
			}
		}
	});
}
// 弹出确认提示框
function create_confirm_win(obj) {

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
			html :'<p class="r-b-tip blue-color">' + obj.content + '</p>',
			border : false,
			dockedItems : [ {
				xtype : 'toolbar',
				dock : 'bottom',
				items : [ {
					xtype : 'button',
					margin : '0 30 0 50',
					width : 50,
					text : '接 受',
					listeners : {
						click : function() {
							//obj.fromName = g_user.getUserName();
							//obj.toName = '';
							send_msg('accepet_add_req','',obj.fromName,obj.toName);
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