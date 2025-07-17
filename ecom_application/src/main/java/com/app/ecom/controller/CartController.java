package com.app.ecom.controller;

import java.net.http.HttpRequest;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecom.dto.CartItemRequestDTO;
import com.app.ecom.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    public ResponseEntity<?> addItemToCart(@RequestHeader("x-user_id") String id,@RequestBody CartItemRequestDTO cartItemRequestDTO) {
        // Logic to add item to cart
        
        return new ResponseEntity(cartService.addToCart(id,cartItemRequestDTO),HttpStatus.CREATED);

        
    }
    
}
