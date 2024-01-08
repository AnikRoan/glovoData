package com.example.glovodata.mappers;

import com.example.glovodata.dto.ProductDto;
import com.example.glovodata.model.data.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
//
//import java.util.List;
//@Component
//@Mapper
//public interface ProductMapper {
//    ProductDto prDto(Product product);
//
//    Product prod(ProductDto dto);
//
//    //   public Product toModel(Product product,ProductDto dto){
//    //        product.setName(dto.getName());
//    //        product.setCost(dto.getCost());
//    @Mapping(source = "dto.id", target = "id")
//    @Mapping(source = "dto.name", target = "name")
//    @Mapping(source = "dto.cost", target = "cost")
//    Product toModelProduct(Product product, ProductDto dto);
//
//    List<ProductDto> productDtoList(Iterable<Product> products);
//
//    List<Product> productList(List<ProductDto> dto);
//}
