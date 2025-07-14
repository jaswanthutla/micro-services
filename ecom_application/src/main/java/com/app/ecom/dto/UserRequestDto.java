package com.app.ecom.dto;

import java.time.LocalDateTime;


import com.app.ecom.model.Address;
import lombok.Data;

@Data
public class UserRequestDto
{
    private Long id;
    private String name;
    private double salary;
    private String email;
    private Address address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
