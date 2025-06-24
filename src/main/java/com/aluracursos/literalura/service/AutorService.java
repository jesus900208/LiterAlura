package com.aluracursos.literalura.service;

import com.aluracursos.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    private final AutorRepository autorRepo;

    @Autowired
    public AutorService(AutorRepository autorRepo) {
        this.autorRepo = autorRepo;
    }

    public void listarAutoresRegistrados() {
        var autores = autorRepo.findAll();
        if (autores.isEmpty()) {
            System.out.println(" No hay autores registrados.");
        } else {
            autores.forEach(autor -> {
                System.out.println("****Autores Encontrados****\n" + autor.getNombre() + "\n" +
                                   "Nacimiento: " + autor.getNacimiento() + "\n" +
                                   "Fallecimiento: " + autor.getFallecimiento() + "\n");
            });
        }
    }
    
    public void listarAutoresPorRango(int inicio, int fin) {
        var autores = autorRepo.findAll();

        var autoresFiltrados = autores.stream()
                .filter(a -> (a.getNacimiento() != null && a.getNacimiento() <= fin)
                      && (a.getFallecimiento() == null || a.getFallecimiento() >= inicio))
                .toList();

        if (autoresFiltrados.isEmpty()) {
            System.out.println("No se encontraron autores vivos entre " + inicio + " y " + fin);
            return;
        }

        System.out.println("Autores vivos entre " + inicio + " y " + fin + ":");
        autoresFiltrados.forEach(a -> 
            System.out.println("\n***Autores Vivos Encontrados***\n" + a.getNombre() + "\n" + "Nacimiento: " + a.getNacimiento() + "\n" + "Fallecimiento: " + a.getFallecimiento() + "\n")
        );
    }
}
