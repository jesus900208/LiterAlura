package com.aluracursos.literalura.menu;

import com.aluracursos.literalura.service.AutorService;
import com.aluracursos.literalura.service.LibroService;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ConsolaMenu {

    private Scanner sc = new Scanner(System.in);
    private final LibroService libroService;
    private final AutorService autorService;

    @Autowired
    public ConsolaMenu(LibroService libroService, AutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
    }

    public void mostrar() {
        var opcion = -1;
        while (opcion != 9) {
            var menu = """
                ================ LiterAlura ================
                 1. Buscar libro por título
                 2. Listar libros registrados
                 3. Listar autores registrados
                 4. Listar autores vivos en un rango de años
                 5. Listar libros por idioma
                                    
                 9. Salir
                                    
                 Elige una opción:  """;
            System.out.print(menu);
            try {
                  opcion = sc.nextInt();
                  sc.nextLine();
                switch (opcion) {
                    case 1:
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();
                        libroService.buscarYGuardarLibro(titulo);
                        break;
                    case 2:
                        libroService.listarLibrosRegistrados();
                        break;
                    case 3:
                        autorService.listarAutoresRegistrados();
                        break;
                    case 4:
                        System.out.print("Año de inicio: ");
                        int anioInicio = Integer.parseInt(sc.nextLine());
                        System.out.print("Año de fin: ");
                        int anioFin = Integer.parseInt(sc.nextLine());
                        autorService.listarAutoresPorRango(anioInicio, anioFin);
                        break;
                    case 5:
                        System.out.print("Idioma (en, es, fr, etc): ");
                        String idioma = sc.nextLine();
                        libroService.listarLibrosPorIdioma(idioma);
                        break;
                    case 9:
                        System.out.println("Cerrando la aplicación...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingresa un número.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
