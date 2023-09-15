package com.solv.inventory.util;

import com.solv.inventory.exceptions.BadArgsException;
import com.solv.inventory.model.dto.BranchDTO;

import java.util.regex.Pattern;

public class BranchAddressValidator {
    public static boolean isValidAddress(String address) throws BadArgsException {
        String addressRegex = "^(\\d{1,}) [a-zA-Z0-9\\s]+(\\,)? [a-zA-Z]+(\\,)? [A-Z]{2} [0-9]{5,6}$";
        Pattern pattern =Pattern.compile(addressRegex);
//         Pattern.compile(addressRegex);
        if (address == null ||!( pattern.matcher(addressRegex).matches())){
            throw new BadArgsException("Invalid Email");
        }
        else {
            return true;
        }
    }
}
