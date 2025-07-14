package com.app.ecom.model;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "user_table")
@Data
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double salary;
    private UserRole role= UserRole.USER; // Default role is USER
    private String email;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="address_id",referencedColumnName = "id")
    private Address address;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    public void setIsactive(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIsactive'");
    }
}
