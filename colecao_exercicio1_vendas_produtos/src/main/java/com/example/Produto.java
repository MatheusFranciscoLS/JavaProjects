package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Produto {
    private String nome;
    private double valor;
    
    @Override
    public String toString() {
     
        return "Nome produto: " + nome+ "Valor: " + valor;
    }
}
