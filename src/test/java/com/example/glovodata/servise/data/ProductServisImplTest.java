package com.example.glovodata.servise.data;


import com.example.glovodata.converter.ProductConverter;
import com.example.glovodata.dto.ProductDto;

import com.example.glovodata.model.data.Product;
import com.example.glovodata.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServisImplTest {

    private static final int PRODUCT_ID = 111;

    @InjectMocks
    private ProductServisImpl testInstance;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductConverter productConverter;

    @Mock
    private Product product;

    @Mock
    private List<Product> products;

    private ProductDto dto;


    @BeforeEach
    public void init() {
        dto = new ProductDto();
        dto.setId(PRODUCT_ID);
        dto.setCost(11.1);
    }


    @Test
    void shouldReturnProductById() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        when(productConverter.fromModel(product)).thenReturn(dto);

        ProductDto result = testInstance.getProductById(PRODUCT_ID);

        verify(productRepository).findById(PRODUCT_ID);
        verify(productConverter).fromModel(product);
        assertNotNull(result);
        assertEquals(PRODUCT_ID, result.getId());


    }

    @Test
    void shouldReturnAllProducts() {
        List<ProductDto> dtos = products.stream()
                .map(productConverter::fromModel)
                .collect(Collectors.toList());
        when(productRepository.findAll()).thenReturn(products);
        when(productConverter.fromModel(products)).thenReturn(dtos);

        List<ProductDto> result = testInstance.getProducts();

        verify(productRepository).findAll();
        verify(productConverter).fromModel(products);

        assertNotNull(result);

        assertEquals(dtos.size(), result.size());

        assertEquals(dtos, result);
    }

    @Test
    void shouldReturnSaveTest() {
        when(productConverter.toModel(dto)).thenReturn(product);

        testInstance.save(dto);

        verify(productConverter).toModel(dto);
        verify(productRepository).save(product);

    }

    @Test
    void shouldReturnUpdateTest() {
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));
        when(productConverter.toModel(product, dto)).thenReturn(new Product());

        testInstance.update(PRODUCT_ID, dto);

        verify(productRepository).findById(PRODUCT_ID);
        verify(productConverter).toModel(product, dto);
        verify(productRepository).save(any());
    }

    @Test
    void shouldReturnDeleteTest() {
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));

        testInstance.getProductById(PRODUCT_ID);
        testInstance.delete(PRODUCT_ID);

        verify(productRepository).findById(PRODUCT_ID);
        verify(productRepository).deleteById(PRODUCT_ID);

        verify(productRepository, times(1)).deleteById(PRODUCT_ID);


    }


}