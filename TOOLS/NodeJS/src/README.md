Transform input to grid array
```js
const grid = input.map((line) => line.split("").map((col) => col));
```
---
Fill grid array with value
```js
const fillGrid = Array(size).fill().map(() => Array(size).fill().map(() => defaultValue));
```
---
Multiple returns format (with result as an array)
```js
console.log(result.join(" "));
//with js tools
return result.join(" ");
```
```js
console.log(result.join("\n"));
//with js tools
return result.join("\n");
```
---