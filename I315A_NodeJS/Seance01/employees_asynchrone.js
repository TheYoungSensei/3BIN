const fs = require("fs");
fs.readFile("employees.json", (err, data) => {
    var json = JSON.parse(data);
    for(var employe = 0; employe < json.employees.length; employe++) {
        console.log(json.employees[employe].firstName + " " + json.employees[employe].lastName);
    }
    if(err) throw err;
});
console.log("Job Done!");