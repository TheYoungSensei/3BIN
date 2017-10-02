const winston = require("winston");

module.exports.info = (message) => {
    var logger = new (winston.Logger)({
        transports : [
            new (winston.transports.Console)({'timestamp':true, 'colorize':true})
        ]
    });
    logger.info(message);
};



