package com.subhash;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventConsumer {

    public CompletableFuture<EventResultData>  processEvent(EventSourceData  eventSourceData) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        return  CompletableFuture.supplyAsync(() -> {
            System.out.println("SupplyAsync thread Name >> : "
                    + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            eventSourceData.setEventData(eventSourceData.getEventData()+"Calling service");
            return eventSourceData;
        }, executor).thenApplyAsync(
                (eventSource) ->{
                    System.out.println("Apply Async thread Name >> : "
                            + Thread.currentThread().getName());
                    String eventData = eventSource.getEventData();
                    EventResultData eventResultData = new EventResultData();
                    eventResultData.setEventData(eventData);
                    return eventResultData;
                }
                ,executor
        );
    }

}
