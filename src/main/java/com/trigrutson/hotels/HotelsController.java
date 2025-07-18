package com.trigrutson.hotels;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/property-view")
class HotelsController {
    
    @GetMapping("/hotels")
    public String getHotels() {
        // TODO: return list of hotels with information
        return "Hotels";
    }

    @GetMapping("/hotels/{id}")
    public String getHotelsById(@PathVariable int id) {
        //TODO: return all information about id hotel
        return "Hotel " + id;
    }

    @GetMapping("/search")
    public String searchHotels(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String brand, 
        @RequestParam(required = false) String city,
        @RequestParam(required = false) String country,
        @RequestParam(required = false) List<String> amenities) 
    {
        //TODO: return list of hotels with parameters
        return "Search";
    }
    
    @PostMapping("/hotels")
    public String postHotels(@RequestBody String entity) {
        //TODO: add hotel
        
        return entity;
    }

    @PostMapping("/hotels/{id}/amenities")
    public String postAmenities(@PathVariable int id, @RequestBody String entity) {
        //TODO: add amenities to id hotel
        
        return entity;
    }
    
    @GetMapping("/histogram/{param}")
    public String getHistogram(@PathVariable String param) {
        return new String();
    }
    
    
}
