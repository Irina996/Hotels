package com.trigrutson.hotels.Entity;

import java.util.Set;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "hotel")
@DynamicInsert
@DynamicUpdate
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long  id;

    @Column(name = "hotel_name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "brand", nullable = false)
    private String brand;

    @OneToOne
    @JoinColumn(name = "address_id", unique = true, nullable = false)
    private Address address;

    @OneToOne
    @JoinColumn(name = "contacts_id", unique = true, nullable = false)
    private Contacts contacts;

    @OneToOne
    @JoinColumn(name = "arrival_time_id", unique = true, nullable = false)
    private ArrivalTime arrivalTime;

    @ManyToMany
    @JoinTable(name = "hotels_amenities",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private Set<Amenity> amenities;

}
