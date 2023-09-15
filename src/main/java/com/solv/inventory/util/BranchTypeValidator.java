package com.solv.inventory.util;


import com.solv.inventory.constants.enums.BranchType;
import com.solv.inventory.exceptions.BadArgsException;

public class BranchTypeValidator {
    public static boolean isValidBranchType(String branchType) throws BadArgsException
    {
        if(branchType==null || branchType.isEmpty())
        {
            throw new BadArgsException("branch type cannot be null");
        }
        for(BranchType i : BranchType.values())
        {
            if(i.getValue().equalsIgnoreCase(branchType)){
                return true;
            }
        }
        throw new BadArgsException("Invalid branch type");

    }
}
