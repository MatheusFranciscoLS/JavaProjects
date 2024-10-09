package com.example.api;

import org.json.JSONArray;
import org.json.JSONObject;
import com.example.models.Tecnico;

import java.util.ArrayList;
import java.util.List;

public class TecnicoAPI {
    private static List<Tecnico> tecnicos = new ArrayList<>();

    // Método para obter todos os técnicos (GET)
    public static List<Tecnico> getTecnicos() {
        String json = ApiConnection.getData("tecnicos");
        
        if (json != null) {
            JSONArray jsonArray = new JSONArray(json);
            tecnicos.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Tecnico tecnico = new Tecnico(
                    jsonObject.getString("id"),
                    jsonObject.getString("nome"),
                    jsonObject.getString("especialidade"),
                    jsonObject.getString("disponibilidade")
                );
                tecnicos.add(tecnico);
            }
        }
        return tecnicos;
    }

    // Método para criar um novo técnico (POST)
    public int createTecnico(Tecnico tecnico) {
        JSONObject tecnicoJson = new JSONObject();
        tecnicoJson.put("nome", tecnico.getNome());
        tecnicoJson.put("especialidade", tecnico.getEspecialidade());
        tecnicoJson.put("disponibilidade", tecnico.getDisponibilidade());

        return ApiConnection.postData("tecnicos", tecnicoJson.toString());
    }

    // Método para atualizar um técnico (PUT)
    public static int updateTecnico(Tecnico tecnico) {
        JSONObject tecnicoJson = new JSONObject();
        tecnicoJson.put("id", tecnico.getId());
        tecnicoJson.put("nome", tecnico.getNome());
        tecnicoJson.put("especialidade", tecnico.getEspecialidade());
        tecnicoJson.put("disponibilidade", tecnico.getDisponibilidade());

        return ApiConnection.putData("tecnicos/" + tecnico.getId(), tecnicoJson.toString());
    }

    // Método para deletar um técnico (DELETE)
    public static int deleteTecnico(String id) {
        return ApiConnection.deleteData("tecnicos/" + id);
    }
}
