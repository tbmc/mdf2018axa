export const flipMatrix = matrix => (
    matrix[0].map((column, index) => (
        matrix.map(row => row[index])
    ))
);

export const rotateMatrix = matrix => flipMatrix(matrix.reverse());

export const rotateMatrixCounterClockwise = matrix => flipMatrix(matrix).reverse();

export const flipMatrixCounterClockwise = matrix => rotateMatrix(matrix).reverse();

export const toGrid = (input, separator="") => input.map((line) => line.split(separator).map((col) => col));
export const toGridNumber = (input, separator="") => input.map((line) => line.split(separator).map((col) => col/1));

export const fillGrid = (size, val) => Array(size).fill().map(() => Array(size).fill(val));