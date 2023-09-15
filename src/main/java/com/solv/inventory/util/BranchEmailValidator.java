package com.solv.inventory.util;

import com.solv.inventory.exceptions.BadArgsException;

import java.util.regex.Pattern;

public class BranchEmailValidator {
    public static boolean isValidEmail(String email) throws BadArgsException {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null ||!( pattern.matcher(email).matches())){
            throw new BadArgsException("Invalid Email");
        }
        else {
            return true;
        }
    }
}
