package com.example.glovodata.servise;

import com.example.glovodata.dto.OrderDto;

import java.util.List;

public interface OrderServise {
    OrderDto getOrderById(Integer id);

    List<OrderDto> getOrders();

    void save(OrderDto dto);

    void update(Integer id, OrderDto dto);

    void delete(Integer id);

}
