package com.solv.inventory.service;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import static com.solv.inventory.config.Log4j2Config.getLoggerVariable;
import static com.solv.inventory.config.Log4j2Config.logger;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger serviceLogger = getLoggerVariable("UserServiceImpl");
    @Override
    public String saveUser(String userName) {
        logger.info("Create User Request : "+ userName);
        return userName + " User Saved Successfully";
    }
//    @Override
//    public txn_branch saveBranch(txn_branch newBranch) {
//        return userRepository.save(newBranch);
//    }
}
