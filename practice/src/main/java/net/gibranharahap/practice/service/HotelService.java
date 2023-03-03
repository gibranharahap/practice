package net.gibranharahap.practice.service;

import java.util.List;

import net.gibranharahap.practice.model.HotelModel;

public interface HotelService {
    
    void addHotel(HotelModel hotel);

    List<HotelModel> getHotelList();

    HotelModel getHotelByIdHotel(Long idHotel);

    HotelModel updateHotel(HotelModel hotel);

    HotelModel deleteHotel(Long idHotel);
}