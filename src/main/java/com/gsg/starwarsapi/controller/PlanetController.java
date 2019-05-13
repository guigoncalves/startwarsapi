package com.gsg.starwarsapi.controller;

import com.gsg.starwarsapi.exception.ResourceNotFoundException;
import com.gsg.starwarsapi.model.Planet;
import com.gsg.starwarsapi.model.SwapiPlanet;
import com.gsg.starwarsapi.payload.ApiResponse;
import com.gsg.starwarsapi.repository.PlanetRepository;
import com.gsg.starwarsapi.service.PlanetService;
import com.gsg.starwarsapi.service.StarwarsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private StarwarsApiService apiService;

    @PostMapping("/planets")
    public ResponseEntity createPlanet(@Valid @RequestBody Planet planetRequest) throws Exception {
        Planet planet = planetService.createPlanet(planetRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{planetId}")
                .buildAndExpand(planet.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Planet Created Successfully"));
    }

    @GetMapping("/swapi/planets")
    public List<SwapiPlanet> getSwapiPlanets() {
        return apiService.findAll();
    }

    @GetMapping("/planets")
    public List<Planet> getPlanets() {
        return planetRepository.findAll();
    }

    @GetMapping("/planets/name/{name}")
    public List<Planet> findByName(@Valid @PathVariable String name) {
        return planetRepository.findByName(name);
    }

    @GetMapping("/planets/{id}")
    public Planet findById(@Valid @PathVariable Long id) {
        return planetRepository.findById(id).orElseThrow(
                () ->  new ResourceNotFoundException("Planet","id", id)
        );
    }

    @DeleteMapping("/planets/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        planetService.deleteById(id);

        return ResponseEntity
                .ok()
                .body("Deleted Successfully");
    }
}
