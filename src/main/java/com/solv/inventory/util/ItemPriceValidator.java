package com.solv.inventory.util;

import com.solv.inventory.exceptions.BadArgsException;

public class ItemPriceValidator {
    public static boolean isValidPrice(double price) throws BadArgsException {
        if(price<0 ) {
            throw new BadArgsException("Not a valid price");
        }
        else {
            return true;
        }
    }
}
