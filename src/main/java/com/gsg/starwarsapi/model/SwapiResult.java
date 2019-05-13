package com.gsg.starwarsapi.model;

import java.util.List;

public class SwapiResult {

    private Integer count;

    private List<SwapiPlanet> results;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<SwapiPlanet> getResults() {
        return results;
    }

    public void setResults(List<SwapiPlanet> results) {
        this.results = results;
    }

}
