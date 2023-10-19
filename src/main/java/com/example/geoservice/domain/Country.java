package com.example.geoservice.domain;

import java.util.List;

public record Country(String countryName, String countryCode, List<List<List<List<Double>>>> C) {
}
