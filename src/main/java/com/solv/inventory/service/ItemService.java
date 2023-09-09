package com.solv.inventory.service;

import com.solv.inventory.dto.ItemResponse;
import com.solv.inventory.entity.Item;

public interface ItemService {
    Item add(Item item);

    ItemResponse getAllItems(int pageNumber,int pageSize);

    ItemResponse searchItems(String value);
}
