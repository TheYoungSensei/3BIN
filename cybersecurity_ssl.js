#!/usr/bin/env node

var https = require('https');
var fs = require('fs');
var express = require('express');

var options = {
    key: fs.readFileSync( './www.startup.ipl.be.key.pem'),
    cert: fs.readFileSync( './www.startup.ipl.be.cert.pem'),
    requestCert: false,
    rejectUnauthorized: false
};

var app = express();
var port = process.env.PORT || 443;
var server = https.createServer( options, app );

server.listen( port, function () {
    console.log( 'Express server listening on port ' + server.address().port );
} );
