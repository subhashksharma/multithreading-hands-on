package com.subhash.util;

import com.subhash.model.cart.Cart;
import com.subhash.model.cart.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class DataGeneratorService {


    public static Cart generateCartItem(Integer noOfCartItem) {
        List<CartItem> cartItemList = new ArrayList<>();
        IntStream.range(1, noOfCartItem).forEach(
                index->{
                    CartItem cartItem = new CartItem(index, "Item"+index, Math.random(), index, false );
                    cartItemList.add(cartItem);
                }
        );

        Cart cart = new Cart(noOfCartItem, cartItemList);
        return cart;
    }
}
