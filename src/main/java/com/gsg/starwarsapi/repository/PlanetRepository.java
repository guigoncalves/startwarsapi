package com.gsg.starwarsapi.repository;

import com.gsg.starwarsapi.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {
    List<Planet> findByName(String name);
}
