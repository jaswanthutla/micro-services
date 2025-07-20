package com.app.ecom.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.app.ecom.model.Address;

import lombok.Data;
@Data
public class UserResponseDto
{
    private Long id;
    private String name;
    private Double salary;
    private String email;
    private String role; // Assuming role is a String representation of UserRole
    private AddressDto address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
