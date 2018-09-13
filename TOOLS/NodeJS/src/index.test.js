import content from "./content";
import _ from "lodash";
import fs from "fs";
import path from "path";

const files = fs.readdirSync(path.resolve(path.join(__dirname, "examples"))).filter((filename) => !filename.startsWith("."));
const fileGroup = _.groupBy(files, (file) => path.parse(file).name.replace(/input|output/, "")/1);
const jsonContents = Object.values(fileGroup).map(([input, output]) => {
    const fileInput = path.resolve(`${__dirname}/examples/${input}`);
    const fileOutput = path.resolve(`${__dirname}/examples/${output}`);
    const inputsJson = fs.readFileSync(fileInput, "utf-8").split("\n").filter(Boolean);
    const outputsJson = fs.readFileSync(fileOutput, "utf-8").split("\n").filter(Boolean);
    return [inputsJson, outputsJson];
});
describe("tests", () => {
    window.LocalPrint = (log) => console.info(log);
    window.LocalPrintArray = (...log) => console.info(...log);

    jsonContents.forEach(([input, outputs]) => {
        test("process test", () => {
            spyOn(console, "log").and.callThrough();

            content(input);

            if (outputs.length === 1) {
                expect(console.log).toBeCalledWith(isNaN(output) ? output: output/1);
            } else {
                try {
                    expect(console.log).toBeCalledWith(outputs.join(" "));
                } catch (e) {
                    expect(console.log).toBeCalledWith(outputs.join("\n"));
                }
            }
        })
    });
});