package com.example.glovodata.controller;

import com.example.glovodata.dto.UserDto;
import com.example.glovodata.model.data.Order;
import com.example.glovodata.model.data.Product;
import com.example.glovodata.model.entity.User;
import com.example.glovodata.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class AuthControllerMVCTest {
    @Value("${local.server.port}")
    private int port;

    private static String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;


    @Autowired
    private UserRepository userRepository;


    @Test
    void shouldCreateOrderTest() {

        UserDto userDto = UserDto.builder()
                .firstName("Anna")
                .email("q@q.com")
                .password("123")
                .build();

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                baseUrl +port+ "/register/save",
                userDto,
                String.class);




        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());




    }




}