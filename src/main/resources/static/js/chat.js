var stompClient = null;


function connect() {
	var socket = new SockJS('/Chatroom/MYChatroom');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/Chatroom', function(response) {
			console.log(response)
			showConversation(JSON.parse(response.body).responseMessage); //
		});
	});
}



//關閉WebSocket方法
function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

//傳送訊息方法
function sendMessage() {
	var name = $("#name").val();
	var dialogue = $("#dialogue").val();
	var Time = sendTime();
	console.log(name);
	console.log(dialogue);
	console.log(Time);
	console.log(stompClient);
	var sendjson = { 'name': name, 'dialogue': dialogue, 'time': Time };
	stompClient.send("/messageControl", {}, JSON.stringify(sendjson, ['name', 'dialogue', 'time']));
}
//顯示接收回來的訊息方法
function showConversation(responseMessage) {
	$("#Chatroom").append("<tr><td>" + responseMessage + "</td></tr>");
	var chatdiv = document.querySelector('.chat');
	chatdiv.scrollTop = chatdiv.scrollHeight;
}

function sendTime() {
	var date = new Date();
	var Min = date.getMinutes();
	var hours = date.getHours();
	var day = date.getDate();
	var month = date.getMonth() + 1;
	var year = date.getFullYear();
	if (month < 10) month = "0" + month;
	if (day < 10) day = "0" + day;
	if (hours < 10) hours = "0" + hours;
	if (Min < 10) Min = "0" + Min;
	var today = year + "/" + month + "/" + day + " " + hours + ":" + Min;
	return today;
}

$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$("#connect").click(function() { connect(); });
	$("#disconnect").click(function() { disconnect(); });
	$("#send").click(function() { sendMessage(); });
});