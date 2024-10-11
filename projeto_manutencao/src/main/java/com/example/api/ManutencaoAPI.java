package com.example.api;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.models.Manutencao;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class ManutencaoAPI {

    // Método para obter a lista de manutenções
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
                    LocalDate.parse(jsonObject.getString("data")),
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

    // Método para adicionar uma nova manutenção
    public static void postManutencao(Manutencao manutencao) {
        // Criar um Objeto Json
        JSONObject manutencaoObject = new JSONObject();
        manutencaoObject.put("id", manutencao.getId());
        manutencaoObject.put("maquinaId", manutencao.getMaquinaID());
        manutencaoObject.put("data", manutencao.getData().toString());
        manutencaoObject.put("tipo", manutencao.getTipo());
        manutencaoObject.put("pecasTrocadas", manutencao.getPecasTrocadas());
        manutencaoObject.put("tempoParada", manutencao.getTempoDeParada());
        manutencaoObject.put("tecnicoId", manutencao.getTecnicoID());
        manutencaoObject.put("observacao", manutencao.getObservacoes());

        // Gravando na API
        if (!manutencaoObject.isEmpty()) {
            ApiConnection.postData("manutencoes", manutencaoObject.toString());
        }
    }

    // Método para atualizar uma manutenção existente
    public static void putManutencao(Manutencao manutencao) {
        // Criar um Objeto Json
        JSONObject manutencaoObject = new JSONObject();
        manutencaoObject.put("maquinaId", manutencao.getMaquinaID());
        manutencaoObject.put("data", manutencao.getData().toString());
        manutencaoObject.put("tipo", manutencao.getTipo());
        manutencaoObject.put("pecasTrocadas", manutencao.getPecasTrocadas());
        manutencaoObject.put("tempoParada", manutencao.getTempoDeParada());
        manutencaoObject.put("tecnicoId", manutencao.getTecnicoID());
        manutencaoObject.put("observacao", manutencao.getObservacoes());

        // Atualizando na API
        if (!manutencaoObject.isEmpty()) {
            ApiConnection.putData("manutencoes/" + manutencao.getId(), manutencaoObject.toString());
        }
    }
}
