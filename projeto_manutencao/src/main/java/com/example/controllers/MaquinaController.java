package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.models.Maquina;

public class MaquinaController {
    private List<Maquina> maquinas;

    public MaquinaController() {
        maquinas = new ArrayList<>();
    }

    // metodos - CRUD
    public void createMaquina(Maquina maquina) {
        this.maquinas.add(maquina);
    }
}
