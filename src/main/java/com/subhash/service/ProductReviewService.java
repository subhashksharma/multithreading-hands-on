package com.subhash.service;

import com.subhash.model.Review;

import static com.subhash.util.CommonUtil.delay;

public class ProductReviewService {

    public Review retrieveProductReview(String productId) {
        delay(1000);
       // delay(1000);
        return  new Review( Integer.valueOf(productId), 200, 4.5);
    }
}
