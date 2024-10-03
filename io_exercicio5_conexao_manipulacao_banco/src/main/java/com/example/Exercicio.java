package com.example;

import java.sql.*;

public class Exercicio {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    // Método para exibir todos os clientes
    public void exibirClientes() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT nome, email FROM clientes")) {

            while (rs.next()) {
                System.out.println("NOME: " + rs.getString("nome") + " | EMAIL: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para adicionar um novo cliente
    public void adicionarCliente(String nome, String email) {
        String sql = "INSERT INTO clientes (nome, email) VALUES (?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            System.out.println("Cliente adicionado: " + nome);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar o e-mail de um cliente baseado no ID
    public void atualizarEmail(int id, String novoEmail) {
        String sql = "UPDATE clientes SET email = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, novoEmail);
            pstmt.setInt(2, id);
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("E-mail atualizado para ID: " + id);
            } else {
                System.out.println("Cliente não encontrado com ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um cliente baseado no ID
    public void excluirCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente excluído com ID: " + id);
            } else {
                System.out.println("Cliente não encontrado com ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}