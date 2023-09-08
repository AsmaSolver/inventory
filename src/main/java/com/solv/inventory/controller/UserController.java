package com.solv.inventory.controller;
import com.solv.inventory.dto.RegisterUserDto;
import com.solv.inventory.dto.Response;
import com.solv.inventory.exceptions.BadArgumentException;
import com.solv.inventory.exceptions.UserNotFoundException;
import com.solv.inventory.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Api(value = "Services for user")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @ApiOperation("- register new user")
    @PostMapping(value = "/",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response> registerUser(@RequestBody RegisterUserDto registerUserDto) throws BadArgumentException {
        Response response=this.userServiceImpl.createNewUser(registerUserDto);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
    @ApiOperation("- get all the users")
    @GetMapping("/users")
    public ResponseEntity<List<RegisterUserDto>> getAllUser() throws UserNotFoundException {
        return new ResponseEntity<>(this.userServiceImpl.getAll(),HttpStatus.OK);
    }
    @ApiOperation("- get an existing user by their id")
    @GetMapping("/{id}")
    public ResponseEntity<RegisterUserDto> getById(@PathVariable("id") int userId) throws UserNotFoundException {
        RegisterUserDto registerUserDto=this.userServiceImpl.getById(userId);
        return new ResponseEntity<>(registerUserDto,HttpStatus.OK);
    }
    @ApiOperation("-update an existing user")
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateUser(@RequestBody RegisterUserDto registerUserDto, @PathVariable("id") int id){
        Response response=this.userServiceImpl.updateById(registerUserDto,id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @ApiOperation("-deletes all the existing users")
    @DeleteMapping("/users")
    public ResponseEntity<Response> deleteAll() throws UserNotFoundException {
        Response response=this.userServiceImpl.deleteAll();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @ApiOperation("-deletes a user of given id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable("id") int id){
        Response response=this.userServiceImpl.deleteUserById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}