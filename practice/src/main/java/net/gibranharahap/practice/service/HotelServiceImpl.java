package net.gibranharahap.practice.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gibranharahap.practice.model.HotelModel;
import net.gibranharahap.practice.repository.HotelDb;

@Service
@Transactional
public class HotelServiceImpl implements HotelService{

    @Autowired
    HotelDb hotelDb;

    @Override
    public void addHotel(HotelModel hotel) {
        hotelDb.save(hotel);
    }

    @Override
    public List<HotelModel> getHotelList() {
        return hotelDb.findAllByOrderByIdDesc();
    }

    @Override
    public HotelModel getHotelByIdHotel(Long idHotel) {

        try {
            return hotelDb.findById(idHotel).get();

        } catch (NoSuchElementException exception) {
            return null;
        }
        
    }

    @Override
    public HotelModel updateHotel(HotelModel hotel) {
        HotelModel hotelTarget = hotelDb.findById(hotel.getId()).get();

        try {
            hotelTarget.setNamaHotel(hotel.getNamaHotel());
            hotelTarget.setAlamat(hotel.getAlamat());
            hotelTarget.setNomorTelepon(hotel.getNomorTelepon());
            hotelDb.save(hotelTarget);
            return hotelTarget;
                       
        } catch (NullPointerException nullException) {
            return null;
        }
    }

    @Override
    public HotelModel deleteHotel(Long idHotel) {
        HotelModel hotelTarget = hotelDb.findById(idHotel).get();
        if (hotelTarget.getListKamar().isEmpty()) {
            hotelDb.deleteById(idHotel);
            return hotelTarget;
        
        } else {
            return null;
        }
    }

}
