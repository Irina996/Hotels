package com.trigrutson.hotels.Model;

import java.util.List;

import lombok.Data;

@Data
public class HotelExtendedDTO {
    private final long id;
    private final String name;
    private final String description;
    private final String brand;
    private final AddressDTO address;
    private final ContactsDTO contacts;
    private List<String> amenities;
}