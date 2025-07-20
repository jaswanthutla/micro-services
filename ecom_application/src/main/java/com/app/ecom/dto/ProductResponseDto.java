package com.app.ecom.dto;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    @Column(name = "image_url")
    private String imageUrl;
    private Integer stockQuantity;
    private String createdAt;
    private String updatedAt;
    private boolean active;
    
    public void setActive(boolean active) {
        this.active = active;
    }

    
   
}
