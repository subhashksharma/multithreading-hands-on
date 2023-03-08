package com.subhash.service;

import com.subhash.model.Product;
import com.subhash.model.ProductInfo;
import com.subhash.model.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.subhash.util.CommonUtil.stopWatch;

public class ProductServiceUsingThreadRunnable {

    private static final Logger log = LoggerFactory.getLogger(ProductInfoService.class);
    public final ProductInfoService productInfoService;
    public final  ProductReviewService productReviewService;

    public ProductServiceUsingThreadRunnable(ProductInfoService productInfoService, ProductReviewService productReviewService) {
        this.productInfoService = productInfoService;
        this.productReviewService = productReviewService;
    }

    public Product retrieveProductDetails(String productId) throws InterruptedException {

        stopWatch.start();
       // ProductInfo productInfo =productInfoService.retrieveProductInformation(productId);
        //Review review = productReviewService.retrieveProductReview(productId);

        ProductRunnable productInfoRunnable = new ProductRunnable(productId);
        ProductReviewRunnable productReviewRunnable = new ProductReviewRunnable(productId);

        Thread productInfoThread = new Thread(productInfoRunnable);
        Thread productReviewThread = new Thread(productReviewRunnable);

        productInfoThread.start();
        productReviewThread.start();

        productInfoThread.join();
        productReviewThread.join();

        stopWatch.stop();

        log.info("Total time taken to execute call ,  [{}]", stopWatch.getTotalTimeMillis());

        return new Product(Integer.valueOf(productId), productInfoRunnable.productInfo, productReviewRunnable.getReview());
    }


    public static void main(String [] args) throws InterruptedException {
        ProductInfoService productInfoService1 = new ProductInfoService();
        ProductReviewService productReviewService2 = new ProductReviewService();

        ProductServiceUsingThreadRunnable productService = new ProductServiceUsingThreadRunnable(productInfoService1, productReviewService2);

        Product product = productService.retrieveProductDetails("1");

        log.info("product Info" + product);

    }

    class ProductRunnable implements  Runnable{

        private String productId;


        private ProductInfo productInfo;
        public ProductRunnable( String productId) {
            this.productId = productId;
        }

        public ProductInfo getProductInfo() {
            return productInfo;
        }

        @Override
        public void run() {
            productInfo = productInfoService.retrieveProductInformation(productId);
        }
    }

    class ProductReviewRunnable implements  Runnable{

        private String productId;


        private Review review;
        public ProductReviewRunnable( String productId) {
            this.productId = productId;
        }


        public Review getReview() {
            return review;
        }

        @Override
        public void run() {
            review = productReviewService.retrieveProductReview(productId);
        }
    }

}

