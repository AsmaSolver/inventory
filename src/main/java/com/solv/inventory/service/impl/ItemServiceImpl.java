package com.solv.inventory.service.impl;

import com.solv.inventory.dao.ItemRepository;
import com.solv.inventory.dto.ItemDto;
import com.solv.inventory.dto.ItemRequestFilterDto;
import com.solv.inventory.dto.ItemResponse;
import com.solv.inventory.dto.ItemResponsePage;
import com.solv.inventory.entity.Item;
import com.solv.inventory.exceptions.BadArgumentException;
import com.solv.inventory.mapper.ItemMapper;
import com.solv.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static com.solv.inventory.mapper.ItemMapper.toItemDto;
import static com.solv.inventory.util.ItemCategoryValidator.isValidCategory;
import static com.solv.inventory.util.ItemPriceValidator.isValidPrice;
import static com.solv.inventory.util.NameValidator.isValidName;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Override
    public ResponseEntity<ItemResponse> add(ItemDto itemDto) {
        if(!isValidItem(itemDto)) {
            ItemResponse itemResponse = itemResponseBuilder1(null, HttpStatus.BAD_REQUEST.value(),
                    "Enter the fields correctly");
            return new ResponseEntity<>(itemResponse,HttpStatus.BAD_REQUEST);
        }
        else {
            Item item = ItemMapper.toItem(itemDto);
            Item savedItem = this.itemRepository.save(item);
            ItemResponse itemResponse = itemResponseBuilder1(toItemDto(savedItem), HttpStatus.CREATED.value()
                    , "Item added successfully");
            return new ResponseEntity<>(itemResponse, HttpStatus.CREATED);
        }
    }
    private ItemResponse itemResponseBuilder1(Object data, int statusCode, String message){
        return ItemResponse.builder()
                .data(data)
                .statusCode(statusCode)
                .statusMessage(message)
                .build();
    }
    private boolean isValidItem(ItemDto itemDto){
        try{
            isValidName(itemDto.getName());
            isValidCategory(itemDto.getCategory());
            isValidPrice(itemDto.getPrice());
            return true;
        }
        catch (BadArgumentException ignored){

        }
        return false;
    }

    @Override
    public ResponseEntity<ItemResponse> getAllItems(int pageNumber, int pageSize){
        PageRequest pageable=PageRequest.of(pageNumber,pageSize);
        Page<Item> pageList=itemRepository.findAll(pageable);

        if(pageList.toList().isEmpty()){
            ItemResponse itemResponse =itemResponseBuilder1(null,HttpStatus.BAD_REQUEST.value()
                    , "Currently there are no items");
            return new ResponseEntity<>(itemResponse,HttpStatus.BAD_REQUEST);
        }
        else{
             ItemResponsePage itemResponsePage=buildResponse(pageList);
            ItemResponse itemResponse =itemResponseBuilder1(itemResponsePage,HttpStatus.OK.value(),"Accepted");
             return new ResponseEntity<>(itemResponse,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ItemResponse> searchItems(String title, int pageNumber, int pageSize) {
        Pageable p=PageRequest.of(pageNumber,pageSize);
        Page<Item>itemList=itemRepository.findByNameContaining(title,p);

        if(itemList.toList().isEmpty()){
            ItemResponse itemResponse =itemResponseBuilder1(null,HttpStatus.BAD_REQUEST.value()
                    , "Currently there are no items");
            return new ResponseEntity<>(itemResponse,HttpStatus.BAD_REQUEST);
        }
        else {
            ItemResponsePage itemResponsePage=buildResponse(itemList);
            ItemResponse itemResponse =itemResponseBuilder1(itemResponsePage,HttpStatus.OK.value(),"Accepted");
            return new ResponseEntity<>(itemResponse,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ItemResponse> getItemsInOrder(int pageNumber, int pageSize, String sortBy, String order) {
        Sort sort= Objects.equals(order, "asc") ?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        PageRequest p=PageRequest.of(pageNumber,pageSize,sort);
        Page<Item> page= this.itemRepository.findAll(p);

        if(page.toList().isEmpty()){
            ItemResponse itemResponse=itemResponseBuilder1(page,HttpStatus.BAD_REQUEST.value()
                    , "Currently there are no items");
            return new ResponseEntity<>(itemResponse,HttpStatus.BAD_REQUEST);
        }
        else {
            ItemResponse itemResponse = itemResponseBuilder1(page, HttpStatus.OK.value(), "Accepted");
            return new ResponseEntity<>(itemResponse, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ItemResponse> getItemsInPriceRange(double minPrice, double maxPrice) {
       List<Item> list= this.itemRepository.findByPriceBetween(minPrice,maxPrice);

       if(list.isEmpty()){
           ItemResponse itemResponse =itemResponseBuilder1(null,HttpStatus.OK.value()
                   ,"Currently there are no items");
           return new ResponseEntity<>(itemResponse,HttpStatus.OK);
       }
       else {
           ItemResponse itemResponse =itemResponseBuilder1(list,HttpStatus.OK.value(), "Accepted");
           return new ResponseEntity<>(itemResponse,HttpStatus.OK);
       }
    }

    @Override
    public ResponseEntity<ItemResponse> getItemsOfCategory(String category, int pageNumber, int pageSize) {
        PageRequest p=PageRequest.of(pageNumber,pageSize);
        Page<Item> page= this.itemRepository.findByCategory(category,p);

        if(page.toList().isEmpty()){
            ItemResponse itemResponse =itemResponseBuilder1(null,HttpStatus.OK.value()
                    ,"Currently there are no items");
            return new ResponseEntity<>(itemResponse,HttpStatus.OK);
        }
        else {
            ItemResponsePage itemResponsePage = buildResponse(page);
            ItemResponse itemResponse =itemResponseBuilder1(itemResponsePage,HttpStatus.OK.value(),"Accepted");
            return new ResponseEntity<>(itemResponse,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ItemResponse> getItemsOfCategoryInPriceRange(String category, double minPrice, double maxPrice){
        List<Item> list= this.itemRepository.findByCategoryAndPriceBetween(category,minPrice,maxPrice);
        if(list.isEmpty()){
            ItemResponse itemResponse =itemResponseBuilder1(null,HttpStatus.OK.value()
                    ,"Currently there are no items");
            return new ResponseEntity<>(itemResponse,HttpStatus.OK);
        }
        else {
            ItemResponse itemResponse =itemResponseBuilder1(list,HttpStatus.OK.value(),"Accepted");
            return new ResponseEntity<>(itemResponse,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ItemResponse> getItemsThatMatchesQuery(String query) {
        List<Item> list=this.itemRepository.findItemBasedOnQuery(query);

        if(list.isEmpty()){
            ItemResponse itemResponse=itemResponseBuilder1(null,HttpStatus.BAD_REQUEST.value()
                    , "No items present");
            return new ResponseEntity<>(itemResponse,HttpStatus.BAD_REQUEST);
        }
        else{
            ItemResponse itemResponse=itemResponseBuilder1(list,HttpStatus.OK.value(),"Accepted");
            return new ResponseEntity<>(itemResponse,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ItemResponse> searchDefault(String category, double minPrice, double maxPrice) {
        List<Item> itemList =this.itemRepository.findItemsBasedOnParameters(category,minPrice,maxPrice);

        if(itemList.isEmpty()){
            ItemResponse itemResponse=itemResponseBuilder1(null,HttpStatus.BAD_REQUEST.value()
                    , "No items present");
            return new ResponseEntity<>(itemResponse,HttpStatus.BAD_REQUEST);
        }
        else{
            ItemResponse itemResponse=itemResponseBuilder1(itemList,HttpStatus.OK.value(),"Accepted");
            return new ResponseEntity<>(itemResponse,HttpStatus.OK);
        }
    }
    public ResponseEntity<ItemResponse> getAllBasedOnSpecification(ItemRequestFilterDto itemRequestFilterDto){

        Specification<Item> byFilters = findByFilters(itemRequestFilterDto);
        List<Item> itemList = itemRepository.findAll(byFilters);
        if(itemList.isEmpty()){
            ItemResponse itemResponse=itemResponseBuilder1(null,HttpStatus.BAD_REQUEST.value()
                    , "No items present");
            return new ResponseEntity<>(itemResponse,HttpStatus.BAD_REQUEST);
        }
        else{
            ItemResponse itemResponse=itemResponseBuilder1(itemList,HttpStatus.OK.value(),"Following Items");
            return new ResponseEntity<>(itemResponse,HttpStatus.OK);
        }


    }

    private Specification<Item> findByFilters(ItemRequestFilterDto itemRequestFilterDto) {
        Specification<Item> specification = new Specification<Item>() {
            @Override
            public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(!itemRequestFilterDto.getCategory().isEmpty()){
                    Predicate equal = criteriaBuilder.equal(root.get("category"), itemRequestFilterDto.getCategory());
                    predicates.add(equal);
                }
                if(!itemRequestFilterDto.getName().isEmpty()){
                    predicates.add(criteriaBuilder.equal(root.get("name"),itemRequestFilterDto.getName()));
                }

                predicates.add(criteriaBuilder.greaterThan(root.get("price"),itemRequestFilterDto.getPrice()));

                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));

            }
        };
        return specification;
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
