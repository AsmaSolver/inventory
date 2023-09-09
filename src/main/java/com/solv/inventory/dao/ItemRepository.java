package com.solv.inventory.dao;

import com.solv.inventory.dto.ItemResponse;
import com.solv.inventory.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer> {
    Page<Item> findByNameContaining(String title, Pageable pageable);
}
