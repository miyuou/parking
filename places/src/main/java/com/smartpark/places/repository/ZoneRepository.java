package com.smartpark.places.repository;


import  com.smartpark.places.domaine.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
}

