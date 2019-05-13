package com.gsg.starwarsapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Planet {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @NotNull
    String name;

    @NotNull
    String climate;

    @NotNull
    String terrain;

    @Column(name="APPARITIONS_IN_MOVIES")
    Integer apparitionsInMovies;

    public Planet() {}

    public Planet(String name, String climate, String terrain, Integer apparitionsInMovies) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.apparitionsInMovies = apparitionsInMovies;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public Integer getApparitionsInMovies() {
        return apparitionsInMovies;
    }

    public void setApparitionsInMovies(Integer apparitionsInMovies) {
        this.apparitionsInMovies = apparitionsInMovies;
    }
}
