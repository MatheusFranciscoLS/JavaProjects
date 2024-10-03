package com.matheus;

import java.io.BufferedReader;
import java.io.FileReader;

public class LeituraCSV {
    public void exemplo() {
        try {
            try (BufferedReader br = new BufferedReader(
                    new FileReader("C:\\Users\\DevNoite\\Documents\\MatheusFrancisco\\JavaProjects\\io_exemplo\\dados.csv"))) {
                String linha = br.readLine();
                if (linha != null) {
                    String colunas[] = linha.split(",");
                    System.out.println("------------Cabeçalho------------");
                    for (String coluna : colunas) {
                        System.out.print(coluna + "");
                    }
                }
                System.out.println();
                System.out.println("------------Conteúdo------------");
                while ((linha = br.readLine()) != null) {
                    String colunas[] = linha.split(",");
                    for (String coluna : colunas) {
                        System.out.print(coluna + "");
                    }
                    System.out.println();
                    System.out.println("--------------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
