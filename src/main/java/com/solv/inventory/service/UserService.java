package com.solv.inventory.service;

import com.solv.inventory.dto.RegisterUserDto;
import com.solv.inventory.dto.Response;
import com.solv.inventory.exceptions.BadArgumentException;
import com.solv.inventory.exceptions.UserNotFoundException;
import java.util.List;

public interface UserService {
     String saveUser(String name);
     Response createNewUser(RegisterUserDto registerUserDto) throws BadArgumentException;

     RegisterUserDto getById(int id) throws UserNotFoundException;

     List<RegisterUserDto> getAll() throws UserNotFoundException;

     Response updateById(RegisterUserDto registerUserDto,int id);

     Response deleteAll() throws UserNotFoundException;

     Response deleteUserById(int id);

}