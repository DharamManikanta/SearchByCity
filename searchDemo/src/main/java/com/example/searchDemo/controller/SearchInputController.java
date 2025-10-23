package com.example.searchDemo.controller;

import com.example.searchDemo.model.SearchInputEntity;
import com.example.searchDemo.service.SearchInputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cities")
@CrossOrigin(origins = "http://localhost:5173")
public class SearchInputController {

    private static final Logger log = LoggerFactory.getLogger(SearchInputController.class);
    @Autowired
    SearchInputService searchInputService;


    @GetMapping("/getAllCities")
    public ResponseEntity<List<SearchInputEntity>> getAllSearchInputs(){
        List<SearchInputEntity> searchInputEntityList = searchInputService.getAllSearchInput();
        System.out.println();
        log.info("---From controller---");
        log.info("City Name: {}", searchInputEntityList);
        return ResponseEntity.ok(searchInputEntityList);
    }

    @GetMapping("/suggest")
    public ResponseEntity<List<SearchInputEntity>> suggestCities(String keyword){
        log.info("Received search keyword: {}", keyword);
    List<SearchInputEntity> suggestions = searchInputService.searchCities(keyword);
    return ResponseEntity.ok(suggestions);
    }
}
