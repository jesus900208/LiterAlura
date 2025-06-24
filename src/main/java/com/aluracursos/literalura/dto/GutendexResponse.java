package com.aluracursos.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexResponse {
    private List<GutendexLibro> results;

    public List<GutendexLibro> getResults() {
        return results;
    }

    public void setResults(List<GutendexLibro> results) {
        this.results = results;
    }
}
