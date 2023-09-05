package com.solv.inventory.controller;
import com.solv.inventory.entity.User;
import com.solv.inventory.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(value = "Services for user")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @ApiOperation("- register new user")
    @PostMapping(value = "/",produces = {MediaType.APPLICATION_JSON_VALUE})
    public User newUser(@RequestBody User user) {
        return this.userServiceImpl.createUser(user);
    }
}