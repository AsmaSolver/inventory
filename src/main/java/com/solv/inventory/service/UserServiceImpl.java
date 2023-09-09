package com.solv.inventory.service;

import com.solv.inventory.dto.Response;
import com.solv.inventory.exceptions.BadArgumentException;
import com.solv.inventory.exceptions.UserNotFoundException;
import com.solv.inventory.dao.UserRepository;
import com.solv.inventory.dto.RegisterUserDto;
import com.solv.inventory.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import static com.solv.inventory.mapper.UserMapper.*;
import static com.solv.inventory.mapper.UserMapper.toUser;
import static com.solv.inventory.util.EmailValidator.isValidEmail;
import static com.solv.inventory.util.MobileNumberValidator.isValidMobileNumber;
import static com.solv.inventory.util.NameValidator.*;
import static com.solv.inventory.util.UserTypeValidator.isValidUserType;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public String saveUser(String userName) {
        return userName + " User Saved Successfully";
    }

    @Override
    public Response createNewUser(RegisterUserDto registerUserDto) {
       if(!isValidUser(registerUserDto)) {
           return buildResponse(HttpStatus.BAD_REQUEST.toString(), "Give proper Inputs");
       }
        else{
            User user = toUser(registerUserDto);
            user.setCreatedDate(LocalDate.now());
            user.setUpdatedDate(LocalDate.now());
            user = userRepository.save(user);
            return buildResponse(HttpStatus.CREATED.toString(), "Successfully Created User");
       }
    }
    private Response buildResponse(String statusCode,String message){
        return Response.builder()
                .statusCode(statusCode)
                .statusMessage(message)
                .build();
    }
    private boolean isValidUser(RegisterUserDto registerUserDto)  {
        try {
            isValidName(registerUserDto.getName());
            isValidMobileNumber(registerUserDto.getMobNum());
            isValidEmail(registerUserDto.getEmail());
            isValidUserType(registerUserDto.getUserType());
            return true;
        }
        catch(BadArgumentException exception){

        }
        return false;
    }
    @Override
    public RegisterUserDto getById(int id) throws UserNotFoundException {
        if(!userRepository.findById(id).isPresent()){
            throw new UserNotFoundException("User with id "+id+" does not exists");
        }
        else {
            User user = userRepository.findById(id).get();
            return toUserDto(user);
        }
    }
    @Override
    public List<RegisterUserDto> getAll() throws UserNotFoundException {
        List<User> userList=this.userRepository.findAll();
        if(userList.isEmpty()){
            throw new UserNotFoundException("Currently there are no users registered");
        }
        else {
            return toUserDtoList(userList);
        }
    }

    @Override
    public Response updateById(RegisterUserDto registerUserDto,int id) {
        if(!isValidUser(registerUserDto)){
            return buildResponse(HttpStatus.BAD_REQUEST.toString(), "Invalid Fields");
        }
        else{
            if(!userRepository.findById(id).isPresent()){
                User user=toUser(registerUserDto);
                user.setCreatedDate(LocalDate.now());
                user.setUpdatedDate(LocalDate.now());
                User response= this.userRepository.save(user);
                return buildResponse(HttpStatus.OK.toString(), "User Created");

            }
            else {
                User user = toUser(registerUserDto);
                User userDB = userRepository.findById(id).get();
                if (Objects.nonNull(user.getName()) && !"".equalsIgnoreCase(user.getName())) {
                    userDB.setName(user.getName());
                }
                if (Objects.nonNull(user.getMobNum()) && !"".equalsIgnoreCase(user.getMobNum())) {
                    userDB.setMobNum(user.getMobNum());
                }
                if (Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())) {
                    userDB.setEmail(user.getEmail());
                }
                if (Objects.nonNull(user.getUserType()) && !"".equalsIgnoreCase(String.valueOf(user.getUserType()))) {
                    userDB.setUserType(user.getUserType());
                }
                user.setUpdatedDate(LocalDate.now());
                User response = this.userRepository.save(userDB);
                return buildResponse(HttpStatus.OK.toString(), "User Updated");
            }
        }

    }
    @Override
    public Response deleteAll() throws UserNotFoundException {
        if(this.userRepository.findAll().isEmpty()){
            throw new UserNotFoundException("There are no registered users");
        }
        else {
            this.userRepository.deleteAll();
            return buildResponse(HttpStatus.OK.toString(), "Successfully deleted all users");
        }
    }

    @Override
    public Response deleteUserById(int id) {
        if(!userRepository.findById(id).isPresent()) {
            return buildResponse(HttpStatus.BAD_REQUEST.toString(), "User with id "+id+" is not present");
        }
        else{
            this.userRepository.deleteById(id);
            return buildResponse(HttpStatus.OK.toString(), "User Deleted");
        }

    }
}