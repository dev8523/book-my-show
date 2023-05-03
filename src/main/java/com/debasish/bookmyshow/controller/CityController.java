package com.debasish.bookmyshow.controller;

import com.debasish.bookmyshow.model.City;
import com.debasish.bookmyshow.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CityController {
    private final CityService cityService;

    @Autowired // dependency injection
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    public City addCity(String name) {
        return cityService.addCity(name);
    }
}
