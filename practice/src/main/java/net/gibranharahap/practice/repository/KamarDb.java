package net.gibranharahap.practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.gibranharahap.practice.model.KamarModel;

@Repository
public interface KamarDb extends JpaRepository<KamarModel, Long> {
    
    List<KamarModel> findAllByHotelId(Long hotelId);
}
