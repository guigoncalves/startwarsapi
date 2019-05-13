package com.gsg.starwarsapi.data;

import com.gsg.starwarsapi.controller.PlanetController;
import com.gsg.starwarsapi.model.Planet;
import com.gsg.starwarsapi.repository.PlanetRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PlanetRepositoryTest {

    @InjectMocks
    PlanetRepository planetRepository;

    @Mock
    RestTemplate restTemplate;

    @Mock
    RestTemplateBuilder restTemplateBuilder;

    @Test
    public void findByName_basic() {
        /*
        List<Planet> planets = planetRepository.findByName("teste");
        Assert.assertEquals(3, planets.size());
        */
    }

}
