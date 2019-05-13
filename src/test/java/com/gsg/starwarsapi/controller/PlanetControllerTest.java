package com.gsg.starwarsapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsg.starwarsapi.model.Planet;
import com.gsg.starwarsapi.model.SwapiPlanet;
import com.gsg.starwarsapi.repository.PlanetRepository;
import com.gsg.starwarsapi.service.PlanetService;
import com.gsg.starwarsapi.service.StarwarsApiService;
import org.hibernate.mapping.Any;
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

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PlanetController.class)
public class PlanetControllerTest {

    @MockBean
    PlanetService serviceMock;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PlanetRepository repositoryMock;

    @MockBean
    StarwarsApiService apiServiceMock;

    @MockBean
    RestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createPlanet_emptyRequest() throws Exception {

        when(serviceMock.createPlanet(any())).thenReturn(
                createPlanet());

        RequestBuilder request = MockMvcRequestBuilders
                .post("/planets")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void createPlanet_basic() throws Exception {

        when(serviceMock.createPlanet(any())).thenReturn(
                createPlanet());

        RequestBuilder request = MockMvcRequestBuilders
                .post("/planets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createPlanet()))
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void getById_basic() throws Exception {

        when(repositoryMock.findById(anyLong())).thenReturn(
                java.util.Optional.of(createPlanet()));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/planets/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(createPlanet())))
                .andReturn();
    }

    @Test
    public void getByName_basic() throws Exception {

        when(repositoryMock.findByName(anyString())).thenReturn(
                Arrays.asList(createPlanet()));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/planets/name/testPlanet")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(createPlanet()))))
                .andReturn();
    }

    @Test
    public void getAll_basic() throws Exception {

        when(repositoryMock.findAll()).thenReturn(
                Arrays.asList(createPlanet()));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/planets")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(createPlanet()))))
                .andReturn();
    }

    @Test
    public void getAll_swapi() throws Exception {

        when(apiServiceMock.findAll()).thenReturn(
                Arrays.asList(new SwapiPlanet()));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/swapi/planets")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(new SwapiPlanet()))))
                .andReturn();
    }

    @Test
    public void deleteById_basic() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .delete("/planets/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted Successfully"))
                .andReturn();
    }

    public static Planet createPlanet() {
        return new Planet("testname", "testclimate", "testterrain", 1);
    }
}
