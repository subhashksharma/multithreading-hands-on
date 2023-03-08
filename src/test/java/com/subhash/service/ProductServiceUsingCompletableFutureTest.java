package com.subhash.service;

import com.subhash.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceUsingCompletableFutureTest {

    private final  ProductInfoService productInfoService = new ProductInfoService();
    private final ProductReviewService productReviewService = new ProductReviewService();

    private final InventoryService inventoryService = new InventoryService();

    private ProductServiceUsingCompletableFuture productServiceUsingCompletableFuture
            = new ProductServiceUsingCompletableFuture(productInfoService, productReviewService, inventoryService);

    @Test
    void retrieveProductDetails() {

        Product product =  productServiceUsingCompletableFuture.retrieveProductDetails("1");

        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptionList().size() >0);

        product.getProductInfo().getProductOptionList().stream().forEach(
                productOption -> {
                    assertNotNull(productOption.getInventory());
                }
        );

    }
}