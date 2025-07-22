package com.trigrutson.hotels.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.trigrutson.hotels.Entity.Amenity;
import com.trigrutson.hotels.Entity.Hotel;
import com.trigrutson.hotels.Model.HotelBriefDTO;
import com.trigrutson.hotels.Model.HotelExtendedDTO;
import com.trigrutson.hotels.Repository.AmenityRepository;
import com.trigrutson.hotels.Repository.HotelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private AmenityRepository amenityRepository;

    public List<HotelBriefDTO> getAllHotels() {
        return hotelRepository.findAllHotelInfo();
    }

    public HotelBriefDTO createHotel(final Hotel hotel) {
        var result = hotelRepository.save(hotel);
        var info = new HotelBriefDTO(result.getId(), result.getName(), result.getDescription(), 
            result.getAddress().getHouseNumber() + " " + result.getAddress().getStreet() + ", " 
            + result.getAddress().getCity() + ", " +  result.getAddress().getPostCode() + ", " 
            + result.getAddress().getCountry(), 
            result.getContacts().getPhone());
        return info;
    }

    public HotelBriefDTO addAmenities(long id, List<String> amenityNames) {
        Hotel hotel = hotelRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Отель не найден"));
        for (String name : amenityNames) {
            Amenity am = amenityRepository.findByAmenityName(name);
            if (am == null) {
                am = new Amenity();
                am.setAmenityName(name);
            }
            hotel.getAmenities().add(am);
        }
        return createHotel(hotel);
    }

    public Optional<HotelExtendedDTO> getHotelById(long id) {
        var h = hotelRepository.findHotelInfoById(id);
        h.ifPresent(x -> x.setAmenities(hotelRepository.findHotelAmienitiesById(id)));
        return h;
    }

    private Map<String, Long> convertToMap(List<Object[]> requestList) {
        Map<String, Long> resultMap = new HashMap<>();
        for (Object[] row : requestList) {
            resultMap.put((String)row[0], (Long)row[1]);
        }
        return resultMap;
    }

    public Map<String, Long> getHistogramByParameter(String parameter) {
        switch (parameter) {
            case "brand":
                return convertToMap(hotelRepository.findBrandHistogram());
            case "city":
                return convertToMap(hotelRepository.findCityHistogram());
            case "country":
                return convertToMap(hotelRepository.findCountryHistogram());
            case "amenities":
                return convertToMap(hotelRepository.findAmenitiesHistogram());
            default:
                throw new IllegalArgumentException("Неподдерживаемый параметр: " + parameter);
        }
    }

    public List<HotelBriefDTO> getHotelByParameter(String name, 
        String brand, String city, String country, String amenities) {
        return hotelRepository.findHotelByParameter(name, brand, city, country, amenities);
    }
}
