package com.example.api;

import org.json.JSONArray;
import org.json.JSONObject;
import com.example.models.Maquina;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MaquinaAPI {

    // Método para obter todas as máquinas
    public static List<Maquina> getMaquinas() {
        String json = ApiConnection.getData("maquinas");
        List<Maquina> maquinas = new ArrayList<>();

        if (json != null) {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Maquina maquina = new Maquina(
                    jsonObject.getString("id"),
                    jsonObject.getString("codigo"),
                    jsonObject.getString("nome"),
                    jsonObject.getString("modelo"),
                    jsonObject.getString("fabricante"),
                    LocalDate.parse(jsonObject.getString("dataAquisicao")),
                    jsonObject.getLong("tempoVidaEstimado"),
                    jsonObject.getString("localizacao"),
                    jsonObject.getString("detalhes"),
                    jsonObject.getString("manual")
                );
                maquinas.add(maquina);
            }
        }
        return maquinas;
    }

    // Método para criar uma nova máquina
    public static int postMaquina(Maquina maquina) {
        JSONObject json = new JSONObject();
        json.put("codigo", maquina.getCodigo());
        json.put("nome", maquina.getNome());
        json.put("modelo", maquina.getModelo());
        json.put("fabricante", maquina.getFabricante());
        json.put("dataAquisicao", maquina.getDataAquisicao().toString());
        json.put("tempoVidaEstimado", maquina.getTempoVidaEstimado());
        json.put("localizacao", maquina.getLocalizacao());
        json.put("detalhes", maquina.getDetalhes());
        json.put("manual", maquina.getManual());

        return ApiConnection.postData("maquinas", json.toString());
    }

    // Método para atualizar uma máquina existente
    public static int putMaquina(Maquina maquina) {
        JSONObject json = new JSONObject();
        json.put("codigo", maquina.getCodigo());
        json.put("nome", maquina.getNome());
        json.put("modelo", maquina.getModelo());
        json.put("fabricante", maquina.getFabricante());
        json.put("dataAquisicao", maquina.getDataAquisicao().toString());
        json.put("tempoVidaEstimado", maquina.getTempoVidaEstimado());
        json.put("localizacao", maquina.getLocalizacao());
        json.put("detalhes", maquina.getDetalhes());
        json.put("manual", maquina.getManual());

        return ApiConnection.putData("maquinas/" + maquina.getId(), json.toString());
    }

}
