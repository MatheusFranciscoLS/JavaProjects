package com.example.api;

import org.json.JSONArray;
import org.json.JSONObject;
import com.example.models.Manutencao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManutencaoAPI {
    private static List<Manutencao> manutencoes = new ArrayList<>();

    // Método para obter todas as manutenções (GET)
    public static List<Manutencao> getManutencoes() {
        String json = ApiConnection.getData("manutencoes");
        
        if (json != null) {
            JSONArray jsonArray = new JSONArray(json);
            manutencoes.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Manutencao manutencao = new Manutencao(
                    jsonObject.getString("id"),
                    jsonObject.getString("maquinaID"),
                    LocalDate.parse(jsonObject.getString("data")),
                    jsonObject.getString("tipo"),
                    jsonObject.getString("pecasTrocadas"),
                    jsonObject.getLong("tempoDeParada"),
                    jsonObject.getString("tecnicoID"),
                    jsonObject.getString("observacoes")
                );
                manutencoes.add(manutencao);
            }
        }
        return manutencoes;
    }

    // Método para criar uma nova manutenção (POST)
    public int createManutencao(Manutencao manutencao) {
        JSONObject manutencaoJson = new JSONObject();
        manutencaoJson.put("maquinaID", manutencao.getMaquinaID());
        manutencaoJson.put("data", manutencao.getData().toString());
        manutencaoJson.put("tipo", manutencao.getTipo());
        manutencaoJson.put("pecasTrocadas", manutencao.getPecasTrocadas());
        manutencaoJson.put("tempoDeParada", manutencao.getTempoDeParada());
        manutencaoJson.put("tecnicoID", manutencao.getTecnicoID());
        manutencaoJson.put("observacoes", manutencao.getObservacoes());

        return ApiConnection.postData("manutencoes", manutencaoJson.toString());
    }

    // Método para atualizar uma manutenção (PUT)
    public static int updateManutencao(Manutencao manutencao) {
        JSONObject manutencaoJson = new JSONObject();
        manutencaoJson.put("id", manutencao.getId());
        manutencaoJson.put("maquinaID", manutencao.getMaquinaID());
        manutencaoJson.put("data", manutencao.getData().toString());
        manutencaoJson.put("tipo", manutencao.getTipo());
        manutencaoJson.put("pecasTrocadas", manutencao.getPecasTrocadas());
        manutencaoJson.put("tempoDeParada", manutencao.getTempoDeParada());
        manutencaoJson.put("tecnicoID", manutencao.getTecnicoID());
        manutencaoJson.put("observacoes", manutencao.getObservacoes());

        return ApiConnection.putData("manutencoes/" + manutencao.getId(), manutencaoJson.toString());
    }

    // Método para deletar uma manutenção (DELETE)
    public static int deleteManutencao(String id) {
        return ApiConnection.deleteData("manutencoes/" + id);
    }
}
