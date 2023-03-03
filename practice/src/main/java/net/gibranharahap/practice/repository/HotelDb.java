package net.gibranharahap.practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.gibranharahap.practice.model.HotelModel;

@Repository
public interface HotelDb extends JpaRepository<HotelModel, Long> {

    List<HotelModel> findAllByOrderByIdDesc();
}
