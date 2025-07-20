package com.app.ecom.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.ecom.dto.OrderItemDTO;
import com.app.ecom.dto.OrderResponse;
import com.app.ecom.model.CartItem;
import com.app.ecom.model.Order;
import com.app.ecom.model.OrderItem;
import com.app.ecom.model.OrderStatus;
import com.app.ecom.model.User;
import com.app.ecom.repository.OrderRepository;
import com.app.ecom.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
    private CartService cartService;
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    public OrderService(CartService cartService,UserRepository userRepository,OrderRepository orderRepository)
    {
        this.cartService=cartService;
        this.userRepository=userRepository;
        this.orderRepository=orderRepository;
    }

    public Optional<OrderResponse> create(String id) {

        //validate the cart items
       List<CartItem> cartItems= cartService.getCarts(id);
       if(cartItems.isEmpty())
       {
            return Optional.empty();
       }
       //valide the user
       Optional<User> optUser= userRepository.findById(Long.parseLong(id));
       if(optUser.isEmpty())
            return Optional.empty(); 
            
        User user=optUser.get();
       //find the total amount
       BigDecimal totalAmount=cartItems.stream().map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        
       //create order
       Order order=new Order();
       order.setTotalAmount(totalAmount);
       order.setStatus(OrderStatus.CONFIRMED);
       order.setUser(user);
       List<OrderItem> orderItems=cartItems.stream().map(item->
                    new OrderItem(
                        null,
                        item.getProduct(),
                        item.getQuantity(),
                        item.getTotalPrice(),
                        order
                    )).toList();  
        order.setOrderItems(orderItems);
       Order savedOrder=orderRepository.save(order);
       log.info("saved order is{} :",savedOrder);

       //clear the cart
       User user1=cartService.clearCart(id);
       log.info("Cart is cleared for user{} :",user1);

       return Optional.of(mapToOrderResponse(savedOrder));
       
    }

    private OrderResponse mapToOrderResponse(Order savedOrder) {

        return new OrderResponse(
            savedOrder.getId(),
            savedOrder.getTotalAmount(),
            savedOrder.getStatus(),
            savedOrder.getOrderItems().stream().map(item->{
                OrderItemDTO orderItemDTO=new OrderItemDTO();
                orderItemDTO.setId(item.getId());
                orderItemDTO.setPrice(item.getPrice());
                orderItemDTO.setProductId(item.getProduct().getId());
                orderItemDTO.setQuantity(item.getQuantity());
                return orderItemDTO;
            }).toList(),
            savedOrder.getCreatedAt()   
        );
        
    }
    
}
