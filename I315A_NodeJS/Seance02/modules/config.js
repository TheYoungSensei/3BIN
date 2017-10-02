const fs = require('fs');
const file = "./config.json"
const logger = require("./logger");

module.exports["load"] = load;

function load(callBack) {
    logger.info("[Config] Start loading config file : " + file);
    fs.readFile(file, (err, data) => {
        var json = JSON.parse(data);
        for(var key in json) {
            module.exports[key] = json[key]; 
        }
        callBack();
        if(err) throw err; 
    });
    logger.info("[Config] Config file " + file + " loaded");
}