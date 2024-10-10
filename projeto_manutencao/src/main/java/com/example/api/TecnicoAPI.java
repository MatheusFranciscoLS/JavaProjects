package com.example.api;

import org.json.JSONArray;
import org.json.JSONObject;
import com.example.models.Tecnico;
import java.util.ArrayList;
import java.util.List;

public class TecnicoAPI {

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

    public static int postTecnico(Tecnico tecnico) {
        JSONObject json = new JSONObject();
        json.put("nome", tecnico.getNome());
        json.put("especialidade", tecnico.getEspecialidade());
        json.put("disponibilidade", tecnico.getDisponibilidade());

        return ApiConnection.postData("tecnicos", json.toString());
    }

    public static int putTecnico(Tecnico tecnico) {
        JSONObject json = new JSONObject();
        json.put("nome", tecnico.getNome());
        json.put("especialidade", tecnico.getEspecialidade());
        json.put("disponibilidade", tecnico.getDisponibilidade());

        return ApiConnection.putData("tecnicos/" + tecnico.getId(), json.toString());
    }

    public static int deleteTecnico(String id) {
        return ApiConnection.deleteData("tecnicos/" + id);
    }
}
