package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.api.FalhaAPI;
import com.example.models.Falha;

public class FalhaController {
    private List<Falha> falhas;

    public FalhaController() {
        falhas = new ArrayList<>();
    }

    // Métodos - CRUD
    public void createFalha(Falha falha) {
        FalhaAPI.postFalha(falha); // Adicionar na API
        this.falhas.add(falha);     // Adicionar na lista local
    }

    public List<Falha> readFalhas() {
        falhas = FalhaAPI.getFalhas(); // Atualiza a lista com dados da API
        return falhas;
    }

    public void updateFalha(int posicao, Falha falha) {
        falhas.set(posicao, falha); // Atualiza a lista local
        FalhaAPI.putFalha(falha);  // Atualiza na API
    }

}
