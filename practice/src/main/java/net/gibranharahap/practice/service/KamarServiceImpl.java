package net.gibranharahap.practice.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gibranharahap.practice.model.KamarModel;
import net.gibranharahap.practice.repository.KamarDb;

@Service
@Transactional
public class KamarServiceImpl implements KamarService {

    @Autowired
    KamarDb kamarDb;

    @Override
    public void addKamar(KamarModel kamar) {
        kamarDb.save(kamar);
    }

    @Override
    public List<KamarModel> findAllKamarByIdHotel(Long idHotel) {
        return kamarDb.findAllByHotelId(idHotel);
    }

    @Override
    public KamarModel getKamarByNoKamar(Long noKamar) {
        try {
            return kamarDb.findById(noKamar).get();

        } catch (NoSuchElementException exception) {
            return null;
        }
    }

    @Override
    public KamarModel updateKamar(KamarModel kamar) {
        KamarModel kamarTarget = kamarDb.findById(kamar.getNoKamar()).get();

        try {
            kamarTarget.setNamaKamar(kamar.getNamaKamar());
            kamarTarget.setTipe(kamar.getTipe());
            kamarTarget.setKapasitasKamar(kamar.getKapasitasKamar());
            kamarDb.save(kamarTarget);
            return kamarTarget;
                       
        } catch (NullPointerException nullException) {
            return null;
        }
    }

    @Override
    public KamarModel deleteKamar(Long noKamar) {
        KamarModel kamarTarget = kamarDb.findById(noKamar).get();
        kamarDb.deleteById(noKamar);
        return kamarTarget;
    }
    
}
