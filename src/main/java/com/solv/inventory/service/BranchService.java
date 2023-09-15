package com.solv.inventory.service;

import com.solv.inventory.exceptions.BadArgsException;
import com.solv.inventory.exceptions.BranchNotFoundException;
import com.solv.inventory.model.dto.BranchDTO;
import com.solv.inventory.model.dto.BranchResponse;
import com.solv.inventory.model.dto.Response;
import com.solv.inventory.model.entity.txn_branch;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BranchService {
    public ResponseEntity<BranchResponse> saveBranch(BranchDTO newBranch);
    public ResponseEntity<BranchResponse> getBranches(int pageNo, int pageSize);
    public  ResponseEntity<BranchResponse> getBranches();

    public  ResponseEntity<BranchResponse> getBranch(int id);

    public ResponseEntity<BranchResponse> updateById(BranchDTO updateBranch, int id);

    public ResponseEntity<BranchResponse> SearchBranch(String keyword);
    public ResponseEntity<BranchResponse> deleteAllBranches();

    public ResponseEntity<BranchResponse> deleteBranchById(int id);
}
