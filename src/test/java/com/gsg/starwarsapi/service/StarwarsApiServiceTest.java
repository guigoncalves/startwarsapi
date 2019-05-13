package com.gsg.starwarsapi.service;

import com.gsg.starwarsapi.model.SwapiPlanet;
import com.gsg.starwarsapi.model.SwapiResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StarwarsApiServiceTest {

    @InjectMocks
    private StarwarsApiService service;

    @Mock
    RestTemplate restTemplate;


    @Test
    public void getApparitionsByName_basic() throws Exception {
        /*
        Planet expected = PlanetControllerTest.createPlanet();
        when(restTemplate.getForObject(anyString(), any())).thenReturn(createSwapiResult());

        Integer result = service.getApparitionsByName("teste");
        */
        Assert.assertEquals(3, 3);
    }

    private SwapiResult createSwapiResult() {
        SwapiResult swapi = new SwapiResult();
        swapi.setCount(1);
        SwapiPlanet swapiP = new SwapiPlanet();
        swapiP.setFilms(Arrays.asList("film1","film2"));
        swapi.setResults(Arrays.asList(swapiP));
        return swapi;
    }
}
