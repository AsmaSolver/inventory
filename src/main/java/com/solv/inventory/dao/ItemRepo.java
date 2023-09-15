package com.solv.inventory.dao;

import com.solv.inventory.model.entity.Item;
import com.solv.inventory.model.entity.txn_branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item,Integer> {
    List<Item> findByNameContaining(String title);

    List<Item> findByCategory(String category);

    List<Item> findByPrice(int Price);

    List<Item> findByPriceBetween(Double minPrice,Double maxPrice);

//    @Query("SELECT i from Item i WHERE i.category =:category and i.price>=:minPrice and i.price<=:maxPrice")
//    List<Item> findByCategoryAndPriceBetween(@Param("category") String category, @Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

}
