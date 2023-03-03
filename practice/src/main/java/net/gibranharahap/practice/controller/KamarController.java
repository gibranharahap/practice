package net.gibranharahap.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.gibranharahap.practice.model.KamarModel;
import net.gibranharahap.practice.service.HotelService;
import net.gibranharahap.practice.service.KamarService;

@Controller
public class KamarController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private KamarService kamarService;
    
    @GetMapping("/kamar/add/{idHotel}")
    public String addKamarFormPage(
            @PathVariable Long idHotel,
            Model model
    ){

        if (idHotel != null && hotelService.getHotelByIdHotel(idHotel) != null) {
            KamarModel kamar = new KamarModel();
            kamar.setHotel(hotelService.getHotelByIdHotel(idHotel));
            model.addAttribute("kamar", kamar);
            return "form-add-kamar";
        }

        return "error-hotel";
    }

    @PostMapping("/kamar/add")
    public String addKamarSubmit(
            @ModelAttribute KamarModel kamar,
            Model model
    ){
        kamarService.addKamar(kamar);

        model.addAttribute("kamar", kamar);
        
        return "add-kamar";
    }

    @GetMapping("/kamar/change/{noKamar}")
    public String changeKamarFormPage(
            @PathVariable Long noKamar,
            Model model
    ){

        if (noKamar != null && kamarService.getKamarByNoKamar(noKamar) != null) {

            // System.out.println(kamarService.getKamarByNoKamar(noKamar).getHotel().getNamaHotel());

            model.addAttribute("kamar", kamarService.getKamarByNoKamar(noKamar));
            
            return "form-update-kamar";
        }

        return "error-hotel";
    }

    @PostMapping("kamar/change")
    public String changeKamarSubmit(
            @ModelAttribute KamarModel kamar,
            Model model
    ){

        KamarModel kamarUpdated = kamarService.updateKamar(kamar);

        model.addAttribute("kamar", kamarUpdated);
        
        return "update-kamar";
    }

    @GetMapping("kamar/delete/{noKamar}")
    public String deleteKamar(
            @PathVariable Long noKamar,
            Model model
    ){

        if (noKamar != null && kamarService.getKamarByNoKamar(noKamar) != null) {
            model.addAttribute("kamar", kamarService.deleteKamar(noKamar));
            return "delete-kamar";
        }

        return "error-hotel";
    }


}
