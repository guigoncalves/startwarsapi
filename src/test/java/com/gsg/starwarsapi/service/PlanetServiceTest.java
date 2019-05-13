package com.gsg.starwarsapi.service;

import com.gsg.starwarsapi.controller.PlanetController;
import com.gsg.starwarsapi.controller.PlanetControllerTest;
import com.gsg.starwarsapi.model.Planet;
import com.gsg.starwarsapi.repository.PlanetRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetServiceTest {

    @InjectMocks
    private PlanetService planetService;

    @Mock
    StarwarsApiService apiServiceMock;

    @Mock
    PlanetRepository repositoryMock;

    @Test
    public void createPlanet_basic() throws Exception {
        Planet expected = PlanetControllerTest.createPlanet();
        when(apiServiceMock.getApparitionsByName(Mockito.anyString())).thenReturn(1);
        when(repositoryMock.save(Mockito.any())).thenReturn(expected);

        Planet persisted = planetService.createPlanet(expected);
        Assert.assertEquals(expected, persisted);
    }
}
