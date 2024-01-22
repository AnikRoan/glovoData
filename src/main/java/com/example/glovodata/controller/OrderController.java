package com.example.glovodata.controller;

import com.example.glovodata.dto.OrderDto;

import com.example.glovodata.model.data.Order;
import com.example.glovodata.respons.ApiResponse;
import com.example.glovodata.servise.OrderServise;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/orders")
@RestController
@Slf4j
public class OrderController {
    private final OrderServise orderServise;

    @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException(Exception e) {
        log.error("An error occurred: "+e.getMessage(), e);

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
 public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Integer orderId) {


     OrderDto order = orderServise.getOrderById(orderId);
     if (order != null) {
         return ResponseEntity.ok(order);
     }
     log.info("Order with id {} not found", orderId);
     return (ResponseEntity<OrderDto>) ResponseEntity.notFound();
 }



    @PostMapping("/update/{id}")
       public ApiResponse<OrderDto> updateOrder(@PathVariable Integer id,@RequestBody OrderDto orderDto) {


        this.orderServise.update(id,orderDto);
        ApiResponse<OrderDto> updateOrder = new ApiResponse<>();
        updateOrder.setSuccess(true);
        updateOrder.setMessage("order updated successfully");
        updateOrder.setData(orderDto);

        log.info("Order updated successfully: {}",id);

        return updateOrder;

}

       @PostMapping("/create")
       public void createOrder(@RequestBody OrderDto orderDto) {
           orderServise.save(orderDto);
           log.info("Order created successfully: {}",orderDto);





       }

       @DeleteMapping("/{id}")
       public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        OrderDto orderDto = this.orderServise.getOrderById(id);

        if(orderDto!=null){
            this.orderServise.delete(id);

            log.info("Order with id {} deleted successfully", id);

            return ResponseEntity.ok("order delete");
        }else{

            log.info("Order with id {} not found ", id);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found"); // Возвращаем 404
        }


       }












}
