os = require('os');
var cpus = os.cpus();
console.log("Number of cpus : " + cpus.length);
console.log("CPU model : " + cpus[0].model);
console.log("Hostname : " + os.hostname());
console.log("Memory : " + os.totalmem());
console.log("Uptime : " + os.uptime());