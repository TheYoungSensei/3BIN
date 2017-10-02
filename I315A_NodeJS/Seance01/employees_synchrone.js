const fs = require("fs");
const data = fs.readFileSync("employees.json");
var json = JSON.parse(data);
for(var employe = 0; employe < json.employees.length; employe++) {
    console.log(json.employees[employe].firstName + " " + json.employees[employe].lastName);
}
console.log("Job Done!");