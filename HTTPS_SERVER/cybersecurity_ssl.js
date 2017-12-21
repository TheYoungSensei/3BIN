#!/usr/bin/env node

var https = require('https');
var fs = require('fs');
var express = require('express');

var options = {
    ca: fs.readFileSync('/root/ca/certs/ca.cert.pem'),
    key: fs.readFileSync('/root/ca/private/www.startup.ipl.be.key.pem'),
    cert: fs.readFileSync('/root/ca/certs/www.startup.ipl.be.cert.pem'),
    requestCert: false,
    rejectUnauthorized: false,
    passphrase: 'IPL4ever'
};

var app = express();
var port = process.env.PORT || 443;
var server = https.createServer( options, app );

server.listen( port, function () {
    console.log( 'Express server listening on port ' + server.address().port );
} );
