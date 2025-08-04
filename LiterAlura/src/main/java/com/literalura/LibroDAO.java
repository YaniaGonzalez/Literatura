package com.literalura;

import java.sql.*;
import java.util.List;

public class LibroDAO {
    private final String url = "jdbc:sqlite:literalura.db";

    public LibroDAO() {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "CREATE TABLE IF NOT EXISTS libros (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "titulo TEXT," +
                         "autor TEXT," +
                         "anio INTEGER)";
            conn.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarLibro(Libro libro) {
        String sql = "INSERT INTO libros (titulo, autor, anio) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, libro.getTitle());
            stmt.setString(2, String.join(", ", libro.getAuthor_name()));
            stmt.setInt(3, libro.getFirst_publish_year() != null ? libro.getFirst_publish_year() : 0);
            stmt.executeUpdate();
            System.out.println("Libro guardado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarTodos() {
        String sql = "SELECT * FROM libros";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("\nTítulo: " + rs.getString("titulo"));
                System.out.println("Autor: " + rs.getString("autor"));
                System.out.println("Año: " + rs.getInt("anio"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buscarPorAutor(String autor) {
        String sql = "SELECT * FROM libros WHERE autor LIKE ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + autor + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("\nTítulo: " + rs.getString("titulo"));
                System.out.println("Autor: " + rs.getString("autor"));
                System.out.println("Año: " + rs.getInt("anio"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
