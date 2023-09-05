package com.solv.inventory.service;

import com.solv.inventory.dao.UserRepository;
import com.solv.inventory.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public String saveUser(String userName) {
        return userName + " User Saved Successfully";
    }

    @Override
    public User getUserById(int id) {
        return this.userRepository.findById(id).get();
    }

    @Override
    public User createUser(User user) {
        System.out.println("User"+ user);
        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
       return this.userRepository.findAll();
    }


}
