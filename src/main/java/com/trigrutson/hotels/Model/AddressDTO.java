package com.trigrutson.hotels.Model;

import lombok.Data;

@Data
public class AddressDTO {
    private final long houseNumber;
    private final String street;
    private final String city;
    private final String postCode;
    private final String country;
}
