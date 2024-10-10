package com.example.controllers;

import java.util.List;
import com.example.api.TecnicoAPI;
import com.example.models.Tecnico;

public class TecnicoController {
    public List<Tecnico> readTecnicos() {
        return TecnicoAPI.getTecnicos();
    }

    public void createTecnico(Tecnico tecnico) {
        TecnicoAPI.postTecnico(tecnico);
    }

    public void updateTecnico(Tecnico tecnico) {
        TecnicoAPI.putTecnico(tecnico);
    }

    public void deleteTecnico(String id) {
        TecnicoAPI.deleteTecnico(id);
    }
}
