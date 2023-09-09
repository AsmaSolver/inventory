package com.solv.inventory.service;

import com.solv.inventory.dao.ItemRepository;
import com.solv.inventory.dto.ItemResponse;
import com.solv.inventory.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    ItemRepository itemRepository;
    @Override
    public Item add(Item item) {
       return this.itemRepository.save(item);
    }

    @Override
    public ItemResponse getAllItems(int pageNumber,int pageSize) {
        PageRequest pageable=PageRequest.of(pageNumber,pageSize);
        Page<Item> pageList=itemRepository.findAll(pageable);
        return buildResponse(pageList);
    }

    @Override
    public ItemResponse searchItems(String title) {
        Pageable p=PageRequest.of(0,2);
        Page<Item>itemList=itemRepository.findByNameContaining(title,p);
        return buildResponse(itemList);
    }

    private ItemResponse buildResponse(Page<Item> pageList){
        return ItemResponse.builder()
                .itemList(pageList.toList())
                .totalPage(pageList.getTotalPages())
                .pageNumber(pageList.getNumber())
                .pageSize(pageList.getSize())
                .isLastPage(pageList.isLast())
                .build();
    }
}
