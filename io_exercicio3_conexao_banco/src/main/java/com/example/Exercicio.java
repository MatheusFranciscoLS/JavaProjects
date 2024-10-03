package com.example;

import java.sql.*;

public class Exercicio {
    public void exemplo() {
        try {
            // Estabelece a conexão com o banco de dados PostgreSQL
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "postgres");

            // Cria um statement para executar a consulta
            Statement stmt = con.createStatement();
            // Executa a consulta para recuperar todos os produtos
            ResultSet rs = stmt.executeQuery("SELECT nome, preco FROM produtos");

            double maisBarato = Double.MAX_VALUE;
            double maisCaro = Double.MIN_VALUE;
            double somaPrecos = 0;
            int contagem = 0;

            System.out.println("Produtos:");
            // Itera sobre os resultados e imprime os produtos
            while (rs.next()) {
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");

                System.out.println("Nome: " + nome + ", Preço: R$" + preco);

                // Atualiza os valores de mais barato e mais caro
                if (preco < maisBarato) {
                    maisBarato = preco;
                }
                if (preco > maisCaro) {
                    maisCaro = preco;
                }

                // Soma o preço para calcular a média
                somaPrecos += preco;
                contagem++;
            }

            // Calcula a média de preços
            double mediaPrecos = (contagem > 0) ? somaPrecos / contagem : 0;

            // Exibe os resultados das estatísticas
            System.out.printf("\nProduto mais barato: R$%.2f\n", maisBarato);
            System.out.printf("Produto mais caro: R$%.2f\n", maisCaro);
            System.out.printf("Média de preços: R$%.2f\n", mediaPrecos);

            // Fecha a conexão
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}