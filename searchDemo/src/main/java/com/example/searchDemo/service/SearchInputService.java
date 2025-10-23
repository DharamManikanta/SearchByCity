package com.example.searchDemo.service;

import com.example.searchDemo.model.SearchInputEntity;

import java.util.List;

public interface SearchInputService {

//    fetching all the cities
    public List<SearchInputEntity> getAllSearchInput();

//    searching city names dynamically for search suggestions
    List<SearchInputEntity> searchCities(String keyword);
}
