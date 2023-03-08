package com.subhash.service;

import org.junit.jupiter.api.Test;

import java .util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ParallelStreamDemoTest {

    ParallelStreamDemo parallelStreamDemo = new ParallelStreamDemo();

    @Test
    void stringTransform() {
        List<String> nameList = Stream.of("test", "subhash", "neil").collect(Collectors.toList());
        List<String>  resultList = parallelStreamDemo.stringTransform(nameList);
        assertEquals(3, resultList.size());
    }

    @Test
    void transformNameToUpperCase() {
        List<String> nameList = Stream.of("test", "subhash", "neil").collect(Collectors.toList());
        List<String> resultList = parallelStreamDemo.transformNameToUpperCase(nameList);

        resultList.forEach( System.out :: println);
        assertEquals("TEST", resultList.get(0));

    }

    @Test
    void main() {
    }
}