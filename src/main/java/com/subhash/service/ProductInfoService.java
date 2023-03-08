package com.subhash.service;

import com.subhash.model.ProductInfo;
import com.subhash.model.ProductOption;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.subhash.util.CommonUtil.delay;

public class ProductInfoService {

    public ProductInfo retrieveProductInformation(String productId) {

        delay(1000);

        List<ProductOption> productOptionList = Stream.of(
                new ProductOption(1, "64GB", "black", 899.9, null),
                new ProductOption(2, "128GB", "black", 999.9, null),
                new ProductOption(3, "256GB", "black", 1099.9, null),
                new ProductOption(4, "512B", "black", 1199.9, null)
        ).collect(Collectors.toList());

      return  ProductInfo.builder().productOptionList(productOptionList).productId(productId).build();
    }
}
