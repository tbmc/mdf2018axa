//Solution transposée de la solution PYTHON
//Solution transposée de la solution PYTHON
//Solution transposée de la solution PYTHON
//Solution transposée de la solution PYTHON

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
    const distance = ([x,y], [xx,yy]) => Math.sqrt(Math.pow((xx - x), 2) + Math.pow((yy - y), 2));
    const map = new ObjectMap();
    const link = new ObjectMap();
    const set = new ObjectSet();
    const first = input[0];
    const [firstX, firstY] = first.split(" ").map(Number);
    set.add([firstX, firstY]);
    const points = new Set(input.map((i) => i.split(" ").map(Number)));
    [...points.keys()].filter((v) => !set.has(v)).forEach((coords) => {
        link.set(coords, first);
        map.set(coords, distance([firstX, firstY], coords));
    });
    let d = 0;
    while(set.size !== points.size) {
        const [min, dist] = [...map.entries()].sort(([,d], [,dd]) => d - dd)[0];
        d += dist;
        map.delete(min);
        set.add(min);
        [...points.keys()].filter((v) => !set.has(v)).forEach((p) => {
            const newD = distance(min, p);
            if (map.get(p) > newD) {
                link.set(p, min);
                map.set(p, newD)
            }
        })
            
    }

    return d;//Math.round(d*1000) / 1000;
};