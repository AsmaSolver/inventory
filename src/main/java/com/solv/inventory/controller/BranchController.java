package com.solv.inventory.controller;

import com.solv.inventory.exceptions.BadArgsException;
import com.solv.inventory.exceptions.BranchNotFoundException;
import com.solv.inventory.model.dto.BranchDTO;
import com.solv.inventory.model.dto.BranchResponse;
import com.solv.inventory.model.dto.Response;
import com.solv.inventory.model.entity.txn_branch;
import com.solv.inventory.service.BranchServiceImpl;
import com.solv.inventory.service.UserServiceImpl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@Api(value = "Services for branch")
@RestController
@RequestMapping("/branch")
//@Slf4j
public class BranchController {

    @Autowired
    private BranchServiceImpl branchService;
    @PostMapping("/cre_branch")
    public ResponseEntity<BranchResponse> newBranch(@RequestBody BranchDTO newBranch) throws BadArgsException {
        return branchService.saveBranch(newBranch);
    }

//    @PutMapping("/up_branch/{branch_Id}")
//    public txn_branch updateBranch(@RequestBody BranchDTO updateBranch, @PathVariable("branch_Id") int id)
//    {
//        return branchService.updateBranch(updateBranch,id);
//    }
//    @DeleteMapping("/delete/")

    @GetMapping("/getbranches/page")
    public ResponseEntity<BranchResponse> getbranches(@RequestParam(value = "PageNo",defaultValue = "0",required = false) int PageNo,
                                                      @RequestParam(value = "PageSize", defaultValue = "5",required = false)int PageSize) throws BranchNotFoundException
    {
        return branchService.getBranches(PageNo,PageSize);
    }
    @GetMapping("/getbranches")
    public ResponseEntity<BranchResponse> getbranches()
    {
        return branchService.getBranches();
    }

    @GetMapping("/getbranch/{id}")
    public ResponseEntity<BranchResponse> getbranch(@PathVariable("id") int branchId)
    {
        return branchService.getBranch(branchId);
    }

    @GetMapping("/search/{keywords}")
    public ResponseEntity<BranchResponse> searchBranchTitle(@PathVariable("keywords")String keywords)
    {
//        List<txn_branch> result=this.branchService.SearchBranch(keywords);
//        return new ResponseEntity<List<txn_branch>>(result, HttpStatus.OK);
        return branchService.SearchBranch(keywords);
    }

    @PutMapping("/updatebranch/{id}")
    public ResponseEntity<BranchResponse> updateById(BranchDTO updateBranch,@PathVariable("id") int id)
    {
        return branchService.updateById(updateBranch,id);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<BranchResponse> deleteAllBranches()
    {
        return branchService.deleteAllBranches();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BranchResponse> deleteBranchById(@PathVariable("id") int id)
    {
        return branchService.deleteBranchById(id);
    }

}
