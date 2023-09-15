package com.solv.inventory.util;

import com.solv.inventory.exceptions.BadArgsException;
import com.solv.inventory.model.dto.BranchDTO;
import com.solv.inventory.model.dto.ItemDTO;

import static com.solv.inventory.util.BranchEmailValidator.isValidEmail;
import static com.solv.inventory.util.BranchMobNoValidator.isValidMobileNumber;
import static com.solv.inventory.util.BranchNameValidator.isValidName;
import static com.solv.inventory.util.BranchTypeValidator.isValidBranchType;
import static com.solv.inventory.util.ItemCategoryValidator.isValidCategory;
import static com.solv.inventory.util.ItemMaterialValidator.isValidMaterial;
import static com.solv.inventory.util.ItemNameValidator.validName;
import static com.solv.inventory.util.ItemPriceValidator.isValidPrice;

public class ItemValidator {
    public static boolean isValidItem(ItemDTO itemDTO) {
        try {
            validName(itemDTO.getName());
            isValidPrice(itemDTO.getPrice());
            isValidMaterial(itemDTO.getMaterial());
            isValidCategory(itemDTO.getCategory());

            return true;
        }
        catch(BadArgsException exception){

        }
        return false;
    }
}
