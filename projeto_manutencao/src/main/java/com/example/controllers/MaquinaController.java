package com.example.controllers;

import java.util.List;
import com.example.api.MaquinaAPI;
import com.example.models.Maquina;

public class MaquinaController {
    public List<Maquina> readMaquinas() {
        return MaquinaAPI.getMaquinas();
    }

    public void createMaquina(Maquina maquina) {
        MaquinaAPI.postMaquina(maquina);
    }

    public void updateMaquina(Maquina maquina) {
        MaquinaAPI.putMaquina(maquina);
    }

}
