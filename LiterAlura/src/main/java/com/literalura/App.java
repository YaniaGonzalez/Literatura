package com.literalura;

import java.util.Scanner;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        LibroAPI api = new LibroAPI();
        LibroParser parser = new LibroParser();
        LibroDAO dao = new LibroDAO();

        while (true) {
            System.out.println("\n=== Catálogo LiterAlura ===");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Mostrar todos los libros");
            System.out.println("3. Buscar por autor");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el título: ");
                    String titulo = sc.nextLine();
                    String json = api.buscarLibroPorTitulo(titulo);
                    List<Libro> libros = parser.parsearRespuesta(json);
                    if (libros.isEmpty()) {
                        System.out.println("No se encontraron libros.");
                    } else {
                        Libro libro = libros.get(0); // Tomamos el primero
                        System.out.println(libro);
                        dao.guardarLibro(libro);
                    }
                }
                case 2 -> dao.mostrarTodos();
                case 3 -> {
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    dao.buscarPorAutor(autor);
                }
                case 4 -> {
                    System.out.println("¡Hasta luego!");
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }
}
