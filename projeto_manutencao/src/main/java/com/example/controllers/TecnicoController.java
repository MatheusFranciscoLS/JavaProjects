package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.api.TecnicoAPI;
import com.example.models.Tecnico;

public class TecnicoController {
    private List<Tecnico> tecnicos;

    public TecnicoController() {
        tecnicos = new ArrayList<>();
    }

    // Métodos - CRUD
    public void createTecnico(Tecnico tecnico) {
        TecnicoAPI.postTecnico(tecnico); // Adicionar na API
        this.tecnicos.add(tecnico);       // Adicionar na lista local
    }

    public List<Tecnico> readTecnicos() {
        tecnicos = TecnicoAPI.getTecnicos(); // Atualiza a lista com dados da API
        return tecnicos;
    }

    public void updateTecnico(int posicao, Tecnico tecnico) {
        tecnicos.set(posicao, tecnico); // Atualiza a lista local
        TecnicoAPI.putTecnico(tecnico); // Atualiza na API
    }

    public void deleteTecnico(int posicao) {
        Tecnico tecnico = tecnicos.get(posicao);
        TecnicoAPI.deleteTecnico(tecnico.getId()); // Deleta na API
        tecnicos.remove(posicao); // Remove da lista local
    }
}
