
var ws;
function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	}
	else {
		$("#conversation").hide();
	}
	$("#messages").html("");
}

function connect() {
	ws = new WebSocket('ws://192.168.1.100:8080/api/ws');
	ws.onmessage = function (response) {
		console.log(response.data);
		msg = JSON.parse(response.data);
		if (msg.id == "Ping") {
			payload = {
				id: "Pong",
				body: ""
			};
			ws.send(JSON.stringify(payload));
		}
	}
	setConnected(true);
}

function disconnect() {
	if (ws != null) {
		ws.close();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendMessage(msg) {
	//var msg = JSON.stringify({"user": $("#name").val(), "message": $("#message").val()})
	ws.send(msg);
}

function showMessages(msg) {
	console.log(msg);
	if (msg == "Ping") {
		message = "Pong";
		sendMessage(message);
	}
	//$("#messages").append(`<tr><td><strong>${msg.user}</strong>: ${msg.message}</td></tr>`);
}

$(function () {
	$("form").on('submit', function (e) {
		e.preventDefault();
	});
	$("#connect").click(function () { connect(); });
	$("#disconnect").click(function () { disconnect(); });
	$("#send").click(function () { sendMessage(); });
});

