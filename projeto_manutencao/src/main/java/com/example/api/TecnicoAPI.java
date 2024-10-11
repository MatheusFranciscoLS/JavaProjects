package com.example.api;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.models.Tecnico;

import java.util.ArrayList;
import java.util.List;

public class TecnicoAPI {

    // Método para obter a lista de técnicos
    public static List<Tecnico> getTecnicos() {
        String json = ApiConnection.getData("tecnicos");
        List<Tecnico> tecnicos = new ArrayList<>();

        if (json != null) {
            JSONArray jsonArray = new JSONArray(json);
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

    // Método para adicionar um novo técnico
    public static void postTecnico(Tecnico tecnico) {
        // Criar um Objeto Json
        JSONObject tecnicoObject = new JSONObject();
        tecnicoObject.put("id", tecnico.getId());
        tecnicoObject.put("nome", tecnico.getNome());
        tecnicoObject.put("especialidade", tecnico.getEspecialidade());
        tecnicoObject.put("disponibilidade", tecnico.getDisponibilidade());

        // Gravando na API
        if (!tecnicoObject.isEmpty()) {
            ApiConnection.postData("tecnicos", tecnicoObject.toString());
        }
    }

    // Método para atualizar um técnico existente
    public static void putTecnico(Tecnico tecnico) {
        // Criar um Objeto Json
        JSONObject tecnicoObject = new JSONObject();
        tecnicoObject.put("nome", tecnico.getNome());
        tecnicoObject.put("especialidade", tecnico.getEspecialidade());
        tecnicoObject.put("disponibilidade", tecnico.getDisponibilidade());

        // Atualizando na API
        if (!tecnicoObject.isEmpty()) {
            ApiConnection.putData("tecnicos/" + tecnico.getId(), tecnicoObject.toString());
        }
    }

    // Método para deletar um técnico
    public static void deleteTecnico(String id) {
        ApiConnection.deleteData("tecnicos/" + id);
    }
}
