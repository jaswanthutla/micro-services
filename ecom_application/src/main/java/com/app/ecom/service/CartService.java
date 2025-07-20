package com.app.ecom.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.app.ecom.dto.CartItemRequestDTO;
import com.app.ecom.model.CartItem;
import com.app.ecom.model.Product;
import com.app.ecom.model.User;
import com.app.ecom.repository.CartItemRepository;
import com.app.ecom.repository.ProductRepository;
import com.app.ecom.repository.UserRepository;


@Service
public class CartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    public CartService(UserRepository userRepository,ProductRepository productRepository,CartItemRepository cartItemRepository)
    {
        this.cartItemRepository=cartItemRepository;
        this.productRepository=productRepository;
        this.userRepository=userRepository;

    }
    public boolean addToCart(String id, CartItemRequestDTO cartItemRequestDTO) {
        // find the product
           Optional<Product> product=  productRepository.findById(cartItemRequestDTO.getProductId());
           if(product.isEmpty())
           {
                return false;
           }
           Product product1=product.get();
           if(product1.getStockQuantity()<cartItemRequestDTO.getQuantity())
                return false;
         // 2. find the user
          Optional<User> userOpt= userRepository.findById(Long.valueOf(id));
          if(userOpt.isEmpty())
           return false;
          User user=userOpt.get();
           //check the cart
          CartItem existingCartItem=cartItemRepository.findByUserAndProduct(user,product1);
          if(existingCartItem!=null)
          {
                existingCartItem.setQuantity(cartItemRequestDTO.getQuantity()+existingCartItem.getQuantity());
                existingCartItem.setTotalPrice(product1.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
                cartItemRepository.save(existingCartItem);

          }
          else{
            CartItem cartItem=new CartItem();
            cartItem.setProduct(product1);
            cartItem.setQuantity(product1.getStockQuantity());
            cartItem.setUser(user);
            cartItem.setTotalPrice(product1.getPrice().multiply(BigDecimal.valueOf(cartItemRequestDTO.getQuantity())));
            cartItemRepository.save(cartItem);
          }
          return true;
    }
    public boolean removeItem(String id, Long productId) {
       Optional<Product> optProduct= productRepository.findById(productId);
       if(optProduct.isEmpty())
            return false;
        Product product=optProduct.get();
        Optional<User> optUser=userRepository.findById(Long.valueOf(id));
        if(optUser.isEmpty())
            return false;
        User user=optUser.get();
        CartItem cartItem=cartItemRepository.findByUserAndProduct(user, product);
        if(cartItem!=null)
        {
            cartItemRepository.deleteById(cartItem.getId());
            return true;
        }
        else{
            return false;
        }

    }
    public List<CartItem> getCarts(String id) {
       Optional<User> optUser= userRepository.findById(Long.parseLong(id));
       if (optUser.isEmpty()) {
         return null;
        }
        User user=optUser.get();
        return cartItemRepository.findByUser(user);
    }
    public User  clearCart(String id) {
        Optional<User> optUser=userRepository.findById(Long.parseLong(id));
        if(optUser.isEmpty())
            return cartItemRepository.deleteByUser(optUser.get());
        else 
            return null;
    }
        
        
    // }
    
}
