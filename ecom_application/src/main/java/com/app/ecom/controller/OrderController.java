package com.app.ecom.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecom.dto.OrderResponse;
import com.app.ecom.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping("")
public ResponseEntity<?> createOrder(@RequestHeader("x_user_id") String id) {
    Optional<OrderResponse> orderResponse = orderService.create(id);
    
    if (orderResponse.isPresent()) {
        return ResponseEntity.ok(orderResponse.get());
    } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Cart is empty or user not found");
    }
}

    
}
