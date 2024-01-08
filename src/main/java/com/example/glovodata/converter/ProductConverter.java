package com.example.glovodata.converter;

import com.example.glovodata.dto.ProductDto;
import com.example.glovodata.model.data.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {

    public ProductDto fromModel(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .cost(product.getCost())
                .build();
    }

    public List<ProductDto> fromModel(Iterable<Product> products){
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product:products){
            productDtos.add(fromModel(product));
        }
        return productDtos;
    }

    public Product toModel(ProductDto dto){
        return Product.builder()
                .name(dto.getName())
                .cost(dto.getCost())
                .build();
    }

    public Product toModel(Product product,ProductDto dto){
        product.setName(dto.getName());
        product.setCost(dto.getCost());
        return product;
    }
}
