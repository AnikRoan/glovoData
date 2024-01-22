package com.example.glovodata.servise.data;


import com.example.glovodata.converter.ProductConverter;
import com.example.glovodata.dto.ProductDto;

import com.example.glovodata.model.data.Product;
import com.example.glovodata.repository.ProductRepository;
import com.example.glovodata.servise.ProductServise;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServisImpl implements ProductServise {
    private final ProductRepository productRepository;

    private final ProductConverter productConverter;


    @Override
    public ProductDto getProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow();
        return productConverter.fromModel(product);
    }

    @Override
    public List<ProductDto> getProducts() {
        Iterable<Product> products = productRepository.findAll();
        return productConverter.fromModel(products);
    }

    @Override
    public void save(ProductDto dto) {
        Product product = productConverter.toModel(dto);
        productRepository.save(product);
        log.debug("product saved " + product);

    }

    @Override
    public void update(Integer id, ProductDto dto) {
        Product old = productRepository.findById(id).orElseThrow();
        Product newProduct = productConverter.toModel(old, dto);
        productRepository.save(newProduct);
        log.debug("product updated " + newProduct);

    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
        log.debug("product deleted");

    }
}
