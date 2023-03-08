package com.subhash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


@RestController
@SpringBootApplication
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SpringApplication.run(Main.class, args);
    }
    @GetMapping(path = "test/{data}")
    CompletableFuture<List<EventResultData>> getData (@PathVariable int data ) throws ExecutionException, InterruptedException {
        EventConsumer  consumer = new EventConsumer();
        System.out.println("Main thread Name >> : "
                + Thread.currentThread().getName());
        List<EventSourceData> eventSourceDataList = new ArrayList<>();
        for( int i=0; i <100 ; i ++) {
            EventSourceData eventSourceData = new EventSourceData();
            eventSourceData.setEventData(i + ">>>subhash >>>>> ");
            eventSourceDataList.add(eventSourceData);
        }

        List<CompletableFuture<EventResultData>>  eventResultDataList = eventSourceDataList.stream().map(eventSourceData -> {
            CompletableFuture<EventResultData>  eventResultDataCompletableFuture = consumer.processEvent(eventSourceData);
            return eventResultDataCompletableFuture;
        }).collect(Collectors.toList());

        CompletableFuture<Void>  eventResultDataList1 = CompletableFuture.allOf(eventResultDataList.toArray(new CompletableFuture[eventResultDataList.size()]));

        CompletableFuture<List<EventResultData>> allPageContentsFuture = eventResultDataList1.thenApply(v -> {
            return eventResultDataList.stream()
                    .map(eventResult -> eventResult.join())
                    .collect(Collectors.toList());
        });
        return allPageContentsFuture;
    }
}