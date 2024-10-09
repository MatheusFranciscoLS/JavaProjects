package com.example.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Maquina {
    public Maquina(int int1, String string, String string2, String string3, String string4, String string5, int int2,
            String string6, String string7, String string8) {
        //TODO Auto-generated constructor stub
    }
    private String id;
    private String codigo;
    private String nome;
    private String modelo;
    private String fabricante;
    private LocalDate dataAquisicao;
    private long tempoVidaEstimado;
    private String localizacao;
    private String detalhes;
    private String manual;
}
