package com.aluracursos.literalura.service;

import com.aluracursos.literalura.dto.GutendexLibro;
import com.aluracursos.literalura.dto.GutendexResponse;
import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class LibroService {

    private final LibroRepository libroRepo;
    private final AutorRepository autorRepo;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public LibroService(LibroRepository libroRepo, AutorRepository autorRepo) {
        this.libroRepo = libroRepo;
        this.autorRepo = autorRepo;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

   public void buscarYGuardarLibro(String titulo) {
        try {
            String tituloCodificado = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
            String url = "https://gutendex.com/books?search=" + tituloCodificado;

            RestTemplate restTemplate = new RestTemplate();
            String respuestaJson = restTemplate.getForObject(url, String.class);

            ObjectMapper mapper = new ObjectMapper();
            GutendexResponse respuesta = mapper.readValue(respuestaJson, GutendexResponse.class);

            if (respuesta.getResults().isEmpty()) {
                System.out.println("No se encontró ningún libro con ese título.");
                return;
            }

            GutendexLibro libroApi = respuesta.getResults().get(0);  // Primer resultado
            String tituloLibro = libroApi.getTitle();

            // Verificar si el libro ya existe
            Optional<Libro> libroExistente = libroRepo.findByTitulo(tituloLibro);
            if (libroExistente.isPresent()) {
                System.out.println("El libro '" + tituloLibro + "' ya está registrado.");
                return;
            }

            // Procesar autor
            if (libroApi.getAuthors().isEmpty()) {
                System.out.println("El libro no tiene autor registrado en la API.");
                return;
            }

            var autorApi = libroApi.getAuthors().get(0);
            Autor autor = autorRepo.findByNombre(autorApi.getName())
                    .orElseGet(() -> {
                    Autor nuevoAutor = new Autor();
                    nuevoAutor.setNombre(autorApi.getName());
                    nuevoAutor.setNacimiento(autorApi.getBirth_year());
                    nuevoAutor.setFallecimiento(autorApi.getDeath_year());
                    autorRepo.save(nuevoAutor);
                    return nuevoAutor;
                    });

            // Guardar libro
            Libro libro = new Libro();
            libro.setTitulo(tituloLibro);
            libro.setIdioma(libroApi.getLanguages().isEmpty() ? "desconocido" : libroApi.getLanguages().get(0));
            libro.setDescargas(libroApi.getDownload_count());
            libro.setAutor(autor);

            libroRepo.save(libro);

            System.out.println("Libro '" + tituloLibro + "' registrado exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al consultar o guardar el libro: " + e.getMessage());
        }
    }

    public void listarLibrosRegistrados() {
        var libros = libroRepo.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            libros.forEach(libro -> {
                System.out.println("****Libros Encontrados****\n" + libro.getTitulo() + "\nIdioma: " + libro.getIdioma() + "\n"+
                                   "Descargas: " + libro.getDescargas() + "\n" +
                                   "Autor: " + (libro.getAutor() != null ? libro.getAutor().getNombre() + "\n" : "Desconocido"));
            });
        }
    }

    public void listarLibrosPorIdioma(String idioma) {
        var libros = libroRepo.findByIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No hay libros en el idioma: " + idioma);
        } else {
            libros.forEach(libro -> {
                System.out.println("Libro: " + libro.getTitulo() + "\n");
            });
        }
    }
}
