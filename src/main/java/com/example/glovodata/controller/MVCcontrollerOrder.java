//package com.example.glovodata.controller;
//
//
//import com.example.glovodata.dto.OrderDto;
//import com.example.glovodata.model.data.Order;
//import com.example.glovodata.servise.OrderServise;
//import com.example.glovodata.servise.data.OrderServisImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//
//@RequiredArgsConstructor
//@RequestMapping("/info")
//@Controller
//public class MVCcontrollerOrder {
//    private final OrderServisImpl orderServise;
//
//
//    @GetMapping
//    public String frontPafe(){
//        return "index";
//    }
//    @GetMapping("/list")
//    public String allOrders(Model model){
//        List<OrderDto> order = orderServise.getOrders();
//         model.addAttribute("orderList",order);
//
//        return "orders";
//
//    }
//
//}
