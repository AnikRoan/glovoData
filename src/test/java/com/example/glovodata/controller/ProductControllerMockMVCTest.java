package com.example.glovodata.controller;

import com.example.glovodata.dto.ProductDto;
import com.example.glovodata.model.data.Order;
import com.example.glovodata.model.data.Product;
import com.example.glovodata.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class ProductControllerMockMVCTest {
    @Value("${local.server.port}")
    private int port;

    private static String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;




    @Test
    void shouldGetProduct()throws Exception{
        Product product = Product.builder()
                .name("test")
                .cost(10.1)
                .build();
        Product saveProduct = productRepository.save(product);

        ProductDto result = restTemplate.getForObject(baseUrl+port+"/api/products/"
                +saveProduct.getId(),ProductDto.class);

        assertNotNull(saveProduct);
        assertEquals(saveProduct.getId(),result.getId());

    }

    @Test
    void shouldCreateProduct(){
        Product product = Product.builder()
                .name("test")
                .cost(10.1)
                .build();



        ResponseEntity<Product> responseEntity = restTemplate.postForEntity(
                baseUrl + port + "/api/products/create",
                product,
                Product.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void shouldDeleteProductById(){
        Product product = Product.builder()
                .name("test")
                .cost(10.1)
                .build();

        Product saveProduct = productRepository.save(product);

        ProductDto result = restTemplate.getForObject(baseUrl+port+"/api/products/"
                +saveProduct.getId(),ProductDto.class);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                baseUrl+port+"/api/products/"+result.getId(), HttpMethod.DELETE
                ,null,Void.class
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }


}