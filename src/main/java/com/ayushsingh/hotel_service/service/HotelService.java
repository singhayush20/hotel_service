package com.ayushsingh.hotel_service.service;


import java.util.List;

import com.ayushsingh.hotel_service.dto.HotelDto;

public interface HotelService {
    public HotelDto saveHotel(HotelDto hotelDto);
    public HotelDto updateHotel(HotelDto hotelDto);
    public void deleteHotel(String hotelId);
    public HotelDto getHotelById(String hotelId);
    public List<HotelDto> getAllHotels();
}
