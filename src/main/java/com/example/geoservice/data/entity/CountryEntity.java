package com.example.geoservice.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "countries")
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "countryName", "countryCode"})
@ToString
public class CountryEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "country_name", unique = true, nullable = false)
    private String countryName;

    @Column(name = "country_code", unique = true, nullable = false, length = 2)
    private String countryCode;

    @Column(name = "C", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<List<List<List<Double>>>> C;
}