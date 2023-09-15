package com.solv.inventory.service;

import com.solv.inventory.dao.BranchRepository;
import com.solv.inventory.exceptions.BadArgsException;
import com.solv.inventory.exceptions.BranchNotFoundException;
import com.solv.inventory.model.dto.BranchDTO;
import com.solv.inventory.model.dto.BranchResponse;
import com.solv.inventory.model.dto.Response;
import com.solv.inventory.model.entity.txn_branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

import static com.solv.inventory.mapper.BranchConvertor.*;
import static com.solv.inventory.util.BranchTypeValidator.isValidBranchType;
import static com.solv.inventory.util.BranchValidator.isValidBranch;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    BranchRepository branchRepository;

    @Override
    public ResponseEntity<BranchResponse> saveBranch(BranchDTO newBranch) {

        if(!isValidBranch(newBranch))
        {
            BranchResponse branchResponse=buildRes(null,HttpStatus.BAD_REQUEST.toString(), "Enter Proper Inputs");
            return new ResponseEntity<>(branchResponse,HttpStatus.BAD_REQUEST);

        }
        txn_branch t=toBranch(newBranch);
        t.setCreated_date(LocalDate.now());
        t.setUpdated_date(LocalDate.now());
        branchRepository.save(t);
        BranchResponse branchResponse=buildRes(t,HttpStatus.CREATED.toString(),"Created Branch");
        return new ResponseEntity<>(branchResponse,HttpStatus.CREATED);
    }

    public ResponseEntity<BranchResponse> getBranches(int pageNo,int pageSize)
    {
//        int pageSize=5;
//        int pageNo=1;
        Pageable p= PageRequest.of(pageNo,pageSize);
        Page<txn_branch> PageBranch = this.branchRepository.findAll(p);
        List<txn_branch> allBranches=PageBranch.getContent();
        if(allBranches.isEmpty()){
//            throw new BranchNotFoundException("There are no Branches");
            BranchResponse branchResponse=buildRes(null,HttpStatus.BAD_REQUEST.toString(), "No Branches Available");
            return new ResponseEntity<>(branchResponse,HttpStatus.BAD_REQUEST);
        }
        BranchResponse branchResponse=buildRes(allBranches,HttpStatus.OK.toString(), "above are the pages of result");
        return new ResponseEntity<>(branchResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BranchResponse> getBranches() {
        List<txn_branch> allBranches=branchRepository.findAll();
        if(allBranches.isEmpty()){
            BranchResponse branchResponse=buildRes(null,HttpStatus.BAD_REQUEST.toString(), "No Branches Available");
            return new ResponseEntity<>(branchResponse,HttpStatus.BAD_REQUEST);
        }
        else
        {

            BranchResponse branchResponse=buildRes(allBranches,HttpStatus.OK.toString(), "Fetched All Branches");
            return new ResponseEntity<>(branchResponse,HttpStatus.OK);

        }

    }

    @Override
    public ResponseEntity<BranchResponse> getBranch(int branch_id) {

         if (!branchRepository.findById(branch_id).isPresent())
         {
             BranchResponse branchResponse=buildRes(null,HttpStatus.BAD_REQUEST.toString(), "Branch with the following ID is not present");
             return new ResponseEntity<>(branchResponse,HttpStatus.BAD_REQUEST);

         }
         else
         {
             txn_branch t=branchRepository.findById(branch_id).get();
             BranchResponse branchResponse=buildRes(t,HttpStatus.OK.toString(),"Branch will the ID Fetched");
             return new ResponseEntity<>(branchResponse,HttpStatus.OK);
         }
    }

    @Override
    public ResponseEntity<BranchResponse> updateById(BranchDTO updateBranch, int id) {
//        if(!isValidBranch(updateBranch))
//        {
//            BranchResponse branchResponse=buildRes(null,HttpStatus.BAD_REQUEST.toString(), "Not a valid branch");
//            return new ResponseEntity<>(branchResponse,HttpStatus.BAD_REQUEST);
//
//        }

        {
            if(!branchRepository.findById(id).isPresent())
            {
                saveBranch(updateBranch);
            }
            else
            {
                txn_branch t= toBranch(updateBranch);

                txn_branch oldt=branchRepository.findById(id).get();
                oldt.setName(t.getName());
                oldt.setUpdated_by(t.getUpdated_by());
                oldt.setAddress(t.getAddress());
                oldt.setUpdated_date(LocalDate.now());
                oldt.setMobile_number(t.getMobile_number());
                oldt.setEmail(t.getEmail());
                oldt.setBranch_type(t.getBranch_type());

                txn_branch updatedBranch=this.branchRepository.save(oldt);
                BranchResponse branchResponse=buildRes(updatedBranch,HttpStatus.OK.toString(), "Branch successfully updated");
                return new ResponseEntity<>(branchResponse,HttpStatus.OK);
            }

        }
        
        return null;
    }


    @Override
    public ResponseEntity<BranchResponse> SearchBranch(String keyword) {
        List<txn_branch> result=this.branchRepository.findByNameContaining(keyword);
        if (result.isEmpty())
        {
            BranchResponse branchResponse=buildRes(result,HttpStatus.BAD_REQUEST.toString(),"no branch found");
            return new ResponseEntity<>(branchResponse,HttpStatus.BAD_REQUEST);
        }
        else
        {
            BranchResponse branchResponse=buildRes(result,HttpStatus.OK.toString(), "Above are the result of the search");
            return new ResponseEntity<>(branchResponse,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<BranchResponse> deleteAllBranches() {
        if(branchRepository.findAll().isEmpty())
        {
            BranchResponse branchResponse=buildRes(null,HttpStatus.BAD_REQUEST.toString(), "No Branches are available");
            return new ResponseEntity<>(branchResponse,HttpStatus.BAD_REQUEST);
        }
        else {
            branchRepository.deleteAll();
            BranchResponse branchResponse=buildRes(null,HttpStatus.OK.toString(), "All Branches are deleted");
            return new ResponseEntity<>(branchResponse,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<BranchResponse> deleteBranchById(int id) {
        if(!branchRepository.findById(id).isPresent())
        {
            BranchResponse branchResponse=buildRes(null,HttpStatus.BAD_REQUEST.toString(), "No Branch with following Id is present");
            return new ResponseEntity<>(branchResponse,HttpStatus.BAD_REQUEST);
        }
        else
        {
            branchRepository.deleteById(id);
            BranchResponse branchResponse=buildRes(null,HttpStatus.OK.toString(), "Branch with the id has been deleted");
            return new ResponseEntity<>(branchResponse,HttpStatus.OK);

        }
    }

//    public List<txn_branch> searchBrach(String keywords) {
//        List<txn_branch> = this.branchRepository.
//    }


//    @Override
//    public txn_branch updateBranch(BranchDTO updateBranch, int id) {
//        txn_branch t=toBranch(updateBranch);
//        boolean b=branchRepository.existsById(id);
//
////        if(b)
////        {
////
////
////            return branchRepository.save(t);
////
////        }
//
//
//    }
//    @Override
//    public static txn_branch saveBranch(txn_branch newBranch) {
//        return branchRepository.save(newBranch);
//    }
}
