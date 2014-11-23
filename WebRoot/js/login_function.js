$(function() {
	// header
	$('.sg-header span').click(headerClick);
	// body-left
	// body-fight
});

function headerClick(event) {
	var self = $(this);
	self.addClass('sg-header-span-click');
	self.siblings().removeClass('sg-header-span-click');

	var body_index = getComIndex($('.sg-header span'), this)
	var sg_body = $('.sg-body:nth(' + body_index + ')').removeClass('hide');
	sg_body.siblings().addClass('hide');
}
function loginClick(event){
	var inputs=$(.'sg-body-l input[type=text]');
	var email=$
}

function regClick(){
	
}