package com.example.api;

import org.json.JSONArray;
import org.json.JSONObject;
import com.example.models.Falha;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FalhaAPI {
    private static List<Falha> falhas = new ArrayList<>();

    // Método para obter todas as falhas (GET)
    public static List<Falha> getFalhas() {
        String json = ApiConnection.getData("falhas");
        
        if (json != null) {
            JSONArray jsonArray = new JSONArray(json);
            falhas.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Falha falha = new Falha(
                    jsonObject.getString("id"),
                    jsonObject.getString("maquinaID"),
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

    // Método para criar uma nova falha (POST)
    public int createFalha(Falha falha) {
        JSONObject falhaJson = new JSONObject();
        falhaJson.put("maquinaID", falha.getMaquinaID());
        falhaJson.put("data", falha.getData().toString());
        falhaJson.put("problema", falha.getProblema());
        falhaJson.put("prioridade", falha.getPrioridade());
        falhaJson.put("operador", falha.getOperador());

        return ApiConnection.postData("falhas", falhaJson.toString());
    }

    // Método para atualizar uma falha (PUT)
    public static int updateFalha(Falha falha) {
        JSONObject falhaJson = new JSONObject();
        falhaJson.put("id", falha.getId());
        falhaJson.put("maquinaID", falha.getMaquinaID());
        falhaJson.put("data", falha.getData().toString());
        falhaJson.put("problema", falha.getProblema());
        falhaJson.put("prioridade", falha.getPrioridade());
        falhaJson.put("operador", falha.getOperador());

        return ApiConnection.putData("falhas/" + falha.getId(), falhaJson.toString());
    }

    // Método para deletar uma falha (DELETE)
    public static int deleteFalha(String id) {
        return ApiConnection.deleteData("falhas/" + id);
    }
}
