package com.app.ecom.model;

import java.math.BigDecimal;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private String imageUrl;
    private int stockQuantity;
    private Boolean active = true;
    @CreationTimestamp
    private String createdAt;
    @UpdateTimestamp
    private String updatedAt;

    public boolean getActive() {
        return active;
    }

}
