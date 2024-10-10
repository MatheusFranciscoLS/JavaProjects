package com.example.api;

import org.json.JSONArray;
import org.json.JSONObject;
import com.example.models.Manutencao;
import java.time.LocalDate; // Certifique-se de importar LocalDate
import java.util.ArrayList;
import java.util.List;

public class ManutencaoAPI {

    public static List<Manutencao> getManutencoes() {
        String json = ApiConnection.getData("manutencoes");
        List<Manutencao> manutencoes = new ArrayList<>();

        if (json != null) {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Manutencao manutencao = new Manutencao(
                    jsonObject.getString("id"),
                    jsonObject.getString("maquinaId"),
                    LocalDate.parse(jsonObject.getString("data")), // Verifique se a data está no formato correto
                    jsonObject.getString("tipo"),
                    jsonObject.getString("pecasTrocadas"),
                    jsonObject.getLong("tempoParada"),
                    jsonObject.getString("tecnicoId"),
                    jsonObject.getString("observacao")
                );
                manutencoes.add(manutencao);
            }
        }
        return manutencoes;
    }

    public static int postManutencao(Manutencao manutencao) {
        JSONObject json = new JSONObject();
        json.put("maquinaId", manutencao.getMaquinaID());
        json.put("data", manutencao.getData().toString());
        json.put("tipo", manutencao.getTipo());
        json.put("pecasTrocadas", manutencao.getPecasTrocadas());
        json.put("tempoParada", manutencao.getTempoDeParada());
        json.put("tecnicoId", manutencao.getTecnicoID());
        json.put("observacao", manutencao.getObservacoes());

        return ApiConnection.postData("manutencoes", json.toString());
    }

    public static int putManutencao(Manutencao manutencao) {
        JSONObject json = new JSONObject();
        json.put("maquinaId", manutencao.getMaquinaID());
        json.put("data", manutencao.getData().toString());
        json.put("tipo", manutencao.getTipo());
        json.put("pecasTrocadas", manutencao.getPecasTrocadas());
        json.put("tempoParada", manutencao.getTempoDeParada());
        json.put("tecnicoId", manutencao.getTecnicoID());
        json.put("observacao", manutencao.getObservacoes());

        return ApiConnection.putData("manutencoes/" + manutencao.getId(), json.toString());
    }
}
