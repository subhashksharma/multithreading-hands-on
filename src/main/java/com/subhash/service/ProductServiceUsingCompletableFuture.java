package com.subhash.service;

import com.subhash.model.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.subhash.util.CommonUtil.stopWatch;

public class ProductServiceUsingCompletableFuture {

    private final  ProductInfoService productInfoService;
    private final ProductReviewService productReviewService;

    private final InventoryService inventoryService;


    public ProductServiceUsingCompletableFuture(ProductInfoService productInfoService, ProductReviewService productReviewService, InventoryService inventoryService) {
        this.productInfoService = productInfoService;
        this.productReviewService = productReviewService;
        this.inventoryService = inventoryService;
    }

    public Product retrieveProductDetails(String productId) {

        stopWatch.start();
       CompletableFuture<ProductInfo> productCompletableFuture = CompletableFuture.supplyAsync(() ->{
           return productInfoService.retrieveProductInformation(productId);
        }).thenApply((productInfo -> {
            List<ProductOption> productOptionList = updateProductInventory(productInfo);
            productInfo.setProductOptionList(productOptionList);
            return productInfo;
       }));

       CompletableFuture<Review> productReviewCompletableFuture = CompletableFuture.supplyAsync(()->{
          return productReviewService.retrieveProductReview(productId);
       });

       Product product = productCompletableFuture.thenCombine(productReviewCompletableFuture, ((productInfo, review) -> {
          return  new Product( Integer.valueOf(productId), productInfo, review);
       })).join();

        stopWatch.stop();
        System.out.println("Time taken to execute the product service : "  + stopWatch.getTotalTimeMillis());
       return product;
    }

    private List<ProductOption> updateProductInventory(ProductInfo productInfo) {
       List<CompletableFuture<ProductOption>> completableFutureList = productInfo.getProductOptionList().stream()
               .map(productOption -> {
                   return  CompletableFuture.supplyAsync(()->{
                        return inventoryService.getInventoryBasedOnProductOption(productOption);
                    }).thenApply(inventory -> {
                        productOption.setInventory(inventory);
                        return productOption;
                    });
        }).collect(Collectors.toList());
       return  completableFutureList.stream().map(CompletableFuture :: join).collect(Collectors.toList());
    }
}
