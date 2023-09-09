package com.solv.inventory.controller;

import com.solv.inventory.dto.ItemResponse;
import com.solv.inventory.dto.Response;
import com.solv.inventory.entity.Item;
import com.solv.inventory.service.ItemServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Services for items")
@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {
    @Autowired
    ItemServiceImpl itemserviceimpl;
    @ApiOperation("-adds an item")
    @PostMapping("/")
    public Item addItem(Item item) {
       return this.itemserviceimpl.add(item);
    }

    @ApiOperation("gives all items")
    @GetMapping("/items")
    public ItemResponse getItems(@RequestParam(value = "pageNumber",defaultValue = "1",required = false) int pageNumber,
                                       @RequestParam(value = "pageSize", defaultValue = "5",required = false)int pageSize){
        return this.itemserviceimpl.getAllItems(pageNumber,pageSize);
    }

    @ApiOperation("-return list of items having name that contains tile")
    @GetMapping("/items/{title}")
    public ItemResponse searchItem(@PathVariable("title") String title){
        return this.itemserviceimpl.searchItems(title);
    }

}
