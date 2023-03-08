package com.subhash.service;

import com.subhash.model.Product;
import com.subhash.model.ProductInfo;
import com.subhash.model.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.subhash.util.CommonUtil.stopWatch;

public class ProductServiceUsingExecutor {

    private static final Logger log = LoggerFactory.getLogger(ProductInfoService.class);
    private final ProductInfoService productInfoService;
    private final  ProductReviewService productReviewService;

    public ProductServiceUsingExecutor(ProductInfoService productInfoService, ProductReviewService productReviewService) {
        this.productInfoService = productInfoService;
        this.productReviewService = productReviewService;
    }

    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public Product retrieveProductDetails(String productId) throws ExecutionException, InterruptedException {

        stopWatch.start();

        Future<ProductInfo> productInfoFuture = executorService.submit(()->productInfoService.retrieveProductInformation(productId));
        Future<Review> reviewFuture = executorService.submit(()->productReviewService.retrieveProductReview(productId));


        ProductInfo productInfo = productInfoFuture.get();
        Review review = reviewFuture.get();
        stopWatch.stop();

        log.info("Total time taken to execute call ,  [{}]", stopWatch.getTotalTimeMillis());

        return new Product(Integer.valueOf(productId), productInfo, review);
    }


    public static void main(String [] args) throws ExecutionException, InterruptedException {
        ProductInfoService productInfoService1 = new ProductInfoService();
        ProductReviewService productReviewService2 = new ProductReviewService();

        ProductServiceUsingExecutor productService = new ProductServiceUsingExecutor(productInfoService1, productReviewService2);

        Product product = productService.retrieveProductDetails("1");

        executorService.shutdown();
        log.info("product Info" + product);

    }
}
