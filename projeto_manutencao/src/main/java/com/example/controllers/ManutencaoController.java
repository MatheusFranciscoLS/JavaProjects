package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.api.ManutencaoAPI;
import com.example.models.Manutencao;

public class ManutencaoController {
    private List<Manutencao> manutencao;

    public ManutencaoController() {
        manutencao = new ArrayList<>();
    }

    // Métodos - CRUD
    public void createManutencao(Manutencao manutencao) {
        ManutencaoAPI.postManutencao(manutencao); // Adiciona a manutenção na API
        this.manutencao.add(manutencao);          // Adiciona na lista local
    }

    public List<Manutencao> readManutencao() {
        manutencao = ManutencaoAPI.getManutencoes(); // Atualiza a lista com dados da API
        return manutencao;
    }

    public void updateManutencao(int posicao, Manutencao manutencao) {
        this.manutencao.set(posicao, manutencao); // Atualiza a lista local
        ManutencaoAPI.putManutencao(manutencao);  // Atualiza na API
    }

}
