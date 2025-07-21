package com.trigrutson.hotels.Model;

import java.time.LocalTime;
import java.util.Optional;

import lombok.Data;

@Data
public class ArrivalTimeDTO {
    private final LocalTime checkIn;
    private final Optional<LocalTime> checkOut;
}
