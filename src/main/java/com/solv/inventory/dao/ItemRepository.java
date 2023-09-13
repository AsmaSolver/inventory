package com.solv.inventory.dao;

import com.solv.inventory.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer> {
    Page<Item> findByNameContaining(String title, Pageable pageable);
    List<Item> findByPriceBetween(double minPrice,double maxPrice);

    Page<Item> findByCategory(String category,Pageable pageable);

    @Query("SELECT i from Item i WHERE i.category =:category and i.price>=:minPrice and i.price<=:maxPrice")
    List<Item> findByCategoryAndPriceBetween(@Param("category") String category,@Param("minPrice") double minPrice,@Param("maxPrice") double maxPrice);
}
