package com.solv.inventory.dao;

import com.solv.inventory.model.entity.txn_branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<txn_branch,Integer> {
    List<txn_branch> findByNameContaining(String title);
}
