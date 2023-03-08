package com.subhash.service;

import com.subhash.model.Product;
import com.subhash.model.ProductInfo;
import com.subhash.model.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.subhash.util.CommonUtil.stopWatch;

public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductInfoService.class);
    private final ProductInfoService productInfoService;
    private final  ProductReviewService productReviewService;

    public ProductService(ProductInfoService productInfoService, ProductReviewService productReviewService) {
        this.productInfoService = productInfoService;
        this.productReviewService = productReviewService;
    }

    public Product retrieveProductDetails(String productId) {

        stopWatch.start();
        ProductInfo productInfo =productInfoService.retrieveProductInformation(productId);
        Review review = productReviewService.retrieveProductReview(productId);
        stopWatch.stop();

        log.info("Total time taken to execute call ,  [{}]", stopWatch.getTotalTimeMillis());

        return new Product(Integer.valueOf(productId), productInfo, review);
    }


    public static void main(String [] args) {
        ProductInfoService productInfoService1 = new ProductInfoService();
        ProductReviewService productReviewService2 = new ProductReviewService();

        ProductService productService = new ProductService(productInfoService1, productReviewService2);

        Product product = productService.retrieveProductDetails("1");

        log.info("product Info" + product);

    }
}
