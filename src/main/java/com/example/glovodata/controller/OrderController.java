package com.example.glovodata.controller;

import com.example.glovodata.dto.OrderDto;
import com.example.glovodata.respons.ApiResponse;
import com.example.glovodata.servise.OrderServise;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/orders")
@RestController
public class OrderController {
    private final OrderServise orderServise;


@GetMapping()
    public ApiResponse<List<OrderDto>>getOrders(){
    ApiResponse<List<OrderDto>> response= new ApiResponse<>();
    List<OrderDto>orders = orderServise.getOrders();
    if(!CollectionUtils.isEmpty(orders)){
        response.setSuccess(true);
        response.setData(orders);

    }
    return response;

}

 @GetMapping("/{id}")
    public
    //
    //    @GetMapping("/{orderId}")
    //    public ResponseEntity<OrderDto> getOrderById(@PathVariable("orderId") Integer orderId) {
    //        OrderDto order = orderService.getOrderById(orderId);
    //        if (order != null) {
    //            return ResponseEntity.ok(order);
    //        }
    //        return (ResponseEntity<OrderDto>) ResponseEntity.notFound();
    //    }
    //
    //    @CrossOrigin("*")
    //    @PostMapping()
    //    @ResponseStatus(HttpStatus.CREATED)
    //    public void createOrder(@RequestBody OrderDto orderDto) {
    //        orderService.save(orderDto);
    //    }
    //
    //    @PutMapping("/{orderId}")
    //    @ResponseStatus(HttpStatus.NO_CONTENT)
    //    public void updateOrderById(@PathVariable("orderId") Integer orderId, @RequestBody OrderDto orderDto) {
    //        orderService.update(orderId, orderDto);
    //    }
    //
    //    @DeleteMapping("/{orderId}")
    //    @ResponseStatus(HttpStatus.NO_CONTENT)
    //    public void deleteOrderyId(@PathVariable("orderId") Integer orderId) {
    //        orderService.delete(orderId);
    //    }








}
