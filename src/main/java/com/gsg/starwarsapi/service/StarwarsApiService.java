package com.gsg.starwarsapi.service;

import com.gsg.starwarsapi.exception.ResourceNotFoundException;
import com.gsg.starwarsapi.model.Planet;
import com.gsg.starwarsapi.model.SwapiPlanet;
import com.gsg.starwarsapi.model.SwapiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StarwarsApiService {

    private static final Logger log = LoggerFactory.getLogger(StarwarsApiService.class);

    private final String API_URL = "https://swapi.co/api/planets/";

    @Autowired
    RestTemplate restTemplate;

    public Integer getApparitionsByName(String planetName) throws Exception, RestClientException {

        String finalUrl = API_URL + "?search=" + planetName;

        log.info("METHOD GET " + finalUrl);

        SwapiResult apiResult = restTemplate.getForObject(finalUrl, SwapiResult.class);

        //Check if the exact name matches
        for (SwapiPlanet swapiPlanet: apiResult.getResults()) {
            if (swapiPlanet.getName().equals(planetName)) {
                return swapiPlanet.getApparitionsCount();
            }
        }

        throw new ResourceNotFoundException("Planet","name", planetName);
    }

    public List<SwapiPlanet> findAll() {

        String finalUrl = API_URL;

        log.info("METHOD GET " + finalUrl);

        SwapiResult apiResult = restTemplate.getForObject(finalUrl, SwapiResult.class);

        return apiResult.getResults();
    }
}
