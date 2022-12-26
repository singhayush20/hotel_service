package com.ayushsingh.hotel_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ayushsingh.hotel_service.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,String>{
    
}