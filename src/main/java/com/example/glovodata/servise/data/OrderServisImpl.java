package com.example.glovodata.servise.data;


import com.example.glovodata.converter.OrderConverter;
import com.example.glovodata.dto.OrderDto;


import com.example.glovodata.model.data.Order;
import com.example.glovodata.repository.OrderRepository;
import com.example.glovodata.servise.OrderServise;

import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServisImpl implements OrderServise {
    private final OrderRepository orderRepository;
   private  final OrderConverter orderConverter;




    @Override
    public OrderDto getOrderById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow();
        return orderConverter.fromModel(order);
    }

    @Override
    public List<OrderDto> getOrders() {
        Iterable<Order>orders = orderRepository.findAll();
        return orderConverter.fromModel(orders);
    }

    @Override
    public void save(OrderDto dto) {
        Order order = orderConverter.toModel(dto);
        orderRepository.save(order);
        log.debug("order saved"+ order);

    }

    @Override
    public void update(Integer id, OrderDto dto) {
        Order old = orderRepository.findById(id).orElseThrow();
        Order newOrder = orderConverter.toModel(old,dto);
        orderRepository.save(newOrder);
        log.debug("order update "+newOrder);


    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
        log.debug("order delete");

    }
}
