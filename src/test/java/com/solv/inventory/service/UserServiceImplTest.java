package com.solv.inventory.service;

import com.solv.inventory.dao.UserRepository;
import com.solv.inventory.dto.UserResponse;
import com.solv.inventory.entity.User;
import com.solv.inventory.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.*;
import static com.solv.inventory.mapper.UserMapper.toUserDto;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.empty;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @MockBean
    UserRepository userRepository;
    @Autowired
    UserServiceImpl userServiceImpl;

    @Test
    void testNewUserOk() {
        User user=User.builder()
               .name("Rahul")
               .email("Rahul@gmail.com")
               .userType("ADMIN")
               .mobNum("9876543219")
               .build();

        when(userRepository.save(any())).thenReturn(user);

        ResponseEntity<UserResponse> userResponse= userServiceImpl.createNewUser(toUserDto(user));

        assertEquals("200 OK",userResponse.getStatusCode().toString());
        assertEquals("User registered successfully", requireNonNull(userResponse.getBody()).getStatusMessage());
        verify(userRepository).save(any());
    }
    @Test
    void testNewUserNotOk(){
        User user=User.builder()
                .name("Rahul")
                .email("Rahul@gmail.com")
                .userType("ADMIN")
                .mobNum("98765432191")
                .build();

        when(userRepository.save(any())).thenReturn(user);

        ResponseEntity<UserResponse> userResponseResponseEntity=userServiceImpl.createNewUser(toUserDto(user));

        assertEquals("400 BAD_REQUEST",userResponseResponseEntity.getStatusCode().toString());
        assertEquals("Invalid Inputs", requireNonNull(userResponseResponseEntity.getBody()).getStatusMessage());
        verify(userRepository,times(0)).save(any());
    }
    @Test
    void testGetByIdOk() {
        User user=User.builder()
                .name("Rahul")
                .email("Rahul@gmail.com")
                .userType("ADMIN")
                .mobNum("9876543211")
                .build();

        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));

        ResponseEntity<UserResponse> userResponseResponseEntity=userServiceImpl.getById(1);

        assertEquals("200 OK",userResponseResponseEntity.getStatusCode().toString());
        assertEquals("User with given id exists", requireNonNull(userResponseResponseEntity.getBody())
                .getStatusMessage());
        verify(userRepository,times(2)).findById(1);

    }
    @Test
    void testGetByIdNotOk() {

        when(userRepository.findById(2)).thenReturn(empty());

        ResponseEntity<UserResponse> userResponseResponseEntity=userServiceImpl.getById(2);

        assertEquals("400 BAD_REQUEST",userResponseResponseEntity.getStatusCode().toString());
        assertEquals("User with id 2 does not exists", requireNonNull(userResponseResponseEntity
                .getBody()).getStatusMessage());
        verify(userRepository,times(0)).findById(1);
    }
    @Test
    void testGetAllOk() {
        User user2=User.builder()
                .name("Rajeev")
                .email("Rajeev@gmail.com")
                .mobNum("7890123456")
                .userType("ADMIN")
                .build();

        User user=User.builder()
                .name("Rahul")
                .email("Rahul@gmail.com")
                .userType("ADMIN")
                .mobNum("9876543211")
                .build();
        List<User> list=new ArrayList<>();
        list.add(user);
        list.add(user2);

        when(userRepository.findAll()).thenReturn(list);

        ResponseEntity<UserResponse> userResponseResponseEntity=userServiceImpl.getAll();

        assertEquals("200 OK",userResponseResponseEntity.getStatusCode().toString());
        assertEquals("Accepted", requireNonNull(userResponseResponseEntity.getBody()).getStatusMessage());
        verify(userRepository,times(1)).findAll();
    }
    @Test
    void testGetAllNotOk() {

        when(userRepository.findAll()).thenReturn(EMPTY_LIST);

        ResponseEntity<UserResponse> userResponseResponseEntity=userServiceImpl.getAll();

        assertEquals("200 OK",userResponseResponseEntity.getStatusCode().toString());
        assertEquals("Currently there are no registered users", requireNonNull(userResponseResponseEntity
                .getBody()).getStatusMessage());
        verify(userRepository,times(1)).findAll();
    }
    @Test
    void testUpdateUserOk(){
        User user=User.builder()
                .name("Rahul")
                .email("Rahul@gmail.com")
                .userType("ADMIN")
                .mobNum("9876543211")
                .build();

        when(userRepository.save(user)).thenReturn(user);

        ResponseEntity<UserResponse> userResponseResponseEntity=userServiceImpl.updateById(toUserDto(user),1);

        assertEquals("201 CREATED",userResponseResponseEntity.getStatusCode().toString());
        assertEquals("User Created", requireNonNull(userResponseResponseEntity.getBody()).getStatusMessage());
        verify(userRepository,times(1)).save(user);
    }
    @Test
    void testUpdateUserFailed(){
        User user=User.builder()
                .name("Rahul")
                .email("Rahul@gmail.com")
                .userType("ADMIN")
                .mobNum("98765432111")
                .build();

        when(userRepository.save(user)).thenReturn(user);

        ResponseEntity<UserResponse> userResponseResponseEntity=userServiceImpl.updateById(toUserDto(user),1);

        assertEquals("400 BAD_REQUEST",userResponseResponseEntity.getStatusCode().toString());
        assertEquals("Invalid Fields", requireNonNull(userResponseResponseEntity.getBody()).getStatusMessage());
        verify(userRepository,times(0)).save(user);
    }

}