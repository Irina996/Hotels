package com.trigrutson.hotels.Model;

import lombok.Data;

@Data
public class HotelBriefDTO {
    private final long id;
    private final String name;
    private final String description;
    private final String address;
    private final String phone;

}