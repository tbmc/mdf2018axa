const content = require('./content');
const _ = require("lodash");
const fs = require("fs");
const path = require("path");

const files = fs.readdirSync(path.resolve(path.join(__dirname, "examples")));
const fileGroup = _.groupBy(files, (file) => path.parse(file).name.replace(/input|output/, "")/1);
const jsonContents = Object.values(fileGroup).map(([input, output]) => {
    const fileInput = path.resolve(`${__dirname}/examples/${input}`);
    const fileOutput = path.resolve(`${__dirname}/examples/${output}`);
    const inputsJson = fs.readFileSync(fileInput, "utf-8").split("\n").filter(Boolean);
    const outputsJson = fs.readFileSync(fileOutput, "utf-8").split("\n").filter(Boolean);
    return [inputsJson, outputsJson];
});
describe("tests", () => {
    window.LocalPrint = () => {};
    window.LocalPrintArray = () => {};


    jsonContents.forEach(([input, outputs]) => {
        test("process test", () => {
            spyOn(console, "log").and.callThrough();

            content(input);

            outputs.forEach((output, index) => expect(console.log.calls.argsFor(index))
                .toEqual([isNaN(output) ? output: output/1]))

        })
    });
});