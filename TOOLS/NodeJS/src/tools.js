export const flipMatrix = matrix => (
    matrix[0].map((column, index) => (
        matrix.map(row => row[index])
    ))
);

export const rotateMatrix = matrix => flipMatrix(matrix.reverse());

export const rotateMatrixCounterClockwise = matrix => flipMatrix(matrix).reverse();

export const flipMatrixCounterClockwise = matrix => rotateMatrix(matrix).reverse();

export const toGrid = (input) => input.map((line) => line.split("").map((col) => col));

export const fillGrid = (size, val) => Array(size).fill().map(() => Array(size).fill(val));