package com.gsg.starwarsapi.controller;

import com.gsg.starwarsapi.model.Planet;
import com.gsg.starwarsapi.repository.PlanetRepository;
import com.gsg.starwarsapi.service.PlanetService;
import com.gsg.starwarsapi.service.StarwarsApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PlanetController.class)
public class PlanetControllerTest {

    @MockBean
    PlanetService serviceMock;

    @MockBean
    PlanetRepository repositoryMock;

    @MockBean
    StarwarsApiService apiServiceMock;

    @MockBean
    RestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createPlanet_basic() throws Exception {

        when(serviceMock.createPlanet(any())).thenReturn(
                createPlanet());

        RequestBuilder request = MockMvcRequestBuilders
                .post("/planets")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().toString("Planet Created Successfully"))
                .andReturn();
    }

    private Planet createPlanet() {
        return new Planet("testname", "testclimate", "testterrain", 1);
    }
}
