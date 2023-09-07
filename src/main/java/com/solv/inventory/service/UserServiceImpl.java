package com.solv.inventory.service;

import com.solv.inventory.dao.UserRepository;
import com.solv.inventory.entity.User;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.solv.inventory.config.Log4j2Config.getLoggerVariable;
import static com.solv.inventory.config.Log4j2Config.logger;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    private static final Logger serviceLogger = getLoggerVariable("UserServiceImpl");
    @Override
    public String saveUser(String userName) {
        logger.info("Create User Request : "+ userName);
        return userName + " User Saved Successfully";
    }
    @Override
    public User saveBranch(User newBranch) {
        return userRepository.save(newBranch);
    }
}
