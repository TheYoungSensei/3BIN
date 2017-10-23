var express = require("express");
var config = require("./config");
var app;
module.exports.start = (callBack) => {
    _configureServer();
    _configureRoutes();
    callBack();
}; 

module.exports.stop = (callBack) => {
    callBack();
}

function _configureServer() {
    app = express();
}

function _configureRoutes() {
    app.get("/", function(req, res) {
        res.send("Hello World !");
    }).get("*", function (req, res) {
        res.send("Sorry can't find that !", 404);
    });
    app.listen(config.port);
}