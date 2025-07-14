package com.app.ecom.dto;

import lombok.Data;

@Data
public class AddressDto
{
    private String street;
    private String village;
    private String state;
    private String zipcode;
    private String country;
}
