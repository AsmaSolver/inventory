package com.solv.inventory.service;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public String saveUser(String userName) {
        return userName + " User Saved Successfully";
    }
}
