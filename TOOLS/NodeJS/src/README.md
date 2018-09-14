Transform input to grid array
```js
const grid = input.map((line) => line.split("").map((col) => col));
```

Fill grid array with value
```js
const fillGrid = Array(size).fill().map(() => Array(size).fill().map(() => defaultValue));
```