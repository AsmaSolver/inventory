package com.solv.inventory.service;

import com.solv.inventory.entity.User;
import java.util.List;

public interface UserService {
     String saveUser(String name);
     User getUserById(int id);
     User createUser(User user);

     List<User> getAllUser();
}