package com.ayushsingh.hotel_service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "hotel")
public class Hotel {
    @Id
    @Column(name = "hotelId")
    private String id;
    @Column(name = "hotelName", nullable = false, unique = true)
    private String name;
    @Column(name = "location", nullable = false)
    private String location;
    private String about;
}
