package com.smartpark.places.repository;

import com.smartpark.places.domaine.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    List<Availability> findByParkingSpotId(Long spotId);
}
