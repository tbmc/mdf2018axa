//to use with js tools
import _ from "lodash";
import munkres from "munkres-js";
import Combinatorics from "js-combinatorics";

import {
    ObjectMap, ObjectSet,
    sortAsc, sortDesc,
    toGrid, toGridNumber, fillGrid, resultGridWithSpaces,
    flipMatrix, flipMatrixCounterClockwise, rotateMatrix, rotateMatrixCounterClockwise
} from "./tools";
const logs = (...args) => LocalPrint(args);

//LocalPrint( $variable );
//LocalPrintArray( $array );
//return to send result
export default (input) => {
    input.shift();
    const grid = toGridNumber(input, " ");

    const distance = ([x,y], [xx,yy]) => Math.sqrt((xx-x)**2 + (yy-y)**2);

    try {
        grid.forEach(([x,y,d], i) => {
            for(let ii = i+1; ii < grid.length; ii++) {
                const [xx,yy,dd] = grid[ii];
                const dist = distance([x,y], [xx,yy]);
                if (Math.min(d, dd) + dist >= Math.max(d, dd) && dist <= (d + dd) ) {
                    throw new Error();
                }
            }
        });
    } catch (e) {
        return "KO";
    }
    return "OK";
};