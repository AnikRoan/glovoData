package com.example.glovodata.controller;

import com.example.glovodata.dto.OrderDto;

import com.example.glovodata.respons.ApiResponse;
import com.example.glovodata.servise.OrderServise;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/orders")
@RestController
public class OrderController {
    private final OrderServise orderServise;

    @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException(Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }


@GetMapping()
    public ApiResponse<List<OrderDto>>getAll(){
    ApiResponse<List<OrderDto>> response= new ApiResponse<>();
    List<OrderDto>orders = orderServise.getOrders();
    if(!CollectionUtils.isEmpty(orders)){
        response.setSuccess(true);
        response.setData(orders);

    }else{
        response.setSuccess(false);
        response.setMessage("orders not found");
    }
    return response;

}

 @GetMapping("/{id}")
    public ApiResponse<OrderDto> get(@PathVariable Integer id){
        ApiResponse<OrderDto>orderDtoApiResponse= new ApiResponse<>();
        OrderDto orderDto = orderServise.getOrderById(id);
        if(orderDto!=null){
            orderDtoApiResponse.setSuccess(true);
            orderDtoApiResponse.setData(orderDto);

        }else{
            orderDtoApiResponse.setSuccess(false);
            orderDtoApiResponse.setMessage("order not found");
        }
        return  orderDtoApiResponse;

 }


    @PostMapping("/update/{id}")
       public ApiResponse<OrderDto> updateOrder(@PathVariable Integer id,@RequestBody OrderDto orderDto) {
        this.orderServise.update(id,orderDto);
        ApiResponse<OrderDto> updateOrder = new ApiResponse<>();
        updateOrder.setSuccess(true);
        updateOrder.setMessage("order updated successfully");
        updateOrder.setData(orderDto);

        return updateOrder;

    }


       @PostMapping("/create")
       public ApiResponse<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        orderServise.save(orderDto);
        ApiResponse<OrderDto>orderDtoApiResponse=new ApiResponse<>();
        orderDtoApiResponse.setSuccess(true);
        orderDtoApiResponse.setData(orderDto);
        orderDtoApiResponse.setMessage("create order");

        return orderDtoApiResponse;

       }

       @DeleteMapping("/{id}")
       public ApiResponse<String> deleteOrder(@PathVariable Integer id) {
        OrderDto orderDto = this.orderServise.getOrderById(id);
        ApiResponse<String>response=new ApiResponse<>();
        if(orderDto!=null){
            this.orderServise.delete(id);
            response.setSuccess(true);
            response.setMessage("order deleted");
        }else{
            response.setSuccess(false);
            response.setMessage("order not found");
        }
        return response;

       }












}
