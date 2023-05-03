package com.debasish.bookmyshow.service;

import com.debasish.bookmyshow.model.City;
import com.debasish.bookmyshow.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City addCity(String name) {
        City newCity = new City();
        newCity.setName(name);
        return cityRepository.save(newCity);
    }
}
