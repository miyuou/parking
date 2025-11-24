package com.smartpark.places.controller;

import com.smartpark.places.domaine.dto.SensorDto;
import com.smartpark.places.service.SensorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService service;

    @GetMapping
    public List<SensorDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SensorDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SensorDto create(@Valid @RequestBody SensorDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public SensorDto update(@PathVariable Long id,
                            @Valid @RequestBody SensorDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
