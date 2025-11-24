package com.smartpark.places.controller;

import com.smartpark.places.domaine.dto.AvailabilityDto;
import com.smartpark.places.service.AvailabilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availability")
@RequiredArgsConstructor
public class AvailabilityController {

    private final AvailabilityService service;

    @GetMapping
    public List<AvailabilityDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/spot/{spotId}")
    public List<AvailabilityDto> getBySpot(@PathVariable Long spotId) {
        return service.getBySpot(spotId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AvailabilityDto create(@Valid @RequestBody AvailabilityDto dto) {
        return service.create(dto);
    }
}
