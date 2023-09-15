package com.solv.inventory.util;

import com.solv.inventory.exceptions.BadArgsException;
import com.solv.inventory.model.dto.BranchDTO;

import static com.solv.inventory.util.BranchAddressValidator.isValidAddress;
import static com.solv.inventory.util.BranchEmailValidator.isValidEmail;
import static com.solv.inventory.util.BranchMobNoValidator.isValidMobileNumber;
import static com.solv.inventory.util.BranchNameValidator.isValidName;
import static com.solv.inventory.util.BranchTypeValidator.isValidBranchType;

public class BranchValidator {
    public static boolean isValidBranch(BranchDTO branchDTO) {
        try {
            isValidName(branchDTO.getName());
            isValidMobileNumber(branchDTO.getMobile_number());
            isValidEmail(branchDTO.getEmail());
            isValidBranchType(branchDTO.getBranch_type());
//            isValidAddress(branchDTO.getAddress());
            return true;
        }
        catch(BadArgsException exception){

        }
        return false;
    }

}
