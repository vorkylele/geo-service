package com.example.geoservice.service;

import com.example.geoservice.data.CountryRepository;
import com.example.geoservice.data.entity.CountryEntity;
import com.example.geoservice.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAllCountries() {
        return countryRepository.findAll().stream()
                .map(entity -> new Country(entity.getCountryName(), entity.getCountryCode(), entity.getC()))
                .collect(Collectors.toList());
    }


    public Optional<Country> findByCountryCode(String countryCode) {
        return countryRepository.findByCountryCode(countryCode)
                .map(entity -> new Country(entity.getCountryName(), entity.getCountryCode(), entity.getC()));
    }

    public Country saveCountry(Country country) {
        CountryEntity entity = new CountryEntity();
        entity.setCountryName(country.countryName());
        entity.setCountryCode(country.countryCode());
        entity.setC(country.C());
        CountryEntity savedEntity = countryRepository.save(entity);
        return new Country(savedEntity.getCountryName(), savedEntity.getCountryCode(), savedEntity.getC());
    }

    public Country updateCountry(String countryCode, Country country) {
        CountryEntity entity = countryRepository.findByCountryCode(countryCode)
                .orElseThrow(() -> new RuntimeException("Country not found!"));  // Customize this exception
        entity.setCountryName(country.countryName());
        entity.setC(country.C());
        CountryEntity updatedEntity = countryRepository.save(entity);
        return new Country(updatedEntity.getCountryName(), updatedEntity.getCountryCode(), updatedEntity.getC());
    }
}
