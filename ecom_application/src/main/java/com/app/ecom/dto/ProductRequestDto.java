package com.app.ecom.dto;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductRequestDto 
{
    private String name;
    private String description;
    private BigDecimal price;
    private String category;  
    @Column(name = "image_url")  
    private String imageUrl;
    private int stockQuantity;
    
}
