import "babel-polyfill";
import _ from "lodash";
import contestResponse from "./content.js";

const input = [];
const logs = (...args) => args.forEach((arg) => LocalPrint(arg));

readline_object.on("line", (value) => { //Read input values
	input.push(value);
})
//Call ContestResponse when all inputs are red
readline_object.on("close", () => {
    LocalPrint("------------ INPUT -----------");
    LocalPrintArray(input);
    LocalPrint("---------- RESPONSE ----------");
    contestResponse(input);
    LocalPrint(" ");
    LocalPrint(" ");
});
