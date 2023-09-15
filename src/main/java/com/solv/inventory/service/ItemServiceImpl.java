package com.solv.inventory.service;

import com.solv.inventory.dao.ItemRepo;
import com.solv.inventory.model.dto.BranchDTO;
import com.solv.inventory.model.dto.BranchResponse;
import com.solv.inventory.model.dto.ItemDTO;
import com.solv.inventory.model.dto.ItemResponse;
import com.solv.inventory.model.entity.Item;
import com.solv.inventory.model.entity.txn_branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.solv.inventory.mapper.BranchConvertor.buildRes;
import static com.solv.inventory.mapper.BranchConvertor.toBranch;
import static com.solv.inventory.mapper.ItemConvertor.buildItemRes;
import static com.solv.inventory.mapper.ItemConvertor.toitem;
import static com.solv.inventory.util.BranchTypeValidator.isValidBranchType;
import static com.solv.inventory.util.BranchValidator.isValidBranch;
import static com.solv.inventory.util.ItemValidator.isValidItem;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemRepo itemRepo;

    public ItemServiceImpl(ItemRepo itemRepo) {
        this.itemRepo=itemRepo;
    }
//    public List<Item> getItems(int pageNo, int pageSize)
//    {
////        int pageSize=5;
////        int pageNo=1;
//        Pageable p= PageRequest.of(pageNo,pageSize);
//        Page<Item> PageItem = this.itemRepo.findA
//        return PageItem.getContent();
//    }

    @Override
    public ResponseEntity<ItemResponse> getitems(int pageNo, int pageSize, String sortBy,String SortDir) {
//        return null;
        Sort sort=null;
        if(SortDir.equalsIgnoreCase("asc"))
        {
            sort=Sort.by(sortBy).ascending();
        }
        else
        {
            sort=Sort.by(sortBy).descending();
        }

        Pageable p= PageRequest.of(pageNo,pageSize,sort);
        Page<Item> PageItem=this.itemRepo.findAll(p);

        List<Item> allBranches=PageItem.getContent();
        if(allBranches.isEmpty()){;
            ItemResponse itemResponse=buildItemRes(null,HttpStatus.BAD_REQUEST.toString(), "No Branches Available");
            return new ResponseEntity<>(itemResponse,HttpStatus.BAD_REQUEST);
        }
        ItemResponse itemResponse=buildItemRes(allBranches,HttpStatus.OK.toString(), "above are the pages of result");
        return new ResponseEntity<>(itemResponse,HttpStatus.OK);

    }

//    @Override
//    public Item saveItem(Item newItem) {
////        Item t=toItem(newBranch);
//        return ItemRepo.save(newItem);
//    }

    @Override
    public ResponseEntity<ItemResponse> SearchItem(String keyword) {
        List<Item> result=this.itemRepo.findByNameContaining(keyword);
        if (result.isEmpty())
        {
            ItemResponse itemResponse=buildItemRes(result,HttpStatus.BAD_REQUEST.toString(),"no branch found");
            return new ResponseEntity<>(itemResponse,HttpStatus.BAD_REQUEST);
        }
        else
        {
            ItemResponse itemResponse=buildItemRes(result,HttpStatus.OK.toString(), "Above are the result of the search");
            return new ResponseEntity<>(itemResponse,HttpStatus.OK);
        }
//        return this.itemRepo.findByNameContaining(keyword);
    }

    @Override
    public ResponseEntity<ItemResponse> saveI(ItemDTO item) {
        if(!isValidItem(item))
        {
            ItemResponse itemResponse=buildItemRes(null,HttpStatus.BAD_REQUEST.toString(), "Enter Proper Inputs");
            return new ResponseEntity<>(itemResponse,HttpStatus.BAD_REQUEST);
        }
        Item t=toitem(item);
        itemRepo.save(t);
        ItemResponse itemResponse=buildItemRes(t,HttpStatus.CREATED.toString(),"Created Branch");
        return new ResponseEntity<>(itemResponse,HttpStatus.CREATED);
//        return itemRepo.save(item);
    }

    @Override
    public ResponseEntity<ItemResponse> ItemsFilterPrice(Double minprice, Double maxprice) {
        List<Item> allItems=this.itemRepo.findByPriceBetween(minprice, maxprice);
//        ItemResponse itemRes = null;
        if (allItems.isEmpty()) {
            ItemResponse itemRes = buildItemRes(null, HttpStatus.BAD_REQUEST.toString(), "No item is available");
            return new ResponseEntity<>(itemRes, HttpStatus.BAD_REQUEST);
        }
        else
        {
            ItemResponse itemRes=buildItemRes(allItems,HttpStatus.OK.toString(), "Items are sorted according to price");
            return new ResponseEntity<>(itemRes,HttpStatus.OK);
        }
//        return itemRepo.findByPrice(price);
    }
        //
//        if(sign.equals("lessandequal")) {
////        return itemRepo.stream
//            return filter.stream()
//                    .filter(c -> c.getPrice() <= price)
//                    .collect(Collectors.toList());
//        } else if (sign.equals("greatandequal")) {
//            return filter.stream()
//                    .filter(c -> c.getPrice() >= price)
//                    .collect(Collectors.toList());
//
//        } else if (sign.equals("great")) {
//            return filter.stream()
//                    .filter(c -> c.getPrice() > price)
//                    .collect(Collectors.toList());
//
//        } else if (sign.equals("less")) {
//            return filter.stream()
//                    .filter(c -> c.getPrice() < price)
//                    .collect(Collectors.toList());
//
//        }
//        else
//        {
//            return filter.stream()
//                    .filter(c -> c.getPrice() == price)
//                    .collect(Collectors.toList());

//        return itemsLessThan2L;


    @Override
    public ResponseEntity<ItemResponse> ItemsFilterCategory(String Category) {
        ItemResponse itemRes = null;
        if (!itemRepo.findByCategory(Category).isEmpty()) {
            itemRes = buildItemRes(null, HttpStatus.BAD_REQUEST.toString(), "No item is available");
            return new ResponseEntity<>(itemRes, HttpStatus.BAD_REQUEST);
        }
        else
        {
            List<Item> allItems=itemRepo.findByCategory(Category);
            itemRes=buildItemRes(allItems,HttpStatus.OK.toString(), "Items are sorted according to price");
            return new ResponseEntity<>(itemRes,HttpStatus.OK);
        }
//        return itemRepo.findByCategory(Category);
    }

//    @Override
//    public List<Item> ItemsFilterCategory(List<Item> filter,String category) {
//        return itemRepo.FindByCategory(category);
//
////        return itemsLessThan2L;
//    }

    @Override
    public ResponseEntity<ItemResponse> getItems() {
        List<Item> allItems=itemRepo.findAll();
        if(allItems.isEmpty()){
            ItemResponse itemResponse=buildItemRes(null,HttpStatus.BAD_REQUEST.toString(), "No item Available");
            return new ResponseEntity<>(itemResponse,HttpStatus.BAD_REQUEST);
        }
        else
        {

            ItemResponse itemResponse=buildItemRes(allItems,HttpStatus.OK.toString(), "Fetched All Branches");
            return new ResponseEntity<>(itemResponse,HttpStatus.OK);

        }
//        return itemRepo.findAll();
    }

//

//    @Override
//    public Item saveItem(Item newItem) {
//        return ItemRepo.save(newItem);
//    }
}
