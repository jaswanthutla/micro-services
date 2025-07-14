package com.app.ecom.dto;

import com.app.ecom.model.Address;

import lombok.Data;
@Data
public class UserResponseDto
{
    private Long id;
    private String name;
    private double salary;
    private String email;
    private String role; // Assuming role is a String representation of UserRole
    private Address address;
}
