package com.dev.moviebookingsystem.mbs.controller;

import com.dev.moviebookingsystem.mbs.dto.CityDto;
import com.dev.moviebookingsystem.mbs.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping("/city")
    public ResponseEntity<CityDto> addCity(@RequestBody CityDto city) {
        return ResponseEntity.ok(cityService.createCity(city));
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<CityDto> getCityById(@PathVariable("id") int cityId) {
        return ResponseEntity.ok(cityService.getCityById(cityId));
    }

    @GetMapping("/city")
    public ResponseEntity<List<CityDto>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable("id") int cityId) {
        cityService.deleteCity(cityId);
        return ResponseEntity.ok("City deleted successfully");
    }

}
