package net.gibranharahap.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.gibranharahap.practice.model.HotelModel;
import net.gibranharahap.practice.model.KamarModel;
import net.gibranharahap.practice.service.HotelService;
import net.gibranharahap.practice.service.KamarService;

import org.springframework.ui.Model;

@Controller
public class HotelController {
    
    // @Qualifier("hotelServiceImpl")
    @Autowired
    private HotelService hotelService;

    @Autowired
    private KamarService kamarService;

    @GetMapping
    private String home(){
        return "home";
    }

    @GetMapping("/hotel/add")
    public String addHotelFormPage(Model model){
        model.addAttribute("hotel", new HotelModel());
        return "form-add-hotel";
    }

    @PostMapping("/hotel/add")
    public String addHotelSubmit(
            @ModelAttribute HotelModel hotel,
            Model model
    ){
        hotelService.addHotel(hotel);

        model.addAttribute("idHotel", hotel.getId());
        
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
            @RequestParam(required = false) Long idHotel,
            Model model
    ){

        if (idHotel == null) {
            return "error-hotel";
        }

        else if (hotelService.getHotelByIdHotel(idHotel) != null) {
            HotelModel hotel = hotelService.getHotelByIdHotel(idHotel);
            List<KamarModel> listKamar = kamarService.findAllKamarByIdHotel(idHotel);            
            model.addAttribute("hotel", hotel);
            model.addAttribute("listKamar", listKamar);
           
            return "view-hotel";
        }

        return "error-hotel";
        
    }
    
    // @RequestMapping("hotel/view/{idHotel}")
    // public String detailHotelPV(
    //         @PathVariable String idHotel,
    //         Model model
    // ){

    //     if (idHotel != null && hotelService.getHotelByIdHotel(idHotel) != null) {
    //         HotelModel hotel = hotelService.getHotelByIdHotel(idHotel);
            
    //         model.addAttribute("hotel", hotel);
            
    //         return "view-hotel";
    //     }

    //     System.out.println("anjing");
            
    //     return "error-hotel";
        
        
    // }

    @GetMapping("hotel/change/{idHotel}")
    public String changeHotelFormPage(
            @PathVariable Long idHotel,
            Model model
    ){
        if (idHotel != null && hotelService.getHotelByIdHotel(idHotel) != null) {
            HotelModel hotel = hotelService.getHotelByIdHotel(idHotel);

            model.addAttribute("hotel", hotel);
            
            return "form-update-hotel";
        }

        return "error-hotel";
    }

    @PostMapping("hotel/change")
    public String changeHotelSubmit(
            @ModelAttribute HotelModel hotel,
            Model model
    ){
        hotelService.updateHotel(hotel);
        
        model.addAttribute("idHotel", hotel.getId());
        
        return "update-hotel";
    }


    @GetMapping("hotel/delete/{idHotel}")
    public String deleteHotel(
            @PathVariable Long idHotel,
            Model model
    ){

        if (idHotel == null) {
            return "error-hotel";
        }

        else if (hotelService.getHotelByIdHotel(idHotel) != null) {

            model.addAttribute("idHotel", idHotel); 

            if(hotelService.deleteHotel(idHotel) != null) {
                                 
                return "delete-hotel";
            }

            return "error-delete-hotel";
        }

        return "error-hotel";
    }

}
