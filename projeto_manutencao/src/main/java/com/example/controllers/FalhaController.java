package com.example.controllers;

import java.util.List;
import com.example.api.FalhaAPI;
import com.example.models.Falha;

public class FalhaController {
    public List<Falha> readFalhas() {
        return FalhaAPI.getFalhas();
    }

    public void createFalha(Falha falha) {
        FalhaAPI.postFalha(falha);
    }

    public void updateFalha(Falha falha) {
        FalhaAPI.putFalha(falha);
    }
}
