package com.solv.inventory.service;

import com.solv.inventory.dao.UserRepository;
import com.solv.inventory.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static com.solv.inventory.mapper.UserMapper.toUserDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userServiceImpl;

    User user;
    @BeforeEach
    void setUp(){
       user=User.builder()
               .name("Rahul")
               .email("Rahul@gmail.com")
               .userType("ADMIN")
               .mobNum("9876543219")
               .build();
    }
    @Test
    void createNewUser() {
        given(userRepository.save(user)).willReturn(user);
        User savedUser= (User) userServiceImpl.createNewUser(toUserDto(user)).getBody().getData();
        assertThat(savedUser).isNotNull();
    }

    @Test
    void getById() {
        given(userRepository.findById(1)).willReturn(Optional.ofNullable(user));
        assertThat(((User)(userServiceImpl.getById(1).getBody()).getData())).isEqualTo(user);
    }
    @Test
    void getAll() {
        User user2=User.builder()
                .name("Rajeev")
                .email("Rajeev@gmail.com")
                .mobNum("7890123456")
                .userType("ADMIN")
                .build();
        List<User> list=new ArrayList<>();
        list.add(user);
        list.add(user2);
        given(userRepository.findAll()).willReturn(list);
        List<User> usersList= (List<User>) userServiceImpl.getAll().getBody().getData();
        assertThat(usersList).isNotNull();
        assertThat(usersList.size()).isEqualTo(2);
    }

    @Test
    void updateById() {
        given(userRepository.save(user)).willReturn(user);
        user.setEmail("singh.ram@gmail.com");
        user.setName("Ram");
        User updatedUser = (User) userServiceImpl.updateById(toUserDto(user),1).getBody().getData();
        assertThat(updatedUser.getEmail()).isEqualTo("singh.ram@gmail.com");
        assertThat(updatedUser.getName()).isEqualTo("Ram");
    }

    @Test
    void deleteAll() {

    }
    @Test
    void deleteUserById() {
    }
}