package com.example.controllers;

import java.util.List;

import com.example.api.MaquinaAPI;
import com.example.models.Maquina;

public class MaquinaController {
    private MaquinaAPI maquinaAPI;

    public MaquinaController() {
        maquinaAPI = new MaquinaAPI();
    }

    // Criar uma nova máquina
    public void createMaquina(Maquina maquina) {
        int status = maquinaAPI.createMaquina(maquina);
        if (status == 201) { // HTTP 201 Created
            System.out.println("Máquina criada com sucesso.");
        } else {
            System.out.println("Erro ao criar máquina: " + status);
        }
    }

    // Ler todas as máquinas
    public List<Maquina> readMaquinas() {
        return MaquinaAPI.getMaquinas();
    }

    // Atualizar uma máquina
    public void updateMaquina(Maquina maquina) {
        int status = MaquinaAPI.updateMaquina(maquina);
        if (status == 200) { // HTTP 200 OK
            System.out.println("Máquina atualizada com sucesso.");
        } else {
            System.out.println("Erro ao atualizar máquina: " + status);
        }
    }

    // Deletar uma máquina
    public void deleteMaquina(String id) {
        int status = MaquinaAPI.deleteMaquina(id);
        if (status == 204) { // HTTP 204 No Content
            System.out.println("Máquina deletada com sucesso.");
        } else {
            System.out.println("Erro ao deletar máquina: " + status);
        }
    }
}
