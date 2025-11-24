package com.smartpark.places.repository;

import com.smartpark.places.domaine.entity.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

    List<ParkingSpot> findByZoneId(Long zoneId);
}
