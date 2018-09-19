export const flipMatrix = matrix => (
    matrix[0].map((column, index) => (
        matrix.map(row => row[index])
    ))
);

export const rotateMatrix = matrix => (
    flipMatrix(matrix.reverse())
);

export const rotateMatrixCounterClockwise = matrix => (
    flipMatrix(matrix).reverse()
);

export const flipMatrixCounterClockwise = matrix => (
    rotateMatrix(matrix).reverse()
);