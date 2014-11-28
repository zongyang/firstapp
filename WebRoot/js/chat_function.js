$(function() {
	// left
	$('.msg-main-l li').click(liClick);
	$('.msg-main-l-friend').click(getFriendList);
	// middle
	$('.msg-main-m li').click(liClick);
	// right
});

// 左侧和中间所有li的样式改变
function liClick() {
	var self = $(this);
	self.addClass('active');
	self.siblings().removeClass('active');
}
// 获得好友列表
function getFriendList(event) {
	$.ajax({
		url : 'action',
		data : {
			id : g_user.id,
			method : 'getFriendBy'
		},
		success : function(data) {
			var obj = JSON.parse(data);

			if (!obj.success) {
				alert(obj.msg);
				return;
			}

			createFriendList(JSON.parse(obj.msg));
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
		}
	});
}
//根据用户id获得聊天记录
function getChatByUser(){}
//创建主聊天的快
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
//创建用户中间聊天的记录块
function createMidChat(arr){
	var lis = '';
	var el = $('.msg-main-m ul');
	el.empty();
	for (var i = 0; i < arr.length; i++) {
		lis += '<li>';
		lis += '<img class="msg-user-img l" src="img/meinv.jpg" />';
		lis += '<div class="l">';
		lis += '<h5 user="' + arr[i].id + '">' + arr[i].content + '</h5>';
		lis += '<p>'+arr[i].content+'</p>';
		lis += '</div>';
		lis += '</li>';
	}
	el.append(lis);
	el.find('li').click(getChatByFriend);
}