package com.example.geoservice.controller;

import com.example.geoservice.domain.Country;
import com.example.geoservice.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class GeoController {

    private final CountryService countryService;

    @Autowired
    public GeoController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.findAllCountries();
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{countryCode}")
    public ResponseEntity<Country> getCountry(@PathVariable String countryCode) {
        return countryService.findByCountryCode(countryCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {
        Country savedCountry = countryService.saveCountry(country);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCountry);
    }

    @PatchMapping("/{countryCode}")
    public ResponseEntity<Country> updateCountry(@PathVariable String countryCode, @RequestBody Country country) {
        try {
            Country updatedCountry = countryService.updateCountry(countryCode, country);
            return ResponseEntity.ok(updatedCountry);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

