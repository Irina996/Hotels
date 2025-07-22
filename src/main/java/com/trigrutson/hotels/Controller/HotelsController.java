package com.trigrutson.hotels.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;

import com.trigrutson.hotels.Entity.Hotel;
import com.trigrutson.hotels.Model.HotelBriefDTO;
import com.trigrutson.hotels.Model.HotelExtendedDTO;
import com.trigrutson.hotels.Service.HotelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/property-view")
class HotelsController {
    
    @Autowired
    private HotelService hotelService;

    @GetMapping("/hotels")
    public List<HotelBriefDTO> getHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/hotels/{id}")
    public Optional<HotelExtendedDTO> getHotelById(@PathVariable int id) {
        return hotelService.getHotelById(id);
    }

    @GetMapping("/search")
    public List<HotelBriefDTO> searchHotels(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String brand, 
        @RequestParam(required = false) String city,
        @RequestParam(required = false) String country,
        @RequestParam(required = false) String amenities) 
    {
        return hotelService.getHotelByParameter(name, brand, city, country, amenities);
    }
    
    @PostMapping("/hotels")
    public HotelBriefDTO postHotels(@RequestBody Hotel hotel) {
        return hotelService.createHotel(hotel);
    }

    @PostMapping("/hotels/{id}/amenities")
    public HotelBriefDTO postAmenities(@PathVariable Long id, @RequestBody List<String> amenities) {
        return hotelService.addAmenities(id, amenities);
    }
    
    @GetMapping("/histogram/{param}")
    public Map<String, Long> getHistogram(@PathVariable String param) {
        return hotelService.getHistogramByParameter(param);
    }
    
}
