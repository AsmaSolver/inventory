package com.solv.inventory.util;

import com.solv.inventory.exceptions.BadArgsException;

public class BranchNameValidator {
    public static boolean isValidName(String s) throws BadArgsException {
        if ((s==null || s.isEmpty() || !(s.matches("[a-zA-Z ]+")))){
            throw new BadArgsException("Not a valid Name");
        }
        else{
            return true;
        }
    }
}
