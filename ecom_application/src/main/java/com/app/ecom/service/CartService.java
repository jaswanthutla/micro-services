package com.app.ecom.service;

import org.springframework.stereotype.Service;

import com.app.ecom.dto.CartItemRequestDTO;
import com.app.ecom.repository.CartItemRepository;
import com.app.ecom.repository.ProductRepository;
import com.app.ecom.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    // public Object addToCart(String id,CartItemRequestDTO cartItemRequestDTO)
    // {
        
        
    // }
    
}
