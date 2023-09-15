package com.solv.inventory.util;

import com.solv.inventory.exceptions.BadArgsException;

import java.util.regex.Pattern;

public class BranchMobNoValidator {
    public static boolean isValidMobileNumber(String mobileNumber) throws BadArgsException {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        if( mobileNumber==null || !(pattern.matcher(mobileNumber).matches())){
            throw new BadArgsException("Invalid Mobile Number");
        }
        else{
            return true;
        }
    }
}
