package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Exercicio1 {
    public void exemplo() {
        String inputFilePath = "C:\\Users\\DevNoite\\Documents\\MatheusFrancisco\\JavaProjects\\io_exercicio4_manipulacao_csv\\exercicio1.csv";
        String outputFilePath = "C:\\Users\\DevNoite\\Documents\\MatheusFrancisco\\JavaProjects\\io_exercicio4_manipulacao_csv\\exercicio1maiorValor.csv";

        List<Produto> produtos = new ArrayList<>();
        Produto produtoComMaiorValor = null;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String linha = br.readLine(); // Ler cabeçalho
            System.out.println("------------Cabeçalho------------");
            System.out.println(linha);
            System.out.println("------------Conteúdo------------");

            while ((linha = br.readLine()) != null) {
                String[] colunas = linha.split(",");
                String nome = colunas[0];
                int quantidade = Integer.parseInt(colunas[1]);
                double preco = Double.parseDouble(colunas[2]);

                double valorTotal = quantidade * preco;
                produtos.add(new Produto(nome, quantidade, preco, valorTotal));

                // Verifica o produto com maior valor total
                if (produtoComMaiorValor == null || valorTotal > produtoComMaiorValor.getValorTotal()) {
                    produtoComMaiorValor = new Produto(nome, quantidade, preco, valorTotal);
                }

                // Exibe cada produto lido
                System.out.println(linha + ", Valor Total: " + valorTotal);
                System.out.println("--------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Exibe o produto com o maior valor total
        if (produtoComMaiorValor != null) {
            System.out.println("Produto com maior valor total em estoque: " + produtoComMaiorValor);
        }

        // Gera um novo arquivo CSV com os dados originais e o valor total
        try (FileWriter fw = new FileWriter(outputFilePath)) {
            fw.write("nome,quantidade,preco,valor_total\n"); // Cabeçalho do novo arquivo
            for (Produto produto : produtos) {
                fw.write(produto.getNome() + "," +
                        produto.getQuantidade() + "," +
                        produto.getPreco() + "," +
                        produto.getValorTotal() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}