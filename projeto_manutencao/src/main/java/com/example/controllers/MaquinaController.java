package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.api.MaquinaAPI;
import com.example.models.Maquina;

public class MaquinaController {
    private List<Maquina> maquinas;

    public MaquinaController() {
        maquinas = new ArrayList<>();
    }

    // Métodos - CRUD
    public void createMaquina(Maquina maquina) {
        MaquinaAPI.postMaquinas(maquina); // Adiciona a máquina na API
        this.maquinas.add(maquina);        // Adiciona na lista local
    }

    public List<Maquina> readMaquinas() {
        maquinas = MaquinaAPI.getMaquinas(); // Atualiza a lista com dados da API
        return maquinas;
    }

    public void updateMaquina(int posicao, Maquina maquina) {
        maquinas.set(posicao, maquina); // Atualiza a lista local
        MaquinaAPI.putMaquinas(maquina); // Atualiza na API
    }

}
