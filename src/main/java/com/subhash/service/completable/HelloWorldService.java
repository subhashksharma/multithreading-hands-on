package com.subhash.service.completable;

import static com.subhash.util.CommonUtil.delay;

public class HelloWorldService {


    public String helloWorld() {
        delay(500);
        return "hello world";
    }

    public String hello() {
        delay(500);
        return " hello";
    }

    public String world() {
        delay(500);
        return " world";
    }


    public String hiThere() {
        delay(500);
        return "hi there";
    }
}
