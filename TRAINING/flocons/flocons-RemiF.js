//to use with js tools
import _ from "lodash";
import munkres from "munkres-js";
import Combinatorics from "js-combinatorics";

import {
    ObjectMap, ObjectSet,
    sortAsc, sortDesc, resultGridWithSpaces,
    toGrid, toGridNumber, fillGrid,
    flipMatrix, flipMatrixCounterClockwise, rotateMatrix, rotateMatrixCounterClockwise
} from "./tools";
const logs = (...args) => LocalPrint(args);

//LocalPrint( $variable );
//LocalPrintArray( $array );
//return to send result
export default (input) => {
    const size = input.shift()/1;
    const grid = fillGrid(size, ".");
    const start = size/2 >> 0;
    grid.forEach((l, curr) => {
        const offset = curr <= start ? curr : size-curr-1; 
        for(let i = start - offset; i < l.length - start + offset ; i++) {
            grid[curr][i] = "*";
        }
    });
    return resultGridWithSpaces(grid);
};