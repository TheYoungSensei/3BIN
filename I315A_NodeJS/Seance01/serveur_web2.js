var express = require("express");
var app = express()
app.get('/', function(req, res) {

    res.send("Hello World");
}).get('/nodejs', function(req, res) {
    res.send("J'adore Node.js");
}).post('/nodejs', function(req, res) {
    res.send("Hoooo monsieur sait faire des POSTs");
});
app.listen(80);
console.log("Server now listening on port 80");