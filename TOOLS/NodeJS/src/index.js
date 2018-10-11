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
    const response = contestResponse(input);
    if (response !== undefined) {
        console.log(response);
    }
    LocalPrint(" ");
    LocalPrint(" ");
});
