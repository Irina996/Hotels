package com.trigrutson.hotels.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trigrutson.hotels.Entity.Amenity;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    public Amenity findByAmenityName(String name);

    @Query("SELECT a.amenityName FROM Amenity a JOIN a.hotels h WHERE h.id = :hotelId")
    public List<String> findAmenityNamesByHotelId(@Param("hotelId") Long hotelId);
}
