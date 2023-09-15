package com.solv.inventory.controller;

import com.solv.inventory.model.dto.BranchDTO;
import com.solv.inventory.model.dto.ItemDTO;
import com.solv.inventory.model.dto.ItemResponse;
import com.solv.inventory.model.entity.Item;
import com.solv.inventory.model.entity.txn_branch;
import com.solv.inventory.service.ItemService;
import com.solv.inventory.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemServiceImpl itemService;


    @GetMapping("/getitems")
    public ResponseEntity<ItemResponse> getitems(@RequestParam(value = "PageNo",defaultValue = "0",required = false) int PageNo,
                                                 @RequestParam(value = "PageSize", defaultValue = "5",required = false)int PageSize,
                                                 @RequestParam(value= "SortBy", defaultValue = "price", required = false) String SortBy,
                                                 @RequestParam(value= "SortDir", defaultValue = "asc", required = false) String SortDir)
    {
        return itemService.getitems(PageNo,PageSize,SortBy,SortDir);
    }

    @PostMapping("/cre_item")
    public ResponseEntity<ItemResponse> newItem(@RequestBody ItemDTO newItem)
    {

          return  itemService.saveI(newItem);
    }

    @GetMapping("/search/{keywords}")
    public ResponseEntity<ItemResponse>searchItemTitle(@PathVariable("keywords")String keywords)
    {
//        List<Item> result=
        return this.itemService.SearchItem(keywords);

    }
    @GetMapping("/pricefilter")
    public ResponseEntity<ItemResponse> filterLessThan2L(@RequestParam(value = "minprice",defaultValue = "0.00",required = false) Double minprice,
                                                         @RequestParam(value = "maxprice",defaultValue = "Double.MAX_VALUE",required = false) Double maxprice)
    {
        return itemService.ItemsFilterPrice(minprice,maxprice);
    }
    @GetMapping("/categoryfilter")
    public ResponseEntity<ItemResponse> filterCategory(@RequestParam(value = "category",required = true) String category)
    {
//        List<Item> allItems=itemService.getItems();
        return itemService.ItemsFilterCategory(category);
    }


}
