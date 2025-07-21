package com.trigrutson.hotels.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trigrutson.hotels.Entity.Amenity;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    public Amenity findByAmenityName(String name);

    
}
