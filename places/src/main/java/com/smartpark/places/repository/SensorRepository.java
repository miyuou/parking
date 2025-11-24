package com.smartpark.places.repository;

import com.smartpark.places.domaine.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
