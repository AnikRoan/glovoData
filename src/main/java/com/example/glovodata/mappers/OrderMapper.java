//package com.example.glovodata.mappers;
//
//import com.example.glovodata.dto.OrderDto;
//import com.example.glovodata.model.data.Order;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.MappingTarget;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
////(uses = ProductMapper.class, componentModel = "spring")
//@Component
//@Mapper
//public interface OrderMapper {
//    // @Mapping(target = "products", source = "products")
//    Order orderDtoToOrder(OrderDto dto);
//
//    // @Mapping(target = "products", source = "products")
//    OrderDto orderToOrderDto(Order order);
//
//
//    @Mapping(source = "dto.id", target = "id")
//    @Mapping(source = "dto.date", target = "date")
//    @Mapping(source = "dto.cost", target = "cost")
//    @Mapping(source = "dto.products", target = "products")
//    Order toModelOrder(Order order, OrderDto dto);
//
//    @Mapping(target = "products", source = "products")
//    List<OrderDto> toOrderDtoList(Iterable<Order> orders);
//
//    // List<Order> fromOrderDtoList(Iterable<OrderDto> dtos);
//
//
//}
