package com.example.glovodata.controller;

import com.example.glovodata.dto.OrderDto;
import com.example.glovodata.dto.ProductDto;
import com.example.glovodata.model.data.Order;
import com.example.glovodata.model.data.Product;
import com.example.glovodata.repository.OrderRepository;
import com.example.glovodata.respons.ApiResponse;
import com.example.glovodata.servise.OrderServise;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@ActiveProfiles("dev")
class OrderControllerMockMVCTest {
    @Value("${local.server.port}")
    private int port;
    private static String baseUrl = "http://localhost:";


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OrderRepository orderRepository;


    @Test
    void shouldGetOrders() throws Exception {
        Order order = Order.builder()
                .date(LocalDate.now())
                .cost(11.1)
                .products(List.of(Product.builder()
                        .cost(22.2)
                        .name("test")
                        .build()))
                .build();

        Order saveOrder = orderRepository.save(order);

        OrderDto result = restTemplate.getForObject(baseUrl + port + "/api/orders/"
                + saveOrder.getId(), OrderDto.class);


        assertNotNull(saveOrder);
        assertEquals(saveOrder.getId(), result.getId());


    }


    @Test
    void shouldCreateOrderTest() {

        Order order = Order.builder()
                .date(LocalDate.now())
                .cost(11.1)
                .products(List.of(Product.builder()
                        .cost(22.2)
                        .name("test")
                        .build()))
                .build();


        ResponseEntity<Order> responseEntity = restTemplate.postForEntity(
                baseUrl + port + "/api/orders/create",
                order,
                Order.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());




    }
    @Test
    void shouldDeleteOrderById(){
        Order order = Order.builder()
                .date(LocalDate.now())
                .cost(11.1)
                .products(List.of(Product.builder()
                        .cost(22.2)
                        .name("test")
                        .build()))
                .build();


   Order saveOrder = orderRepository.save(order);

        OrderDto result = restTemplate.getForObject(baseUrl+port+"/api/orders/"
                +saveOrder.getId(),OrderDto.class);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                baseUrl+port+"/api/orders/"+result.getId(), HttpMethod.DELETE
                ,null,Void.class
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

}