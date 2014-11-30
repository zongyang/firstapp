function startWebSocket() {
	var url = 'ws://' + location.host + '/firstapp/chat/' + g_user.email;
	ws = new WebSocket(url);

	ws.onmessage = function(evt) {
		createChatLi(JSON.parse(evt.data));
	};

	ws.onclose = function(evt) {
		// alert("close");
	};

	ws.onopen = function(evt) {
		// alert("open");
	};
}

function sendMsg(method,msg,friend,callback) {
	var obj={method:method,msg:msg,friend:friend};
	ws.send(JSON.stringify(obj));
	
	if(callback){
		callback();
	}
}

function createChatLi(obj) {
	var li;
	var self = g_user.email;

	//如果不是自己发起的消息，并且对话也不再当前对话框则不做操作，在聊天记录里面查找
	if (getFriendId() != obj.friend
			&& obj.friend != self) {
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
    
	//滑动到最新消息
	var ul=$('.msg-main-r ul');
	ul.append($(li));
	scrollButton();
}


