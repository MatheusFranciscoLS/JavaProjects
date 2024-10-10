package com.example.controllers;

import java.util.List;
import com.example.api.ManutencaoAPI;
import com.example.models.Manutencao;

public class ManutencaoController {
    public List<Manutencao> readManutencoes() {
        return ManutencaoAPI.getManutencoes();
    }

    public void createManutencao(Manutencao manutencao) {
        ManutencaoAPI.postManutencao(manutencao);
    }

    public void updateManutencao(Manutencao manutencao) {
        ManutencaoAPI.putManutencao(manutencao);
    }
}
