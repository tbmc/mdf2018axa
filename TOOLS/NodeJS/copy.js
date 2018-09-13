const fs = require("fs");
const path = require("path");
const {copy} = require("copy-paste");

const file = fs.readFileSync(path.resolve("./dist/bundle.js"), {encoding: "utf8"});
copy(file);