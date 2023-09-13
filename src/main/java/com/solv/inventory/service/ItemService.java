package com.solv.inventory.service;

import com.solv.inventory.dto.ItemDto;
import com.solv.inventory.dto.ItemResponse;
import com.solv.inventory.dto.ItemResponsePage;
import com.solv.inventory.entity.Item;
import com.solv.inventory.exceptions.ItemNotFoundException;

import java.util.List;

public interface ItemService {
    ItemResponse add(ItemDto itemDto);

    ItemResponsePage getAllItems(int pageNumber, int pageSize) throws ItemNotFoundException;

    ItemResponsePage searchItems(String value, int pageNumber, int pageSize) throws ItemNotFoundException;

    ItemResponsePage getItemsInOrder(int pageNumber, int pageSize, String sortBy, String order);

    List<Item> getItemsInPriceRange(double minPrice,double maxPrice) throws ItemNotFoundException;
    ItemResponsePage getItemsOfCategory(String category, int pageNumber, int pageSize) throws ItemNotFoundException;

    List<Item> getItemsOfCategoryInPriceRange(String category,double minPrice,double maxPrice) throws ItemNotFoundException;
}
