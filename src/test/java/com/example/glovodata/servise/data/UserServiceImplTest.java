package com.example.glovodata.servise.data;

import com.example.glovodata.dto.UserDto;
import com.example.glovodata.model.entity.Role;
import com.example.glovodata.model.entity.User;
import com.example.glovodata.repository.RoleRepository;
import com.example.glovodata.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private static final int User_ID = 111;
    @InjectMocks
    private UserServiceImpl testInstance;

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    private UserDto dto;

    @Test
    void saveUser(){
        dto = UserDto.builder()
                .firstName("Anna")
                .email("x@c.com")
                .password("123")
                .build();


        Role role = new Role();
        role.setName("ROLE_ADMIN");
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(role);

        when(passwordEncoder.encode(dto.getPassword())).thenReturn("hashedPassword");

        testInstance.saveUser(dto);

        verify(userRepository).save(userArgumentCaptor.capture());


        User savedUser = userArgumentCaptor.getValue();
        assertEquals("Anna", savedUser.getName());
        assertEquals("x@c.com", savedUser.getEmail());
        assertEquals("hashedPassword", savedUser.getPassword());


        assertEquals(1, savedUser.getRoles().size());
        assertEquals("ROLE_ADMIN", savedUser.getRoles().iterator().next().getName());
    }



    }






