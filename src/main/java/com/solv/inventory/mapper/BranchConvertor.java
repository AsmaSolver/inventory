package com.solv.inventory.mapper;

import com.solv.inventory.model.dto.BranchDTO;
import com.solv.inventory.model.dto.BranchResponse;
import com.solv.inventory.model.dto.Response;
import com.solv.inventory.model.entity.txn_branch;

public class BranchConvertor {

    public static Response buildResponse(String statusCode,String message){
        return Response.builder()
                .statusCode(statusCode)
                .statusMessage(message)
                .build();
    }

    public static BranchResponse buildRes(Object object, String statusCode, String message){
        return BranchResponse.builder()
                .data(object)
                .statusCode(statusCode)
                .statusMessage(message)
                .build();
    }
    public static BranchDTO toBranchDto(txn_branch user)
    {
        return BranchDTO.builder().branch_id(user.getBranch_id())
                .email(user.getEmail())
                .name(user.getName())
                .address(user.getAddress())
                .branch_type(user.getBranch_type())
                .created_by(user.getCreated_by())
                .created_date(user.getCreated_date())
                .mobile_number(user.getMobile_number())
                .updated_by(user.getUpdated_by())
                .updated_date(user.getUpdated_date())
                .build();
    }

    public static txn_branch toBranch(BranchDTO branch)
    {
        return txn_branch.builder().branch_id(branch.getBranch_id())
                .branch_type(branch.getBranch_type())
                .email(branch.getEmail())
                .name(branch.getName())
                .address(branch.getAddress())
                .mobile_number(branch.getMobile_number())
                .updated_by(branch.getUpdated_by())
                .created_by(branch.getCreated_by())
                .build();
    }
}