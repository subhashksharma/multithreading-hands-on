package com.subhash.service;

import com.subhash.model.Inventory;
import com.subhash.model.ProductOption;

import static com.subhash.util.CommonUtil.delay;

public class InventoryService {

    public Inventory getInventoryBasedOnProductOption(ProductOption productOption) {
        delay(500);
        return new Inventory(2);
    }
}
