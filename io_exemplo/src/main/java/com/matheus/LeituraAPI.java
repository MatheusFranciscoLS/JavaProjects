package com.matheus;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class LeituraAPI {
    public void exemplo() {
        try {
            URL url = new URL("https:api.github.com/users/MatheusFranciscoLS");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();
            if (status != 200) {
                throw new Exception("Erro de conexão");
            }
            //conexão estabelecida
            BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream());
            );
            //laço de repetilção
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
