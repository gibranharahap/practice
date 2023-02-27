package net.gibranharahap.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.gibranharahap.practice.model.HotelModel;
import net.gibranharahap.practice.service.HotelService;

import org.springframework.ui.Model;

@Controller
public class HotelController {
    
    @Autowired
    private HotelService hotelService;

    @RequestMapping("/hotel/add")
    public String addHotel(
            @RequestParam(value = "idHotel", required = true) String idHotel,
            @RequestParam(value = "namaHotel", required = true) String namaHotel,
            @RequestParam(value = "alamat", required = true) String alamat,
            @RequestParam(value = "noTelepon", required = true) String noTelepon,
            Model model
    ){
        HotelModel hotel = new HotelModel(idHotel, namaHotel, alamat, noTelepon);

        hotelService.addHotel(hotel);

        model.addAttribute("idHotel", idHotel);
        
        return "add-hotel";
    }

    @RequestMapping("hotel/viewall")
    public String listHotel(Model model){

        List<HotelModel> listHotel = hotelService.getHotelList();

        model.addAttribute("listHotel", listHotel);

        return "viewall-hotel";
    }

    @RequestMapping("hotel/view")
    public String detailHotel(
            @RequestParam(value = "idHotel") String idHotel,
            Model model
    ){

        if (idHotel != null & hotelService.getHotelByIdHotel(idHotel) != null) {
            HotelModel hotel = hotelService.getHotelByIdHotel(idHotel);
            
            model.addAttribute("hotel", hotel);
           
            return "view-hotel";
        }

        return "error-hotel";
        
    }
    
    @RequestMapping("hotel/view/{idHotel}")
    public String detailHotelPV(
            @PathVariable String idHotel,
            Model model
    ){

        if (idHotel != null & hotelService.getHotelByIdHotel(idHotel) != null) {
            HotelModel hotel = hotelService.getHotelByIdHotel(idHotel);
            
            model.addAttribute("hotel", hotel);
            
            return "view-hotel";
        }

        System.out.println("anjing");
            
        return "error-hotel";
        
        
    }

    @RequestMapping("hotel/update/id-hotel/{idHotel}/no-telepon/{noTelepon}")
    public String updateNoTeleponHotel(
            @PathVariable String idHotel,
            @PathVariable String noTelepon,
            Model model
    ){
        if (idHotel != null & hotelService.getHotelByIdHotel(idHotel) != null) {
            hotelService.updateHotel(idHotel, noTelepon);
            
            model.addAttribute("idHotel", idHotel);
            
            return "update-hotel";
        }

        return "error-hotel";
    }

    @RequestMapping("hotel/delete/id-hotel/{idHotel}")
    public String deleteHotel(
            @PathVariable String idHotel,
            Model model
    ){

        if (idHotel != null & hotelService.getHotelByIdHotel(idHotel) != null) {
            hotelService.deleteHotel(idHotel);
            
            model.addAttribute("idHotel", idHotel);
            
            return "delete-hotel";
        }

        return "error-hotel";
    }

}
