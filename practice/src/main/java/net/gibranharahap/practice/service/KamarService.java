package net.gibranharahap.practice.service;

import java.util.List;

import net.gibranharahap.practice.model.KamarModel;

public interface KamarService {

    void addKamar(KamarModel kamar);
    
    List<KamarModel> findAllKamarByIdHotel(Long idHotel);

    KamarModel getKamarByNoKamar(Long noKamar);

    KamarModel updateKamar(KamarModel kamar);

    KamarModel deleteKamar(Long noKamar);
}
