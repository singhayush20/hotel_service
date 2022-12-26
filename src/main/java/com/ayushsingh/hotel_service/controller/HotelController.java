package com.ayushsingh.hotel_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ayushsingh.hotel_service.constants.AppConstants;
import com.ayushsingh.hotel_service.dto.HotelDto;
import com.ayushsingh.hotel_service.exceptions.ApiResponse;
import com.ayushsingh.hotel_service.exceptions.SuccessResponse;
import com.ayushsingh.hotel_service.service.HotelService;
@RestController
@RequestMapping("/microservices/hotel")
public class HotelController {
    @Autowired
    HotelService hotelService;

    @GetMapping("/get-all-hotels")
    public  ResponseEntity<?> getAllHotels(){
        List<HotelDto> hotels=this.hotelService.getAllHotels();
        SuccessResponse<List<HotelDto>> successResponse=new SuccessResponse<>(AppConstants.SUCCESS_CODE,AppConstants.SUCCESS_MESSAGE,hotels);
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }

    @GetMapping("/get-hotel-by-id")
    public ResponseEntity<?> getHotelById(@RequestParam(name="hotelId") String hotelId){
        HotelDto hotel=this.hotelService.getHotelById(hotelId);
        SuccessResponse<HotelDto> successResponse=new SuccessResponse<HotelDto>(AppConstants.SUCCESS_CODE,AppConstants.SUCCESS_MESSAGE, hotel);
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }

    @PutMapping("/update-hotel")
    public ResponseEntity<?> updateHotel(@RequestBody HotelDto hotelDto){
        HotelDto hotel=this.hotelService.updateHotel(hotelDto);
        SuccessResponse<HotelDto> successResponse=new SuccessResponse<HotelDto>(AppConstants.SUCCESS_CODE,AppConstants.SUCCESS_MESSAGE, hotel);
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }

    @DeleteMapping("/delete-hotel")
    public ResponseEntity<?> deleteHotel(@RequestParam(name="hotelId") String hotelId){
        this.hotelService.deleteHotel(hotelId);
        ApiResponse apiResponse=new ApiResponse(AppConstants.SUCCESS_CODE, "Hotel deleted successfully", AppConstants.SUCCESS_MESSAGE);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/save-hotel")
    public ResponseEntity<?> createHotel(@RequestBody HotelDto hotelDto){
        HotelDto hotel=this.hotelService.saveHotel(hotelDto);
        SuccessResponse<HotelDto> successResponse=new SuccessResponse<HotelDto>(AppConstants.SUCCESS_CODE,AppConstants.SUCCESS_MESSAGE, hotel);
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }
}
