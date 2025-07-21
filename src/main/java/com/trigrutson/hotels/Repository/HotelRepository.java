package com.trigrutson.hotels.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.trigrutson.hotels.Entity.*;
import com.trigrutson.hotels.Model.*;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query(
        """
            SELECT 
                h.id, 
                h.name, 
                h.description, 
                concat(a.houseNumber, ' ', a.street, ', ', a.city, ', ', a.postCode, ', ', a.country),
                c.phone
            
            FROM Hotel h 
            LEFT JOIN Address a ON h.address.id = a.id
            LEFT JOIN Contacts c ON h.contacts.id = c.id
        """
    )
    List<HotelBriefDTO> findAllHotelInfo();

    
    @Query(
        """
            SELECT new com.trigrutson.hotels.Model.HotelExtendedDTO(
                h.id, 
                h.name, 
                h.description, 
                h.brand,
                new com.trigrutson.hotels.Model.AddressDTO(a.houseNumber, a.street, a.city, a.postCode, a.country),
                new com.trigrutson.hotels.Model.ContactsDTO(c.phone, c.email)
            )
            FROM Hotel h 
            LEFT JOIN Address a ON h.address.id = a.id
            LEFT JOIN Contacts c ON h.contacts.id = c.id
            LEFT JOIN h.amenities am
            WHERE h.id = :id
            GROUP BY h.id
        """
    )
    Optional<HotelExtendedDTO> findHotelInfoById(long id);

    @Query(
        """
            SELECT amenity.amenityName
            FROM Amenity amenity
            LEFT JOIN amenity.hotels ha
            WHERE ha.id = :id
        """
    )
    List<String> findHotelAmienitiesById(long id);

    @Query(
        """
            SELECT
                h.brand, COUNT(h.brand)
            FROM Hotel h
            GROUP BY h.brand
        """
    )
    List<Object[]> findBrandHistogram();

    @Query(
        """
            SELECT a.city, COUNT(a.city)
            FROM Hotel h
            LEFT JOIN h.address a
            GROUP BY a.city
        """
    )
    List<Object[]> findCityHistogram();

    @Query(
        """
            SELECT a.country, COUNT(a.country)
            FROM Hotel h
            LEFT JOIN h.address a
            GROUP BY a.country
        """
    )
    List<Object[]> findCountryHistogram();
    
    @Query(
        """
            SELECT am.amenityName, COUNT(am.amenityName)
            FROM Hotel hotel
            LEFT JOIN hotel.amenities am
            GROUP BY am.amenityName
        """
    )
    List<Object[]> findAmenitiesHistogram();

    
    @Query(
        """
            SELECT new com.trigrutson.hotels.Model.HotelBriefDTO(
                h.id, 
                h.name, 
                h.description, 
                concat(a.houseNumber, ' ', a.street, ', ', a.city, ', ', a.postCode, ', ', a.country),
                c.phone)
            FROM Hotel h 
            LEFT JOIN Address a ON h.address.id = a.id
            LEFT JOIN Contacts c ON h.contacts.id = c.id
            LEFT JOIN h.amenities am
            WHERE (:name IS NULL OR LOWER(h.name) = LOWER(:name))
                AND (:brand IS NULL OR LOWER(h.brand) = LOWER(:brand))
                AND (:city IS NULL OR LOWER(a.city) = LOWER(:city))
                AND (:country IS NULL OR LOWER(a.country) = LOWER(:country))
                AND (:amenity IS NULL OR LOWER(am.amenityName) = LOWER(:amenity))
            GROUP BY h.id
            
        """
    )
    public List<HotelBriefDTO> findHotelByParameter(String name, String brand, String city, String country, String amenity);
}

