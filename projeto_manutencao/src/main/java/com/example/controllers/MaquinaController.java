package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.models.Maquina;

public class MaquinaController {
    private List<Maquina> maquinas;

    public MaquinaController(){
        maquinas = new ArrayList<>();
    }

    //metodos
    public void createMaquina(Maquina maquina){
        maquinas.add(maquina);
    }
}
