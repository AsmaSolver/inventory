package com.solv.inventory.constants.enums;

public enum BranchType {
    Main_Branch("Main Branch"),
    Small_Appliances("Small Appliances");
    private String value;


    BranchType(String value) {
        this.value=value;
    }

    public String getValue() {
        return this.value;
    }
}
