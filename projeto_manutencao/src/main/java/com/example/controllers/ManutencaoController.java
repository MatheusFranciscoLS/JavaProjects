package com.example.controllers;

import com.example.api.ManutencaoAPI;
import com.example.models.Manutencao;

import java.util.List;

public class ManutencaoController {
    private ManutencaoAPI manutencaoAPI;

    public ManutencaoController() {
        manutencaoAPI = new ManutencaoAPI();
    }

    // Criar uma nova manutenção
    public void createManutencao(Manutencao manutencao) {
        int status = manutencaoAPI.createManutencao(manutencao);
        if (status == 201) {
            System.out.println("Manutenção criada com sucesso.");
        } else {
            System.out.println("Erro ao criar manutenção: " + status);
        }
    }

    // Ler todas as manutenções
    public List<Manutencao> readManutencoes() {
        return ManutencaoAPI.getManutencoes();
    }

    // Atualizar uma manutenção
    public void updateManutencao(Manutencao manutencao) {
        int status = ManutencaoAPI.updateManutencao(manutencao);
        if (status == 200) {
            System.out.println("Manutenção atualizada com sucesso.");
        } else {
            System.out.println("Erro ao atualizar manutenção: " + status);
        }
    }

    // Deletar uma manutenção
    public void deleteManutencao(String id) {
        int status = ManutencaoAPI.deleteManutencao(id);
        if (status == 204) {
            System.out.println("Manutenção deletada com sucesso.");
        } else {
            System.out.println("Erro ao deletar manutenção: " + status);
        }
    }
}
