package com.solv.inventory.mapper;

import com.solv.inventory.model.dto.BranchDTO;
import com.solv.inventory.model.dto.BranchResponse;
import com.solv.inventory.model.dto.ItemDTO;
import com.solv.inventory.model.dto.ItemResponse;
import com.solv.inventory.model.entity.Item;
import com.solv.inventory.model.entity.txn_branch;

public class ItemConvertor {

    public static ItemResponse buildItemRes(Object object, String statusCode, String message){
        return ItemResponse.builder()
                .data(object)
                .statusCode(statusCode)
                .statusMessage(message)
                .build();
    }

    public static Item toitem(ItemDTO item)
    {
        return Item.builder().price(item.getPrice())
                .name(item.getName())
                .category(item.getCategory())
                .Material(item.getMaterial()).id(item.getId())
                .build();
    }

    public static ItemDTO toItemDto(Item item)
    {
        return ItemDTO.builder().category(item.getCategory())
                .id(item.getId())
                .Material(item.getMaterial())
                .price(item.getPrice())
                .name(item.getName())
                .build();
    }
}
