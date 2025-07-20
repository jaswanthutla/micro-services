package com.app.ecom.controller;

import java.net.http.HttpRequest;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecom.dto.CartItemRequestDTO;
import com.app.ecom.model.CartItem;
import com.app.ecom.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PostMapping()
    public ResponseEntity<String> addItemToCart(@RequestHeader("x_user_id") String id,@RequestBody CartItemRequestDTO cartItemRequestDTO) {

        boolean value=cartService.addToCart(id,cartItemRequestDTO);
        if(value)
        {
            return new ResponseEntity<>("Created",HttpStatus.CREATED);

        }
        else{
            return new ResponseEntity<>("product out of stocke or user not found or product not found",HttpStatus.NOT_FOUND);
        }
            
    }
    @DeleteMapping()
    public ResponseEntity<?> removeItemFromCart(@RequestHeader("x_user_id") String id, @RequestParam Long productId){
         boolean value=cartService.removeItem(id,productId);
         if(value)
         {
            return ResponseEntity.ok("deleted");
         }
         else{
            return ResponseEntity.notFound().build();
         }

    }
    @GetMapping("/getById")
    public ResponseEntity<?> getCart(@RequestHeader("x_user_id") String id)
    {
        List<CartItem> cartItems=cartService.getCarts(id);
        if(cartItems==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartItems);
    }
    @GetMapping("/ping")
    public String ping() {
        return "Ping success";
    }
    
}
