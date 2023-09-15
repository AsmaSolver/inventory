package com.solv.inventory.service.impl;

import com.solv.inventory.dao.ItemRepository;
import com.solv.inventory.entity.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {
    @Mock
    ItemRepository itemRepository;
    @InjectMocks
    ItemServiceImpl itemServiceImpl;

    Item item;
    @BeforeEach
    void setUp(){
        this.itemServiceImpl=new ItemServiceImpl(itemRepository);
        item =Item.builder()
                .name("Fan")
                .price(1000.0)
                .category("Electronics")
                .build();

    }

    @Test
    void add() {

    }

    @Test
    void getAllItems() {
//        List<Item> list=new ArrayList<>();
//        list.add(item);
//        when(itemRepository.findAll()).thenReturn(list);
//
//        verify(itemRepository).findAll();
    }

    @Test
    void searchItems() {
    }

    @Test
    void getItemsInOrder() {
    }

    @Test
    void getItemsInPriceRange() {
    }

    @Test
    void getItemsOfCategory() {
    }

    @Test
    void getItemsOfCategoryInPriceRange() {
    }

    @Test
    void getItemsThatMatchesQuery() {
    }
}