package com.solv.inventory.service;

import com.solv.inventory.entity.User;
public interface UserService {
     String saveUser(String name);
     User saveBranch(User newBranch);
}