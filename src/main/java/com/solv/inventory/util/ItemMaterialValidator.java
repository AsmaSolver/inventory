package com.solv.inventory.util;

import com.solv.inventory.exceptions.BadArgsException;

public class ItemMaterialValidator {
    public static boolean isValidMaterial(String s) throws BadArgsException {
        if ((s==null || s.isEmpty() || !(s.matches("[a-zA-Z]+")))){
            throw new BadArgsException("Not a valid Name");
        }
        else{
            return true;
        }
    }
}
