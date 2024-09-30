package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LeituraNotasAlunos {
    private List<Aluno> alunos;

    // ctor
    public LeituraNotasAlunos() {
        alunos = new ArrayList<>();
    }

    // metodo leitura
    public void leituraFile() {
        try (BufferedReader br = new BufferedReader(
                new FileReader("notas.txt"))) {
                   do {
                    String linha = br.readLine();
                    String[] aluno = linha.split(",");
                   } while ();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
