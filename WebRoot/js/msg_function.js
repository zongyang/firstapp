$(function(){
	//left
	$('.msg-main-l li').click(liClick);
	//middle
	$('.msg-main-m li').click(liClick);
	//right
})

function liClick(){
	var self=$(this);
	self.addClass('active');
	self.siblings().removeClass('active');
}
function getFriendList(){
	
}