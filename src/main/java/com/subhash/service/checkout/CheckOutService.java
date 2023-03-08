package com.subhash.service.checkout;

import com.subhash.model.cart.Cart;
import com.subhash.model.cart.CartItem;
import com.subhash.model.cart.CheckoutResponse;
import com.subhash.model.cart.CheckoutStatus;

import java.util.List;
import java.util.stream.Collectors;

import static com.subhash.util.CommonUtil.stopWatch;

public class CheckOutService {

    private final CheckOutItemValidatorService checkOutItemValidatorService;

    public CheckOutService(CheckOutItemValidatorService checkOutItemValidatorService) {
        this.checkOutItemValidatorService = checkOutItemValidatorService;
    }

    public CheckoutResponse  checkOutItemValidator(Cart cart) {

        stopWatch.start();
        List<CartItem> priceValidatorList = cart.getCartItemList().parallelStream()
                .map(checkOutItemValidatorService :: isValidCartItemPrice)
                .filter(cartItem -> cartItem.isExpired())
                .collect(Collectors.toList());

        stopWatch.stop();
        System.out.println("Time taken to execute this method" + stopWatch.getTotalTimeMillis());
        if(priceValidatorList.size()>0){
            return new CheckoutResponse( CheckoutStatus.FAILURE, priceValidatorList);
        }


        return new CheckoutResponse( CheckoutStatus.SUCCESSFUL);
    }
}
