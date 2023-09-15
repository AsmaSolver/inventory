package com.solv.inventory.service;

import com.solv.inventory.model.dto.BranchDTO;
import com.solv.inventory.model.dto.ItemDTO;
import com.solv.inventory.model.dto.ItemResponse;
import com.solv.inventory.model.entity.Item;
import com.solv.inventory.model.entity.txn_branch;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ItemService {
//    List<Item> getItems(int pageNo, int pageSize);

    public ResponseEntity<ItemResponse> getitems(int pageNo, int pageSize, String sortby,String Dir);
    public ResponseEntity<ItemResponse> SearchItem(String keyword);


//    Item saveItem(Item newItem);
    public ResponseEntity<ItemResponse> saveI(ItemDTO item);
    public ResponseEntity<ItemResponse> ItemsFilterPrice(Double minPrice,Double maxPrice);
    public ResponseEntity<ItemResponse> ItemsFilterCategory(String Category);

    public ResponseEntity<ItemResponse> getItems();

}
