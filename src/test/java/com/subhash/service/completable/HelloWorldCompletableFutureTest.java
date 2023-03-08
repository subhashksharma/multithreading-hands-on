package com.subhash.service.completable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloWorldCompletableFutureTest {

    HelloWorldService helloWorldService1 = new HelloWorldService();
    HelloWorldCompletableFuture helloWorldCompletableFuture = new HelloWorldCompletableFuture(helloWorldService1);


    @Test
    void completableFutureDemo() {
        helloWorldCompletableFuture.completableFutureDemo().thenAccept((value)->{
            assertEquals("hello world", value);
        }).join();

    }

    @Test
    void concatStringWithAllServiceCall() {

        String result =helloWorldCompletableFuture.concatStringWithAllServiceCall();
        assertEquals("HI THERE HELLO WORLD", result);
    }
}