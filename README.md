# LiterAlura
 
 Aplicación de consola desarrollada en Java + Spring Boot + PostgreSQL que permite consultar y
 gestionar libros y autores usando la API de Gutendex.
 Funcionalidades- Buscar libros por título (consulta en la API y guarda en la base de datos).- Listar libros registrados en la base de datos.- Listar autores registrados (sin duplicados).- Listar autores vivos en un rango de años.- Listar libros por idioma.- Evita agregar libros duplicados por título.- La aplicación termina completamente al elegir la opción 9.
 
 ## Menú de opciones
 =============== LiterAlura ================
 1. Buscar libro por título
 2. Listar libros registrados
 3. Listar autores registrados
 4. Listar autores vivos en un rango de años
 5. Listar libros por idioma
 9. Salir
 
 ## Requisitos-
 Java 17- Maven- PostgreSQL (configura tu base de datos en application.properties)
 ##Cómo ejecutar
 
 ### 1. Clona el repositorio
 git clone https://github.com/jesus900208/literalura.git
 
 ### 2. Crea la base de datos
 CREATE DATABASE bd_libros;
 
 ### 3. Configura tu conexión en src/main/resources/application.properties
 spring.datasource.url=jdbc:postgresql://localhost/bd_libros
 spring.datasource.username=postgres
 spring.datasource.password=TU_PASSWORD
 
 ### 4. Ejecuta el proyecto
 mvn spring-boot:run
