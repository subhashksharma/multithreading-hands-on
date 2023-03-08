package com.subhash.service.completable;

import java.util.concurrent.CompletableFuture;

public class HelloWorldCompletableFuture {

    private final HelloWorldService helloWorldService;

    public HelloWorldCompletableFuture(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }
    public CompletableFuture<String> completableFutureDemo() {
       return CompletableFuture.supplyAsync( () -> {
          return  helloWorldService.helloWorld();
        });

    }


    public String concatStringWithAllServiceCall() {

        CompletableFuture<String> hiThere = CompletableFuture.supplyAsync(()->{
            return helloWorldService.hiThere();
        });
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(()->{
            return helloWorldService.hello();
        });
        CompletableFuture<String> world = CompletableFuture.supplyAsync(()->{
            return helloWorldService.world();
        });

       String value = hiThere.thenCombine(hello, (prev, current) ->{ return prev+current;}).thenCombine(world, (prev, current)->{
            return prev+current;
        }).thenApply(String:: toUpperCase).join();

       return value;
    }



    public static void main(String args[]) {
        HelloWorldService helloWorldService1 = new HelloWorldService();
        HelloWorldCompletableFuture helloWorldCompletableFuture = new HelloWorldCompletableFuture(helloWorldService1);
        helloWorldCompletableFuture.completableFutureDemo().thenAccept((value)->{
            System.out.println("Value from service" + value);
        });;

        System.out.println("done");
    }
}
