package com.example.glovodata.servise.data;

import com.example.glovodata.converter.OrderConverter;
import com.example.glovodata.dto.OrderDto;
import com.example.glovodata.model.data.Order;
import com.example.glovodata.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServisImplTest {
    private static final int ORDER_ID = 111;

    @InjectMocks
    private OrderServisImpl testInstance;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderConverter orderConverter;

    @Mock
    private Order order;
    @Mock
    private List<Order> orders;

    private OrderDto dto;
    private List<OrderDto> dtos;

    @BeforeEach
    public void init() {
        dto = new OrderDto();
        dto.setId(111);
        dto.setCost(11.1);
    }

    @Test
    void shouldReturnOrderById() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
        when(orderConverter.fromModel(order)).thenReturn(dto);

        OrderDto result = testInstance.getOrderById(ORDER_ID);

        verify(orderRepository).findById(ORDER_ID);
        verify(orderConverter).fromModel(order);
        assertNotNull(result);
        assertEquals(ORDER_ID, result.getId());
    }


    @Test
    void getAllOrders() {
        when(orderRepository.findAll()).thenReturn(orders);
        when(orderConverter.fromModel(orders)).thenReturn(dtos);

        List<OrderDto>result = testInstance.getOrders();

        verify(orderRepository).findAll();
        verify(orderConverter).fromModel(orders);


    }


    // @Override
    //    public void save(OrderDto dto) {
    //        Order order = orderConverter.toModel(dto);
    //        orderRepository.save(order);
    //
    //    }
    @Test
    void saveTest(){
        //when(orderRepository.save(order)).thenReturn(order);
        when(orderConverter.toModel(dto)).thenReturn(order);

        testInstance.save(dto);

        verify(orderConverter).toModel(dto);
        verify(orderRepository).save(order);
    }

    //  @Override
    //    public void update(Integer id, OrderDto dto) {
    //        Order old = orderRepository.findById(id).orElseThrow();
    //        Order newOrder = orderConverter.toModel(old,dto);
    //        orderRepository.save(newOrder);

    @Test
    void updateTest(){
       // testInstance.getOrderById(ORDER_ID);
        when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.of(order));
        when(orderConverter.toModel(order,dto)).thenReturn(new Order());

        testInstance.update(ORDER_ID,dto);

        verify(orderRepository).findById(ORDER_ID);
        verify(orderConverter).toModel(order,dto);
        verify(orderRepository).save(any());

    }

//    @Override
//    public void delete(Integer id) {
//        orderRepository.deleteById(id);
//
//    }

    @Test
    void deletTest(){

        when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.of(order));



        testInstance.getOrderById(ORDER_ID);
        testInstance.delete(ORDER_ID);


        verify(orderRepository).findById(ORDER_ID);
        verify(orderRepository).deleteById(ORDER_ID);


        verify(orderRepository, times(1)).deleteById(ORDER_ID);


    }

}