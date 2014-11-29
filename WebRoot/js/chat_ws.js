

function startWebSocket() {
	var url = 'ws://' + location.host + '/firstapp/chat';
	ws = new WebSocket(url);
	
	ws.onmessage = function(evt) {
		debugger;
		alert(evt.data);
	};

	ws.onclose = function(evt) {
		alert("close");
	};

	ws.onopen = function(evt) {
		alert("open");
	};
}

function sendMsg(str){
	ws.send('love love');   
}