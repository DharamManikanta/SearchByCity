package com.example.searchDemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class SearchInputEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cityId;

    private String cityName;

    // Default constructor required by JPA
    public SearchInputEntity() {
    }

    // Getters and Setters
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "SearchInputEntity{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
