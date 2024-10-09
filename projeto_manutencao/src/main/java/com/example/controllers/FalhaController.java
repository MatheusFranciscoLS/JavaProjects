package com.example.controllers;

import com.example.api.FalhaAPI;
import com.example.models.Falha;

import java.util.List;

public class FalhaController {
    private FalhaAPI falhaAPI;

    public FalhaController() {
        falhaAPI = new FalhaAPI();
    }

    // Criar uma nova falha
    public void createFalha(Falha falha) {
        int status = falhaAPI.createFalha(falha);
        if (status == 201) {
            System.out.println("Falha criada com sucesso.");
        } else {
            System.out.println("Erro ao criar falha: " + status);
        }
    }

    // Ler todas as falhas
    public List<Falha> readFalhas() {
        return FalhaAPI.getFalhas();
    }

    // Atualizar uma falha
    public void updateFalha(Falha falha) {
        int status = FalhaAPI.updateFalha(falha);
        if (status == 200) {
            System.out.println("Falha atualizada com sucesso.");
        } else {
            System.out.println("Erro ao atualizar falha: " + status);
        }
    }

    // Deletar uma falha
    public void deleteFalha(String id) {
        int status = FalhaAPI.deleteFalha(id);
        if (status == 204) {
            System.out.println("Falha deletada com sucesso.");
        } else {
            System.out.println("Erro ao deletar falha: " + status);
        }
    }
}
