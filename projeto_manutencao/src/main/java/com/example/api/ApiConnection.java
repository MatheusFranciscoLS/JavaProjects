package com.example.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiConnection {
    private static final String API_URL = "http://localhost:3000/";

    // Método GET
    public static String getData(String endpoint) {
        try {
            URL url = new URL(API_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            connection.disconnect();
            return content.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método POST
    public static int postData(String endpoint, String jsonInputString) {
        try {
            URL url = new URL(API_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"))) {
                bw.write(jsonInputString);
                bw.flush();
            }

            int responseCode = connection.getResponseCode();
            connection.disconnect();
            return responseCode;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Método PUT
    public static int putData(String endpoint, String jsonInputString) {
        try {
            URL url = new URL(API_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"))) {
                bw.write(jsonInputString);
                bw.flush();
            }

            int responseCode = connection.getResponseCode();
            connection.disconnect();
            return responseCode;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Método DELETE
    public static int deleteData(String endpoint) {
        try {
            URL url = new URL(API_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            connection.disconnect();
            return responseCode;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
