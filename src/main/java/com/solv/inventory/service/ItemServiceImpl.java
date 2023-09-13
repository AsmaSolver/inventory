package com.solv.inventory.service;

import com.solv.inventory.dao.ItemRepository;
import com.solv.inventory.dto.ItemDto;
import com.solv.inventory.dto.ItemResponse;
import com.solv.inventory.dto.ItemResponsePage;
import com.solv.inventory.entity.Item;
import com.solv.inventory.exceptions.BadArgumentException;
import com.solv.inventory.exceptions.ItemNotFoundException;
import com.solv.inventory.exceptions.UserNotFoundException;
import com.solv.inventory.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import static com.solv.inventory.util.ItemCategoryValidator.isValidCategory;
import static com.solv.inventory.util.ItemPriceValidator.isValidPrice;
import static com.solv.inventory.util.NameValidator.isValidName;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    ItemRepository itemRepository;
    ItemServiceImpl(ItemRepository itemRepository){
        this.itemRepository=itemRepository;
    }
    @Override
    public ItemResponse add(ItemDto itemDto) {
        if(!isValidItem(itemDto)){
            return itemResponseBuilder(HttpStatus.BAD_REQUEST.toString(), "Enter the fields correctly");
        }
        else {
            Item item = ItemMapper.toItem(itemDto);
            this.itemRepository.save(item);
            return itemResponseBuilder(HttpStatus.CREATED.toString(), "Item added successfully");
        }
    }
    private boolean isValidItem(ItemDto itemDto){
        try{
            isValidName(itemDto.getName());
            isValidCategory(itemDto.getCategory());
            isValidPrice(itemDto.getPrice());
            return true;
        }
        catch (BadArgumentException ex){

        }
        return false;
    }
    private ItemResponse itemResponseBuilder(String statusCode,String statusMessage){
        return ItemResponse.builder()
                .statusCode(statusCode)
                .statusMessage(statusMessage)
                .build();
    }

    @Override
    public ItemResponsePage getAllItems(int pageNumber, int pageSize) throws ItemNotFoundException {
        PageRequest pageable=PageRequest.of(pageNumber,pageSize);
        Page<Item> pageList=itemRepository.findAll(pageable);
        if(pageList.toList().isEmpty()){
            throw new ItemNotFoundException("Currently there are no items");
        }
        return buildResponse(pageList);
    }

    @Override
    public ItemResponsePage searchItems(String title, int pageNumber, int pageSize) throws ItemNotFoundException {
        Pageable p=PageRequest.of(pageNumber,pageSize);
        Page<Item>itemList=itemRepository.findByNameContaining(title,p);
        if(itemList.toList().isEmpty()){
            throw new ItemNotFoundException("Currently there are no items");
        }
        return buildResponse(itemList);
    }

    @Override
    public ItemResponsePage getItemsInOrder(int pageNumber, int pageSize, String sortBy, String order) {
        Sort sort= Objects.equals(order, "asc") ?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        PageRequest p=PageRequest.of(pageNumber,pageSize,sort);
        Page<Item> page= this.itemRepository.findAll(p);
        return buildResponse(page);
    }

    @Override
    public List<Item> getItemsInPriceRange(double minPrice, double maxPrice) throws ItemNotFoundException {
       List<Item> list= this.itemRepository.findByPriceBetween(minPrice,maxPrice);
       if(list.isEmpty()){
           throw new ItemNotFoundException("Currently there are no items");
       }
       else {
           return list;
       }
    }

    @Override
    public ItemResponsePage getItemsOfCategory(String category, int pageNumber, int pageSize) throws ItemNotFoundException {
        PageRequest p=PageRequest.of(pageNumber,pageSize);
        Page<Item> page= this.itemRepository.findByCategory(category,p);
        if(page.toList().isEmpty()){
            throw new ItemNotFoundException("Currently there are no items");
        }
        return buildResponse(page);
    }

    @Override
    public List<Item> getItemsOfCategoryInPriceRange(String category,double minPrice,double maxPrice) throws ItemNotFoundException {
        List<Item> list= this.itemRepository.findByCategoryAndPriceBetween(category,minPrice,maxPrice);
        if(list.isEmpty()){
            throw new ItemNotFoundException("Currently there are no items");
        }
        return list;
    }

    private ItemResponsePage buildResponse(Page<Item> pageList){
        return ItemResponsePage.builder()
                .itemList(pageList.toList())
                .totalPage(pageList.getTotalPages())
                .pageNumber(pageList.getNumber())
                .pageSize(pageList.getSize())
                .isLastPage(pageList.isLast())
                .build();
    }
}
