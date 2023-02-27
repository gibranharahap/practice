package net.gibranharahap.practice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.gibranharahap.practice.model.HotelModel;

@Service
public class HotelServiceImpl implements HotelService{

    private List<HotelModel> listHotel;

    public HotelServiceImpl() {
        listHotel = new ArrayList<>();
    }

    @Override
    public void addHotel(HotelModel hotel) {
        listHotel.add(hotel);
    }

    @Override
    public List<HotelModel> getHotelList() {
        return listHotel;
    }

    @Override
    public HotelModel getHotelByIdHotel(String idHotel) {
        for (HotelModel hotelModel : listHotel) {
            if (hotelModel.getIdHotel().equals(idHotel)) {
                return hotelModel;
            }
        }
        return null;
    }

    @Override
    public void updateHotel(String idHotel, String noTelepon) {
        HotelModel hotelTarget = getHotelByIdHotel(idHotel);
        hotelTarget.setNoTelepon(noTelepon);
    }

    @Override
    public void deleteHotel(String idHotel) {
        listHotel.remove(getHotelByIdHotel(idHotel));
    }
    
}
