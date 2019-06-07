var express = require('express');
var app = express();
var expressWs = require('express-ws')(app);

let clients = [];
app.ws('/dist', function (ws, req) {
	clients.push(ws);
	ws.on('message', function (msg) {
		clients.forEach(c => c.send(msg));
	});
	ws.on('close', () => {
		clients.splice(clients.indexOf(ws));
	});
});

console.log('Kwetter WebSockets listening on port 3001!');
app.listen(3001);
