package com.solv.inventory.service;

import com.solv.inventory.dao.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {

    @Mock
    ItemRepository itemRepository;

    ItemServiceImpl itemServiceImpl;
    @BeforeEach
    void setUp()
    {
        itemServiceImpl=new ItemServiceImpl(itemRepository);
    }
    @Test
    void add() {
    }

    @Test
    void getAllItems() {
        try {
            itemServiceImpl.getAllItems(0,10);
            verify(itemRepository.findAll());
        }
        catch(Exception ignored){

        }


    }

    @Test
    void searchItems(){
        try {
            itemServiceImpl.searchItems("Mobile", 0, 10);
            verify(itemRepository).findByNameContaining("Mobile", PageRequest.of(0,10));
        }
        catch (Exception ignored){
            System.out.println("Failed");
        }
    }

    @Test
    void getItemsInOrder() {
       try{
            itemServiceImpl.getItemsInOrder(0,10,"category","asc");
            Sort sort=Sort.by("category").ascending();
            PageRequest p=PageRequest.of(0,10, sort);
            verify(itemRepository).findAll(p);
       }
       catch (Exception ignored){

       }
    }

    @Test
    void getItemsInPriceRange() {
        try{
            itemServiceImpl.getItemsInPriceRange(500,10000);
            verify(itemRepository).findByPriceBetween(1000,100000);
        }
        catch (Exception ignored){

        }
    }

    @Test
    void getItemsOfCategory() {
    }

    @Test
    void getItemsOfCategoryInPriceRange() {
    }
}