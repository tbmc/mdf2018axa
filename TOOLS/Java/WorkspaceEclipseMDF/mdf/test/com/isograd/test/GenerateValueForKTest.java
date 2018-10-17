package com.isograd.test;

import com.isograd.helper.GenerateValueForSize;
import org.junit.Test;

public class GenerateValueForKTest {

    @Test
    public void printAllKLength() {
        System.out.println("First Test");
        char[] set1 = {'a', 'b'};
        int k = 3;
        GenerateValueForSize.printAllKLength(set1, k);

        System.out.println("\nSecond Test");
        char[] set2 = {'a', 'b', 'c', 'd'};
        k = 1;
        GenerateValueForSize.printAllKLength(set2, k);
    }
}