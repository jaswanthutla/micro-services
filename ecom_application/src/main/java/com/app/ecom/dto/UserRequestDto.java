package com.app.ecom.dto;

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
}
