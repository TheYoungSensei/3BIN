const http = require("http");
var server = http.createServer(function(req, res) {
    res.writeHead(200);
    res.end("Hello World");
});
server.listen("80");
console.log("Server now listening on port 80")