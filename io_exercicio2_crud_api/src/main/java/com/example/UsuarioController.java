package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class UsuarioController {
    private List<Usuario> usuarios;
    private URL url;

    public UsuarioController() {
        usuarios = new ArrayList<>();
    }

    public void read() {
        try {
            // Define a URL da API que será acessada
            url = new URL("http://localhost:3000/usuarios");
            // Abre uma conexão HTTP com a URL especificada
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            // Define o método de requisição como GET
            con.setRequestMethod("GET");

            // Obtém o código de resposta da requisição
            int status = con.getResponseCode();
            // Verifica se a resposta é 200 (OK). Caso contrário, lança uma exceção
            if (status != 200) {
                throw new Exception("Erro de conexão: " + status);
            }

            // Cria um BufferedReader para ler a resposta da API
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            // StringBuilder para construir a resposta em formato de string
            StringBuilder content = new StringBuilder();
            String linha;

            // Lê cada linha da resposta e a adiciona ao StringBuilder
            while ((linha = br.readLine()) != null) {
                content.append(linha);
            }

            // Fecha o BufferedReader
            br.close();

            // Converter o arquivo de texto para dados da classe usuario
            JSONArray dadosUsuarios = new JSONArray(content.toString());

            for (int i = 0; i < dadosUsuarios.length(); i++) {
                JSONObject usuarioJson = dadosUsuarios.getJSONObject(i);
                usuarios.add(new Usuario(
                        usuarioJson.getString("id"),
                        usuarioJson.getString("nome"),
                        usuarioJson.getInt("idade"),
                        usuarioJson.getString("endereco")));

            }

            // Imprime a resposta da API no console
            System.out.println(usuarios.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createUser(Usuario usuario) {
        // estabelecer conexão
        try {
            url = new URL("http://localhost:3000/usuarios");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json;utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            // informações necessárias para o post
            // criando o objeto JSON
            JSONObject usuarioJson = new JSONObject();
            usuarioJson.put("nome", usuario.getNome());
            usuarioJson.put("idade", usuario.getIdade());
            usuarioJson.put("endereco", usuario.getEndereco());

            // enviar dos dados para a API
            try (BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(con.getOutputStream(), "UTF-8"))) {
                bw.write(usuarioJson.toString());
                bw.flush();
            }

            // Verificar o status da resposta
            int status = con.getResponseCode();
            if (status != HttpURLConnection.HTTP_CREATED) { // HTTP 201 CREATED
                throw new Exception("Erro ao criar usuário" + status);
            }

            System.out.println("Usuário Cadastrado com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
