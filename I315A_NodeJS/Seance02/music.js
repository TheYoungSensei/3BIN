const config = require("./modules/config");
const logger = require("./modules/logger");
const server = require("./modules/server");

config.load(() => {
    logger.info(config.ip);
    server.start(() => {
        logger.info("Server now listening on port " + config.port);
    });
});





