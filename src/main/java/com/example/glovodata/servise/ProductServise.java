package com.example.glovodata.servise;

import com.example.glovodata.dto.OrderDto;
import com.example.glovodata.dto.ProductDto;

import java.util.List;

public interface ProductServise {
    ProductDto getProductById(Integer id);
    List<ProductDto> getProducts();
    void save(ProductDto dto);
    void update(Integer id, ProductDto dto);
    void delete(Integer id);
}
