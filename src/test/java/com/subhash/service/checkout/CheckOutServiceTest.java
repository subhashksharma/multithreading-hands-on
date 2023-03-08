package com.subhash.service.checkout;

import com.subhash.model.cart.Cart;
import com.subhash.model.cart.CartItem;
import com.subhash.model.cart.CheckoutResponse;
import com.subhash.model.cart.CheckoutStatus;
import com.subhash.util.DataGeneratorService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckOutServiceTest {

    public CheckOutService checkOutService;

    @Test
    void checkOutItemValidator() {


        CheckOutItemValidatorService checkOutItemValidatorService = new CheckOutItemValidatorService();
        checkOutService = new CheckOutService(checkOutItemValidatorService);

        Cart cartGenerated  =DataGeneratorService.generateCartItem(10);
        CheckoutResponse checkoutResponse = checkOutService.checkOutItemValidator(cartGenerated);
        assertNotNull(checkoutResponse);
        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());

    }
}