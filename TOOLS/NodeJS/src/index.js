import contestResponse from "./content.js";

const input = [];
const logs = (...args) => args.forEach((arg) => console.error(arg));

readline_object.on("line", (value) => { //Read input values
	input.push(value);
})
//Call ContestResponse when all inputs are red
readline_object.on("close", () => {
    console.error("------------ INPUT -----------");
    console.error(input);
    console.error("---------- RESPONSE ----------");
    const response = contestResponse(input);
    if (response !== undefined) {
        console.log(response);
    }
    console.error(" ");
    console.error(" ");
});
