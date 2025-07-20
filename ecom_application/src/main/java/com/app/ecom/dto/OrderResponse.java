package com.app.ecom.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.app.ecom.model.OrderStatus;

import lombok.Data;
@Data
public class OrderResponse {
     private Long id;
    private BigDecimal total;
    private OrderStatus status;
    private List<OrderItemDTO> items;
    LocalDateTime localDateTime;

    public OrderResponse(Long id, BigDecimal total, OrderStatus status, List<OrderItemDTO> items, LocalDateTime localDateTime) {
        this.id = id;
        this.total = total;
        this.status = status;
        this.items = items;
        this.localDateTime=localDateTime;
    }
   
    

}
