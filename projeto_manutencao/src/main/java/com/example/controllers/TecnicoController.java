package com.example.controllers;

import com.example.api.TecnicoAPI;
import com.example.models.Tecnico;

import java.util.List;

public class TecnicoController {
    private TecnicoAPI tecnicoAPI;

    public TecnicoController() {
        tecnicoAPI = new TecnicoAPI();
    }

    // Criar um novo técnico
    public void createTecnico(Tecnico tecnico) {
        int status = tecnicoAPI.createTecnico(tecnico);
        if (status == 201) {
            System.out.println("Técnico criado com sucesso.");
        } else {
            System.out.println("Erro ao criar técnico: " + status);
        }
    }

    // Ler todos os técnicos
    public List<Tecnico> readTecnicos() {
        return TecnicoAPI.getTecnicos();
    }

    // Atualizar um técnico
    public void updateTecnico(Tecnico tecnico) {
        int status = TecnicoAPI.updateTecnico(tecnico);
        if (status == 200) {
            System.out.println("Técnico atualizado com sucesso.");
        } else {
            System.out.println("Erro ao atualizar técnico: " + status);
        }
    }

    // Deletar um técnico
    public void deleteTecnico(String id) {
        int status = TecnicoAPI.deleteTecnico(id);
        if (status == 204) {
            System.out.println("Técnico deletado com sucesso.");
        } else {
            System.out.println("Erro ao deletar técnico: " + status);
        }
    }
}
