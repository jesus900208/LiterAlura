package com.aluracursos.literalura.model;

import jakarta.persistence.*;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    private String idioma;

    private Integer descargas;

    @ManyToOne
    private Autor autor;

    // Getters y setters
    public Long getId() {
        return id; 
    }
    public String getTitulo() { 
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo; 
    }
    public String getIdioma() {
        return idioma; 
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma; 
    }
    public Integer getDescargas() {
        return descargas; 
    }
    public void setDescargas(Integer descargas) {
        this.descargas = descargas; 
    }
    public Autor getAutor() {
        return autor; 
    }
    public void setAutor(Autor autor) {
        this.autor = autor; 
    }
}
