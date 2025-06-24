package com.aluracursos.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexLibro {
    private String title;
    private List<GutendexAutor> authors;
    private List<String> languages;
    private Integer download_count;

    public String getTitle() {
        return title;
    }

    public List<GutendexAutor> getAuthors() {
        return authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public Integer getDownload_count() {
        return download_count;
    }
}