package com.subhash.model.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CheckoutResponse {

    private CheckoutStatus checkoutStatus;
    private List<CartItem> errorList = new ArrayList<>();

    public CheckoutResponse( CheckoutStatus checkoutStatus) {
        this.checkoutStatus  =  checkoutStatus;
    }

    public CheckoutResponse( CheckoutStatus checkoutStatus, List<CartItem> errorList) {
        this.errorList = errorList;
        this.checkoutStatus = checkoutStatus;
    }



}
