package com.backend.educonsultancy_backend.controllers;

import com.backend.educonsultancy_backend.entities.UserOrder;
import com.backend.educonsultancy_backend.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserOrderController {
    @Autowired
    private UserOrderService service;

    @GetMapping("/")
    public String init(){
        return "index";
    }

    @PostMapping(value = "/create-order",produces = "application/json")
    @ResponseBody
    public ResponseEntity<UserOrder> createOrder(@RequestBody UserOrder userOrder) throws Exception {
        UserOrder createdOrder = service.createOrder(userOrder);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
}
