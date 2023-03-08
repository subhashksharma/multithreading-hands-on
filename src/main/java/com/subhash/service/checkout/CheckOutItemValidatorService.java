package com.subhash.service.checkout;

import com.subhash.model.cart.CartItem;

import static com.subhash.util.CommonUtil.delay;

public class CheckOutItemValidatorService {

    public CartItem isValidCartItemPrice( CartItem cartItem) {

         delay(500);
        if(cartItem.getItemId() == 5 || cartItem.getItemId() ==10) {
            cartItem.setExpired(true);
        }
        else {
            cartItem.setExpired(false);
        }

        return cartItem;
    }

}
