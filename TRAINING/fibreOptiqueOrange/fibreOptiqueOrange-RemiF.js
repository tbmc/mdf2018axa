//to use with js tools
import _ from "lodash";
import munkres from "munkres-js";
import Combinatorics from "js-combinatorics";
import jsgraphs from "js-graph-algorithms";

import {
    ObjectMap, ObjectSet,
    toGrid, toGridNumber, fillGrid,
    flipMatrix, flipMatrixCounterClockwise, rotateMatrix, rotateMatrixCounterClockwise
} from "./tools";
const logs = (...args) => LocalPrint(args);

//LocalPrint( $variable );
//LocalPrintArray( $array );
//return to send result
export default (input) => {
    const size = input.shift();
    const grid = toGridNumber(input, " ");
    const vals = new Set();
    grid.forEach((l) => l.forEach((c) => vals.add(c)));

    const sorted = [...vals.keys()].sort((a, b) => a - b);

    const toCheck = _.range(size).map((e) => e);
    const isLinked = (tmpG, curr, excludeValue) => {

        let links = new Set();
        links.add(curr);
        const checkLinks = (g) => {
            for(let i = 0; i < g.length; i++) {
                if (!links.has(i)) {
                    const el = g[i];
                    if (el > excludeValue) {
                        links.add(i);
                        if (checkLinks(tmpG[i])) {
                            return true;
                        }
                    }
                }
            }
            return toCheck.every((t) => links.has(t));
        }
        return checkLinks(tmpG[curr], excludeValue);
    }
    let i = 0;
    while (isLinked(grid, 0, sorted[i])) {
        i++;
    }
    return sorted[i];
};