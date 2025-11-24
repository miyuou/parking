package com.smartpark.places.controller;
import com.smartpark.places.domaine.dto.ZoneDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.smartpark.places.service.ZoneService;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @GetMapping
    public List<ZoneDto> getAll() {
        return zoneService.getAll();
    }

    @GetMapping("/{id}")
    public ZoneDto getById(@PathVariable Long id) {
        return zoneService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ZoneDto create(@Valid @RequestBody ZoneDto dto) {
        return zoneService.create(dto);
    }

    @PutMapping("/{id}")
    public ZoneDto update(@PathVariable Long id, @Valid @RequestBody ZoneDto dto) {
        return zoneService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        zoneService.delete(id);
    }
}
