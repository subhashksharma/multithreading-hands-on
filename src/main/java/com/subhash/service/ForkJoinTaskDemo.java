package com.subhash.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.subhash.util.CommonUtil.delay;
import static com.subhash.util.CommonUtil.stopWatch;

public class ForkJoinTaskDemo {


    public static void main(String args [] ) {

        stopWatch.start();

        List<String> nameList = Stream.of("test", "subhash", "neil").collect(Collectors.toList());
        List<String> resultNameList = new ArrayList<>();

        resultNameList = nameList.stream().map(name -> {
            delay(500);
           return  name.length() + "-" +name;
        }).collect(Collectors.toList());

        stopWatch.stop();

        System.out.println("Time taken " + stopWatch.getTotalTimeMillis()
                + "and result >> " + resultNameList);

    }

}
