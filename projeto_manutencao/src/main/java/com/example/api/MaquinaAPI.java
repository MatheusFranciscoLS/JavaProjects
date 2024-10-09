package com.example.api;

import org.json.JSONArray;
import org.json.JSONObject;
import com.example.models.Maquina;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MaquinaAPI {
    private static List<Maquina> maquinas = new ArrayList<>();

    // Método para obter todas as máquinas (GET)
    public static List<Maquina> getMaquinas() {
        String json = ApiConnection.getData("maquinas");
        
        if (json != null) {
            JSONArray jsonArray = new JSONArray(json);
            maquinas.clear(); // Limpa a lista antes de adicionar os novos dados
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Maquina maquina = new Maquina(
                    jsonObject.getString("id"), // Altere para String para se alinhar ao modelo
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

    // Método para criar uma nova máquina (POST)
    public int createMaquina(Maquina maquina) {
        JSONObject maquinaJson = new JSONObject();
        maquinaJson.put("codigo", maquina.getCodigo());
        maquinaJson.put("nome", maquina.getNome());
        maquinaJson.put("modelo", maquina.getModelo());
        maquinaJson.put("fabricante", maquina.getFabricante());
        maquinaJson.put("dataAquisicao", maquina.getDataAquisicao().toString());
        maquinaJson.put("tempoVidaEstimado", maquina.getTempoVidaEstimado());
        maquinaJson.put("localizacao", maquina.getLocalizacao());
        maquinaJson.put("detalhes", maquina.getDetalhes());
        maquinaJson.put("manual", maquina.getManual());

        return ApiConnection.postData("maquinas", maquinaJson.toString());
    }

    // Método para atualizar uma máquina (PUT)
    public static int updateMaquina(Maquina maquina) {
        JSONObject maquinaJson = new JSONObject();
        maquinaJson.put("id", maquina.getId());
        maquinaJson.put("codigo", maquina.getCodigo());
        maquinaJson.put("nome", maquina.getNome());
        maquinaJson.put("modelo", maquina.getModelo());
        maquinaJson.put("fabricante", maquina.getFabricante());
        maquinaJson.put("dataAquisicao", maquina.getDataAquisicao().toString());
        maquinaJson.put("tempoVidaEstimado", maquina.getTempoVidaEstimado());
        maquinaJson.put("localizacao", maquina.getLocalizacao());
        maquinaJson.put("detalhes", maquina.getDetalhes());
        maquinaJson.put("manual", maquina.getManual());

        return ApiConnection.putData("maquinas/" + maquina.getId(), maquinaJson.toString());
    }

    // Método para deletar uma máquina (DELETE)
    public static int deleteMaquina(String id) {
        return ApiConnection.deleteData("maquinas/" + id);
    }
}
