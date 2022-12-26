package com.ayushsingh.hotel_service.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayushsingh.hotel_service.dto.HotelDto;
import com.ayushsingh.hotel_service.entities.Hotel;
import com.ayushsingh.hotel_service.exceptions.ResourceNotFoundException;
import com.ayushsingh.hotel_service.repository.HotelRepository;
import com.ayushsingh.hotel_service.service.HotelService;
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public HotelDto saveHotel(HotelDto hotelDto) {
        hotelDto.setId(UUID.randomUUID().toString());
        Hotel hotel=this.hotelRepository.save(this.hotelDtotoHotel(hotelDto));
        return this.hotelToHotelDto(hotel);
    }

    @Override
    public HotelDto updateHotel(HotelDto hotelDto) {
        Hotel hotel=this.hotelRepository.findById(hotelDto.getId()).get();
        if(hotel==null){
            throw new ResourceNotFoundException("Hotel", "Hotel Id", hotelDto.getId());
        }
        hotel.setAbout(hotelDto.getAbout());
        hotel.setLocation(hotelDto.getLocation());
        hotel.setName(hotelDto.getName());
        hotel.setId(hotelDto.getId());
        hotel=this.hotelRepository.save(hotel);
        return this.hotelToHotelDto(hotel);
    }

    @Override
    public void deleteHotel(String hotelId) {
        Hotel hotel=this.hotelRepository.findById(hotelId).get();
        if(hotel==null){
            throw new ResourceNotFoundException("Hotel", "Hotel Id", hotelId);
        }
        else{
            this.hotelRepository.delete(hotel);
        }
        
    }

    @Override
    public HotelDto getHotelById(String hotelId) {
        Hotel hotel=this.hotelRepository.findById(hotelId).get();
        if(hotel!=null){
            return this.hotelToHotelDto(hotel);
        }
        else {
            throw new ResourceNotFoundException("Hotel", "Hotel id", hotelId);
        }
    }

    @Override
    public List<HotelDto> getAllHotels() {
        List<Hotel> hotels=this.hotelRepository.findAll();
        List<HotelDto> hotelDtos=new ArrayList<>();
        for(Hotel hotel: hotels){
            hotelDtos.add(this.hotelToHotelDto(hotel));
        }
        return hotelDtos;
    }

    public Hotel hotelDtotoHotel(HotelDto hotelDto){
        return this.modelMapper.map(hotelDto, Hotel.class);
    }
    public HotelDto hotelToHotelDto(Hotel hotel){
        return this.modelMapper.map(hotel,HotelDto.class);
    }
}
