package com.solv.inventory.service;

import com.solv.inventory.dao.ItemRepo;
import com.solv.inventory.model.dto.ItemDTO;
import com.solv.inventory.model.entity.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.solv.inventory.mapper.ItemConvertor.toitem;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {

    @Mock
    private ItemRepo itemRepo;
    private ItemServiceImpl itemService;
    @BeforeEach
    void setUp() {
        this.itemService=new ItemServiceImpl(this.itemRepo);
    }
//    @Test
//    List<Item> getitems() {
//        String sortBy="price";
//        String SortDir="asc";
//        List<Item> page=itemService.getitems(0,2,sortBy,SortDir);
//        List<Item> expectedPage=itemRepo.
//        Sort sort=null;
//        if(SortDir.equalsIgnoreCase("asc"))
//        {
//            sort=Sort.by(sortBy).ascending();
//        }
//        else
//        {
//            sort=Sort.by(sortBy).descending();
//        }
//        Pageable p= PageRequest.of(0,2,sort);
//        List<Item> PageItem=this.itemRepo.findAll(p).getContent();
//        List<int> i= PageItem.;
//        assertArrayEquals();


//    }

    @Test
    void searchItemfornull() {
        itemService.SearchItem("");
        verify(itemRepo).findByNameContaining("");
    }
    @Test
    void searchItemforempty() {
        itemService.SearchItem(null);
        verify(itemRepo).findByNameContaining(null);
    }
    @Test
    void searchItem() {
        itemService.SearchItem("laptops");
        verify(itemRepo).findByNameContaining("laptops");
    }

    @Test
    void saveI() {
        ItemDTO i=new ItemDTO();
        itemService.saveI(i);
        Item i1=toitem(i);
        verify(itemRepo).save(i1);
    }

    @Test
    void itemsFilterPrice() {
        itemService.ItemsFilterPrice(0.00,200000.00);
        verify(itemRepo).findByPriceBetween(0.00,200000.00);

    }

    @Test
    void itemsFilterCategory() {
        itemService.ItemsFilterCategory("laptops");
        verify(itemRepo).findByCategory("laptops");
    }

    @Test
    void getItems() {
        itemService.getItems();
        verify(itemRepo).findAll();
    }
}