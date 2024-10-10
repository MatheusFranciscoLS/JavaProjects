package com.example.api;

import org.json.JSONArray;
import org.json.JSONObject;
import com.example.models.Falha;
import java.time.LocalDate; // Certifique-se de importar LocalDate
import java.util.ArrayList;
import java.util.List;

public class FalhaAPI {

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
                    LocalDate.parse(jsonObject.getString("data")), // Verifique se a data está no formato correto
                    jsonObject.getString("problema"),
                    jsonObject.getString("prioridade"),
                    jsonObject.getString("operador")
                );
                falhas.add(falha);
            }
        }
        return falhas;
    }

    public static int postFalha(Falha falha) {
        JSONObject json = new JSONObject();
        json.put("maquinaId", falha.getMaquinaID());
        json.put("data", falha.getData().toString());
        json.put("problema", falha.getProblema());
        json.put("prioridade", falha.getPrioridade());
        json.put("operador", falha.getOperador());

        return ApiConnection.postData("falhas", json.toString());
    }

    public static int putFalha(Falha falha) {
        JSONObject json = new JSONObject();
        json.put("maquinaId", falha.getMaquinaID());
        json.put("data", falha.getData().toString());
        json.put("problema", falha.getProblema());
        json.put("prioridade", falha.getPrioridade());
        json.put("operador", falha.getOperador());

        return ApiConnection.putData("falhas/" + falha.getId(), json.toString());
    }
}
