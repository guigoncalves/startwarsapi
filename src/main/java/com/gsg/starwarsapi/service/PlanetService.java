package com.gsg.starwarsapi.service;

import com.gsg.starwarsapi.exception.ResourceNotFoundException;
import com.gsg.starwarsapi.model.Planet;
import com.gsg.starwarsapi.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private StarwarsApiService apiService;

    public Planet createPlanet(Planet planet) throws Exception {
        Integer apparitions = apiService.getApparitionsByName(planet.getName());
        planet.setApparitionsInMovies(apparitions);
        return planetRepository.save(planet);
    }

    public List<Planet> findAll() {
        return planetRepository.findAll();
    }

    public void deleteById(Long id) {
        Planet planet = planetRepository.findById(id).orElseThrow(
                () ->  new ResourceNotFoundException("Planet","id", id)
        );
        planetRepository.deleteById(id);
    }
}
