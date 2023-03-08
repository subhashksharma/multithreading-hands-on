package com.subhash.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.subhash.util.CommonUtil.delay;
import static com.subhash.util.CommonUtil.stopWatch;

public class ParallelStreamDemo {


    public  List<String>  stringTransform(List<String>nameList) {
        List<String> resultNameList = new ArrayList<>();
        resultNameList = nameList.parallelStream().map(name -> {
            delay(500);
            return  name.length() + "-" +name;
        }).collect(Collectors.toList());
    return resultNameList;
    }


    public List<String> transformNameToUpperCase(List<String> lowerCaseNameList) {

        List<String> resultList = lowerCaseNameList.parallelStream().map(String :: toUpperCase).collect(Collectors.toList());

        return resultList;

    }

    public static void main(String args [] ) {

        stopWatch.start();

        ParallelStreamDemo parallelStreamDemo = new ParallelStreamDemo();

        List<String> nameList = Stream.of("test", "subhash", "neil").collect(Collectors.toList());
        List<String> resultNameList = new ArrayList<>();

        resultNameList = parallelStreamDemo.stringTransform(nameList);
        stopWatch.stop();

        System.out.println("Time taken " + stopWatch.getTotalTimeMillis()
                + "and result >> " + resultNameList);

    }

}
