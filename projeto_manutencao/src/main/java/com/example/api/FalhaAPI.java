package com.example.api;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.models.Falha;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class FalhaAPI {

    // Método para obter a lista de falhas
    public static List<Falha> getFalhas() {
        String json = ApiConnection.getData("falhas");
        List<Falha> falhas = new ArrayList<>();

        if (json != null) {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Falha falha = new Falha(
                    jsonObject.getString("id"),
                    jsonObject.getString("maquinaId"),
                    LocalDate.parse(jsonObject.getString("data")),
                    jsonObject.getString("problema"),
                    jsonObject.getString("prioridade"),
                    jsonObject.getString("operador")
                );
                falhas.add(falha);
            }
        }
        return falhas;
    }

    // Método para adicionar uma nova falha
    public static void postFalha(Falha falha) {
        // Criar um Objeto Json
        JSONObject falhaObject = new JSONObject();
        falhaObject.put("id", falha.getId());
        falhaObject.put("maquinaId", falha.getMaquinaID());
        falhaObject.put("data", falha.getData().toString());
        falhaObject.put("problema", falha.getProblema());
        falhaObject.put("prioridade", falha.getPrioridade());
        falhaObject.put("operador", falha.getOperador());

        // Gravando na API
        if (!falhaObject.isEmpty()) {
            ApiConnection.postData("falhas", falhaObject.toString());
        }
    }

    // Método para atualizar uma falha existente
    public static void putFalha(Falha falha) {
        // Criar um Objeto Json
        JSONObject falhaObject = new JSONObject();
        falhaObject.put("maquinaId", falha.getMaquinaID());
        falhaObject.put("data", falha.getData().toString());
        falhaObject.put("problema", falha.getProblema());
        falhaObject.put("prioridade", falha.getPrioridade());
        falhaObject.put("operador", falha.getOperador());

        // Atualizando na API
        if (!falhaObject.isEmpty()) {
            ApiConnection.putData("falhas/" + falha.getId(), falhaObject.toString());
        }
    }
}
